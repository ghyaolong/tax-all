package com.chinasoft.tax.controller;

import com.chinasoft.tax.annotation.SystemLog;
import com.chinasoft.tax.common.utils.IDGeneratorUtils;
import com.chinasoft.tax.common.utils.MyFileUtils;
import com.chinasoft.tax.common.utils.ResponseUtil;
import com.chinasoft.tax.enums.ExceptionCode;
import com.chinasoft.tax.service.MaterialService;
import com.chinasoft.tax.vo.BizException;
import com.chinasoft.tax.vo.MaterialVo;
import com.chinasoft.tax.vo.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Paths;

@Slf4j
@RequestMapping("/file")
@RestController
public class FileController {


    @Value("${tax.file.upload}")
    private String filePath;

    @Value("${tax.file.maxSize}")
    private Long fileMaxSize;

    @Autowired
    private MaterialService materialService;

    private final ResourceLoader resourceLoader;


    @Autowired
    public FileController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    /**
     * 上传图片
     * 使用NIO可以提高图片上传效率。
     *
     * @param files 图片
     * @return
     */
    @SystemLog(description = "上传资料")
    @PostMapping("/upload")
    public Message uploadFile(@RequestParam(value = "file", required = false) MultipartFile[] files,
                              @RequestParam(value = "materialTypeDict") String materialTypeDict,
                              @RequestParam(value="taxDict") String taxDict,  // 税种
                              @RequestParam(value="currency") String currency // 币种
                                ) {

        if(StringUtils.isEmpty(materialTypeDict)){
            throw new BizException(ExceptionCode.REQUEST_PARAM_ERROR);
        }
        if (files != null && files.length>0) {
            if(StringUtils.isEmpty(filePath)){
                filePath = ClassUtils.getDefaultClassLoader().getResource("upload").getPath();
            }
            String newFileName;
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    String fileName;
                    try {
                        fileName = file.getOriginalFilename();
                        String ext = MyFileUtils.getExtensionName(fileName);
                        if (ext == null) {
                            // 说明上传的文件没有扩展名，不是正规的文件
                            return ResponseUtil.responseBody(ExceptionCode.FILE_UPLOAD_ERROR.getCode(), "无法识别的文件");
                        } else {
                            // 判断文件大小
                            if (!MyFileUtils.isAllowSize(file.getSize(), fileMaxSize)) {
                                return ResponseUtil.responseBody(ExceptionCode.FILE_UPLOAD_ERROR.getCode(), "文件过大，不能超过4M");
                            }
                        }
                        newFileName = IDGeneratorUtils.getUUID32() + ext;
                        //Files.copy(file.getInputStream(), Paths.get(filePath+imgPath, newFileName));
                        MyFileUtils.uploadFile(file.getBytes(), filePath, newFileName);

                        //将此文件保存到数据库，并返回文件相关信息
                        MaterialVo vo = new MaterialVo();
                        vo.setId(IDGeneratorUtils.getUUID32());
                        vo.setFileName(newFileName);
                        vo.setOriName(fileName);
                        vo.setPath(filePath+newFileName);
                        vo.setSuffix(ext);
                        vo.setMaterialTypeDict(materialTypeDict);
                        vo.setTaxDict(taxDict);
                        vo.setCurrency(currency);
                        materialService.add(vo);

                        return ResponseUtil.responseBody(vo);
                    } catch (IOException | RuntimeException e) {
                        log.error("上传文件失败", e);
                        return ResponseUtil.responseBody(ExceptionCode.FILE_UPLOAD_ERROR.getCode(), "上传失败，请稍后在试");
                    } catch (Exception e) {
                        log.error("上传文件失败", e);
                        return ResponseUtil.responseBody(ExceptionCode.FILE_UPLOAD_ERROR.getCode(), "上传失败，请稍后在试");
                    }

                } else {
                    throw new BizException(ExceptionCode.FILE_IS_NOT_NULL);
                }
            }
        } else {
            throw new BizException(ExceptionCode.FILE_IS_NOT_NULL);
        }
        return null;
    }


    /**
     * 预览相关文件
     * @param filename
     * @return
     */
    @SystemLog(description = "预览文件")
    @RequestMapping(method = RequestMethod.GET, value = "/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String filename) {

        try {

//            ResponseEntity<Resource> ok = ResponseEntity
//                    .ok(resourceLoader.getResource("file:" + Paths.get(filePath).toString()));

            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(filePath, filename).toString()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 下载文件
     * @param res
     * @param fileName
     */
    @GetMapping("/download/{fileName:.+}")
    public void download(HttpServletResponse res,@PathVariable String fileName) {
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            //filePath = ClassUtils.getDefaultClassLoader().getResource("upload").getPath();
            bis = new BufferedInputStream(new FileInputStream(new File(
                    filePath + fileName)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        log.info("download success");
    }

}
