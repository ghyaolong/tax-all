package com.chinasoft.tax.activiti.listener;

import com.chinasoft.tax.service.impl.TaxProcessServiceImpl;
import com.chinasoft.tax.vo.TaxApplicationVo;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

@SuppressWarnings("serial")
public class ReviewProcessTaskListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        //获取RuntimeService
        RuntimeService runtimeService = TaxProcessServiceImpl.getRuntimeService();
        TaskService taskService = TaxProcessServiceImpl.getTaskService();
        //获得流程过程中输入的业务参数
        TaxApplicationVo taxApplicationVo = (TaxApplicationVo) runtimeService.getVariable(delegateTask.getExecutionId(), "bean");
        // 获取申请人编号
        String currentHandler = taxApplicationVo.getCurrentHandler();
        // delegateTask.setAssignee(currentHandler);

        String taskId = delegateTask.getId();
        taskService.setAssignee(taskId, taxApplicationVo.getCurrentHandler());
    }
}