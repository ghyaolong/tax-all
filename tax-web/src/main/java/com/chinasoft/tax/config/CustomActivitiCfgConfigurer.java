package com.chinasoft.tax.config;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.stereotype.Component;

@Component
public class CustomActivitiCfgConfigurer implements ProcessEngineConfigurationConfigurer {
    @Override
    public void configure(SpringProcessEngineConfiguration processEngineConfiguration) {
        // processEngineConfiguration.setActivityFontName("WenQuanYi Zen Hei");
        processEngineConfiguration.setActivityFontName("AR PL UMing HK");
    }
}