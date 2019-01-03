package com.chinasoft.tax.quartz.jobs;

import com.chinasoft.tax.component.MailComponent;
import com.chinasoft.tax.constant.CommonConstant;
import com.chinasoft.tax.po.TaskTipPo;
import com.chinasoft.tax.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @Description: 邮件通知
 * @Author: yaochenglong
 * @Date: 23:12 2018/11/15
 */
@Slf4j
@Configuration
public class EmailNotificationJob implements Job {

    @Autowired
    private UserService userService;

    @Autowired
    private MailComponent mailComponent;

    @Override
    public void execute(JobExecutionContext context) {
        List<TaskTipPo> tipPoList = userService.getTaskTip(CommonConstant.EXECUTE_TYPE_COMMIT);
        for (TaskTipPo taskTipPo : tipPoList) {
            mailComponent.sendEmail(taskTipPo.getEmail(),"代办任务提醒","您有"+taskTipPo.getCount()+"个代办任务，请及时处理");
        }
        log.info("发送邮件提醒成功");
    }
}
