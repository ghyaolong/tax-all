package com.chinasoft.tax;

import com.chinasoft.tax.constant.CommonConstant;
import com.chinasoft.tax.service.SysConfigService;
import com.chinasoft.tax.vo.SysConfigVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@MapperScan({"com.chinasoft.tax.dao"})
@EnableTransactionManagement
//启用自带定时任务
@EnableScheduling
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class TaxWebApplication {

    @Autowired
    private SysConfigService sysConfigService;

    public static void main(String[] args) {
        SpringApplication.run(TaxWebApplication.class, args);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大
        SysConfigVo vo = sysConfigService.getMsgByKey(CommonConstant.FILE_SIZE);
        String propertyValue = vo.getPropertyValue();
        factory.setMaxFileSize(propertyValue+vo.getUnit()); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize(propertyValue+vo.getUnit());
        return factory.createMultipartConfig();
    }
}
