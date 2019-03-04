package com.chinasoft.tax.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chinasoft.tax.common.utils.Base64Util;
import com.chinasoft.tax.common.utils.ResponseUtil;
import com.chinasoft.tax.qo.TaxQo;
import com.chinasoft.tax.service.DictService;
import com.chinasoft.tax.service.TaxApplicationService;
import com.chinasoft.tax.service.TaxProcessService;
import com.chinasoft.tax.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/process")
public class TaxProcessController {

    private final ResourceLoader resourceLoader;
    @Value("${tax.file.upload}")
    private String filePath;
    @Autowired
    private TaxProcessService taxProcessService;
    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private TaxApplicationService taxApplicationService;

    @Autowired
    private DictService dictService;

    @Autowired
    public TaxProcessController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @PostMapping("/start")
    public Message startProcess(@RequestBody TaxApplicationVo taxApplicationVo) {
        ProcessInstance processInstance = taxProcessService.startProcess(taxApplicationVo);
        if (processInstance != null) {
            return ResponseUtil.responseBody("流程启动成功");
        } else {
            return ResponseUtil.responseBody("流程启动失败");
        }
    }

    @PostMapping("/list")
    public Message listTasks(@RequestBody TaxQo taxQo) {
        if (taxQo != null) {
            PageVo pageVo = taxQo.getPageVo();
            SearchVo searchVo = taxQo.getSearchVo();
            MyPageInfo<TaxApplicationVo> page = taxProcessService.listTasks(pageVo, searchVo);
            if (page != null) {
                return ResponseUtil.responseBody(page);
            } else {

                return ResponseUtil.responseBody("查询失败");
            }
        } else {
            return ResponseUtil.responseBody("参数有误");
        }
    }

    @PostMapping("/audit")
    public Message audit(@RequestBody Map<String, Object> map) {
        boolean audit;
        if (map != null && map.size() > 0) {
            String taskId = (String) map.get("taskId");
            Integer operateApprove = Integer.valueOf((String) map.get("operateApprove"));
            String comment = (String) map.get("comment");
            String userId = (String) map.get("userId");
            String currentHandler = (String) map.get("currentHandler");
            JSONObject jsonObject = (JSONObject) map.get("bean");
            audit = taxProcessService.audit(taskId, operateApprove, comment, userId, currentHandler, jsonObject);
        } else {
            audit = false;
        }
        return ResponseUtil.responseBody(audit);
    }

    @PostMapping("/resubmit")
    public Message resubmit(@RequestBody Map<String, Object> map) {
        boolean audit;
        if (map != null && map.size() > 0) {
            String taskId = (String) map.get("taskId");
            Integer operateApprove = Integer.parseInt((String) map.get("operateApprove"));
            JSONObject jsonObject = (JSONObject) map.get("taxApplicationVo");
            TaxApplicationVo taxApplicationVo = jsonObject.toJavaObject(TaxApplicationVo.class);
            audit = taxProcessService.resubmit(taxApplicationVo, operateApprove, taskId);
        } else {
            audit = false;
        }
        return ResponseUtil.responseBody(audit);
    }

    @PostMapping("/comment")
    public Message searchAuditComment(@RequestBody Map<String, Object> map) {
        if (map != null && map.size() > 0) {
            String taskId = (String) map.get("taskId");
            List<Comment> list = taxProcessService.searchAuditComment(taskId);
            if (list != null && list.size() > 0) {
                return ResponseUtil.responseBody(list);
            } else {

                return ResponseUtil.responseBody("查询失败");
            }
        } else {
            return ResponseUtil.responseBody("参数有误");
        }
    }

    @PostMapping("/image")
    public void getImage(@RequestBody Map<String, Object> map, HttpServletResponse response) {

        try {
            if (map != null && map.size() > 0) {
                String deploymentId = (String) map.get("deploymentId");
                String filePath = (String) map.get("filePath");
                InputStream in = repositoryService.getResourceAsStream(deploymentId, filePath);
                OutputStream out = response.getOutputStream();
                // 把图片的输入流程写入resp的输出流中
                byte[] b = new byte[1024];
                for (int len = -1; (len = in.read(b)) != -1; ) {
                    out.write(b, 0, len);
                }
                // 关闭流
                out.close();
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载相关文件
     *
     * @param filename
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String filename) {

        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(filePath, filename).toString()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/done")
    public Message searchDone(@RequestBody TaxQo taxQo) {
        if (taxQo != null) {
            PageVo pageVo = taxQo.getPageVo();
            SearchVo searchVo = taxQo.getSearchVo();
            MyPageInfo<DoneVo> page = taxProcessService.searchHistory(pageVo, searchVo);
            if (page != null) {
                return ResponseUtil.responseBody(page);
            } else {

                return ResponseUtil.responseBody("查询失败");
            }
        } else {
            return ResponseUtil.responseBody("参数有误");
        }
    }

    /**
     * 通过流水号导出excel
     * @param
     * @return
     */
    @Deprecated
    @GetMapping("/exportExcel/{json}")
    public Message exportExcelByFlowNum(@PathVariable String json,HttpServletResponse response){

        //String decode = URLDecoder.decode(json);
        String str2 = Base64Util.decode(json);
        TaxApplicationVo tax = JSON.parseObject(str2, TaxApplicationVo.class);
        // 获取导出excel指定模版，第二个参数true代表显示一个Excel中的所有 sheet
        TemplateExportParams params = new TemplateExportParams(convertTemplatePath("/templates/template.xlsx"), true);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("date", new Date());//导出一般都要日期
        data.put("one", tax);//导出一个对象
        data.put("list", tax.getDetails());//导出list集合
        //审批记录
        data.put("auditList",tax.getAuditLogVoList());

        try {
            // 简单模板导出方法
            Workbook book = ExcelExportUtil.exportExcel(params, data);
            //下载方法
            export(response, book, "税金申请");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseUtil.responseBody("导出成功");
    }

    /**
     * 导出Excel
     * @param procInstId
     * @param userId
     * @return
     */
    @GetMapping("/exportExcel/{procInstId}/{userId}")
    public Message exportExcelByProceIdAndUserId(@PathVariable String procInstId,@PathVariable String userId,HttpServletResponse response){

        DoneVo doneVo = taxProcessService.searchExportInfo(procInstId);


        // 获取导出excel指定模版，第二个参数true代表显示一个Excel中的所有 sheet
        TemplateExportParams params = new TemplateExportParams(convertTemplatePath("/templates/template.xlsx"), true);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("date", new Date());//导出一般都要日期
        data.put("one", doneVo.getTaxApplicationVo());//导出一个对象
        List<TaxApplicationDetailVo> details = doneVo.getTaxApplicationVo().getDetails();
        for (TaxApplicationDetailVo detail : details) {
            String taxDict = detail.getTaxDict();
            DictVo byCode = dictService.findByCode(taxDict);
            if(byCode!=null){
                detail.setTaxDict(byCode.getName());
            }
            detail.setActualTaxPayment(detail.getTaxPaid()+detail.getOverduePayment());
        }
        data.put("list", details);//导出list集合
        //审批记录
        data.put("auditList",doneVo.getAuditLogVoList());

        try {
            // 简单模板导出方法
            Workbook book = ExcelExportUtil.exportExcel(params, data);
            //下载方法
            export(response, book, "税金申请");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseUtil.responseBody("导出成功");
    }

    /**
     * export导出请求头设置
     *
     * @param response
     * @param workbook
     * @param fileName
     * @throws Exception
     */
    private static void export(HttpServletResponse response, Workbook workbook, String fileName) throws Exception {
        response.reset();
        response.setContentType("application/x-msdownload");
        fileName = fileName + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("gb2312"), "ISO-8859-1") + ".xls");
        ServletOutputStream outStream = null;
        try {
            outStream = response.getOutputStream();
            workbook.write(outStream);
        } finally {
            outStream.close();
        }
    }



    public static String convertTemplatePath(String path) {
        // 如果是windows 则直接返回
        // if (System.getProperties().getProperty("os.name").contains("Windows")) {
        // return path;
        // }

        Resource resource = new ClassPathResource(path);
        FileOutputStream fileOutputStream = null;
        // 将模版文件写入到 tomcat临时目录
        String folder = System.getProperty("catalina.home");
        File tempFile = new File(folder + File.separator + path);
        // System.out.println("文件路径：" + tempFile.getPath());
        // 文件存在时 不再写入
        if (tempFile.exists()) {
            return tempFile.getPath();
        }
        File parentFile = tempFile.getParentFile();
        // 判断父文件夹是否存在
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(resource.getInputStream());
            fileOutputStream = new FileOutputStream(tempFile);
            byte[] buffer = new byte[10240];
            int len = 0;
            while ((len = bufferedInputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return tempFile.getPath();
    }
}
