package com.chinasoft.tax.quartz.jobs;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 示例带参定时任务
 * @author yueyang
 */
@Slf4j
@Configuration
public class SampleJob implements Job {
    @Value("${tax.file.dbFilePath}")
    private String filePath;

    @Value("${tax.db.userName}")
    private String userName;

    @Value("${tax.db.password}")
    private String password;

    @Value("${tax.db.dbName}")
    private String dbName;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        log.info(String.format("欢迎使用schedual 时间:"+ DateUtil.now()));
        backDB();
        log.info("备份数据库["+dbName+"]成功,时间："+new Date());
    }

    private void backDB(){

        File uploadDir = new File(filePath);
        if (!uploadDir.exists())
            uploadDir.mkdirs();

        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss");
        String format = sdf.format(new Date());

        String cmd =  "mysqldump -u"+ userName +"  -p"+password + " "+dbName + " -r "
                + filePath + "/" + dbName+"_"+format+ ".sql";

        try {
            Process process = Runtime.getRuntime().exec(cmd);
            System.out.println("备份数据库成功!!!");
        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}
