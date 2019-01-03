package com.chinasoft.tax.common.utils;

import cn.hutool.core.io.resource.ClassPathResource;
import org.springframework.beans.factory.config.YamlMapFactoryBean;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

public class MyFileUtils {

    /**
     * 获取文件扩展名
     *
     * @param fileName 1.jpg
     * @return
     */
    public static String getExtensionName(String fileName) {
        if ((fileName != null) && (fileName.length() > 0)) {
            int dot = fileName.lastIndexOf('.');
            if ((dot > -1) && (dot < (fileName.length() - 1))) {
                return fileName.substring(dot);
            }
        }
        return null;
    }

    /**
     * 判断文件是否超出允许上传的最大限度
     * @param fileSize
     * @param maxSize
     * @return
     */
    public static boolean isAllowSize(long fileSize,long maxSize){
        return fileSize<=maxSize;
    }

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
