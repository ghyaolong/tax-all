package com.chinasoft.tax.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@Slf4j
@RequestMapping("/backup")
@RestController
public class BackupController {

    @RequestMapping("/db")
    public void test(){
        backDB();
    }

    private void backDB(){
        String dbName="tax";//备份的数据库名
        String username="root";//用户名
        String password="123456789";//密码
        String filePath="upload/db";
        File uploadDir = new File(filePath);
        if (!uploadDir.exists())
            uploadDir.mkdirs();

        String cmd =  "mysqldump -u"+ username +"  -p"+password + " "+dbName + " -r "
                + filePath + "/" + dbName+new java.util.Date().getTime()+ ".sql";
        //mysqldump -uroot  -p123456789 tax -r upload/db/tax1542290783182.sql
        //mysqldump -uroot  -p123456789 tax > tax1542290783182.sql
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            log.info("备份数据库成功!!!");
        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}
