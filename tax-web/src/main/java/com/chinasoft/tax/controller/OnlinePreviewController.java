package com.chinasoft.tax.controller;

import com.chinasoft.tax.enums.ExceptionCode;
import com.chinasoft.tax.vo.BizException;
import lombok.extern.slf4j.Slf4j;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.regex.Pattern;


@Slf4j
@RestController
public class OnlinePreviewController {

    @Value("${tax.file.upload}")
    private String filePath;

    @PostMapping("/previewFile")
    public  HttpServletResponse preview(@RequestBody ModelMap map, HttpServletResponse response) throws IOException {
        String fileName = (String) map.get("fileName");
        if(StringUtils.isEmpty(fileName)){
            throw new BizException(ExceptionCode.PARAM_IS_NOT_NULL);
        }
        File file = null;
        String dirPath = filePath;
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        file = new File(dirFile + File.separator + fileName);
        log.info("文件路径:"+dirFile + File.separator + fileName);
        if(!file.exists()){
            //throw new BizException(ExceptionCode.UNKNOWN_ERROR.getCode(),"文件不存在");
            response.reset();
            PrintWriter out = response.getWriter();
            response.setContentType("UTF-8");
            out.print("file is not found");
            out.flush();
            out.close();
            return response;
        }
        //判断文件类型(是否是pdf格式,不是则转换成pdf文件)
        if (!"pdf".equals(fileName.substring(fileName.lastIndexOf(".") + 1))) {
            file = pdf(file.getPath());
        }
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(file));
        byte[] buf = new byte[1024*10];
        int len = 0;
        response.reset(); // 非常重要
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition",
                "inline; filename=" + java.net.URLEncoder.encode(fileName.replaceAll(".", ".pdf"), "UTF-8"));

        OutputStream out = response.getOutputStream();
        while ((len = br.read(buf)) != -1) {
            out.write(buf, 0, len);
        }
        out.flush();
        br.close();
        out.close();
        file.delete();
        if(!("pdf".equals(fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase()))){
            file.delete();
        }
        return response;
    }

    private static File pdf(String url) {
        OfficeManager officeManager = null;
        //也可连接外部服务器中openoffice
        try {
            if (StringUtils.isEmpty(url)) {
                return null;
            }

            File inputFile = new File(url);

            if (!inputFile.exists()) {
                return null;
            }
            // 获取OpenOffice的安装路劲
            officeManager = getOfficeManager();
            // 连接OpenOffice
            OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);

            return converterFile(inputFile, url.replaceAll("." + url.substring(url.lastIndexOf(".") + 1), ".pdf"), url, converter);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 停止openOffice
            if (officeManager != null) {
                officeManager.stop();
            }
        }
        return null;

    }

    /**
     * 连接OpenOffice.org 并且启动OpenOffice.org
     *
     * @return
     */
    public static OfficeManager getOfficeManager() {
        DefaultOfficeManagerConfiguration config = new DefaultOfficeManagerConfiguration();
        //int Open_Office_Port=8100;

        // 设置OpenOffice的安装目录
        config.setOfficeHome(getOfficeHome());
        //可设置openoffice端口号，也可默认为2003
        //config.setPortNumber(Open_Office_Port);

        // 启动OpenOffice的服务
        OfficeManager officeManager = config.buildOfficeManager();
        officeManager.start();
        return officeManager;
    }

    /**
     * 根据操作系统的名称，获取OpenOffice的安装目录
     * 如我的OpenOffice安装在：C:/Program Files (x86)/OpenOffice 4<br>
     *
     * @return OpenOffice的安装目录
     */
    public static String getOfficeHome() {
        String osName = System.getProperty("os.name");
        System.out.println("操作系统名称:" + osName);
        if (Pattern.matches("Linux.*", osName)) {
            return "/opt/openoffice4";
        } else if (Pattern.matches("Windows.*", osName)) {
            return "C:/Program Files (x86)/OpenOffice 4";
        } else if (Pattern.matches("Mac.*", osName)) {
            return "/Applications/OpenOffice.app/Contents/";
        }
        return null;
    }

    /**
     * openoffice转换文件
     *
     * @param inputFile
     * @param outputFilePath_end
     * @param inputFilePath
     * @param
     * @param converter
     */
    public static File converterFile(File inputFile, String outputFilePath_end, String inputFilePath,
                                     OfficeDocumentConverter converter) {
        File outputFile = new File(outputFilePath_end);
        // 假如目标路径不存在,则新建该路径
        if (!outputFile.getParentFile().exists()) {
            outputFile.getParentFile().mkdirs();
        }
        converter.convert(inputFile, outputFile);
        System.out.println("文件：" + inputFilePath + "\n转换为\n目标文件：" + outputFile + "\n成功!");
        return outputFile;
    }
}