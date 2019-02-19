package com.chinasoft.tax.common.utils;

import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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

    /**
     * 判断文件类型是否允许上传
     * @param ext
     * @param fileTypes
     * @return
     */
    public static boolean isAllFileType(String ext,String[] fileTypes){
        for (String fileType : fileTypes) {
            if(ext.equals(fileType)){
                return true;
            }
        }
        return false;
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

    /**
     * 删除上传了文件，但是税金申请未保存或未提交的文件，只保留10分钟以内上传但未保存或提交的文件
     * @param fileName
     */
    public static void evictUselessFile(String filePath,String fileName){
        if(StringUtils.isEmpty(fileName)){
            return;
        }
        String tempPath = filePath+"temp"+ File.separator;
        File file = new File(tempPath);

        boolean exists = file.exists();
        if(exists){
            File[] files = file.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    if (fileName.equals(name)) {
                        //将临时文件挪到正式目录
                        File endDir = new File(filePath);
                        File endFile = new File(endDir+File.separator+fileName);
                        try {
                            boolean bbo = endFile.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return false;
                    }
                    return true;
                }
            });
            for (File file1 : files) {
                //TODO 只能删除创建时间大于10分钟的。
                try {
                    BasicFileAttributes basicFileAttributes = Files.readAttributes(file1.toPath(), BasicFileAttributes.class);
                    String modifyTimeString = basicFileAttributes.lastModifiedTime().toString();
                    modifyTimeString = modifyTimeString.replace("Z", "").replace("T"," ");
                    SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    simpleFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                    Date modifyTime = simpleFormat.parse(modifyTimeString);
                    Date curr = new Date();
                    int minutes = (int) ((curr.getTime()-modifyTime.getTime())/1000*60);
                    if(minutes>10){
                        file1.delete();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }


    }
}
