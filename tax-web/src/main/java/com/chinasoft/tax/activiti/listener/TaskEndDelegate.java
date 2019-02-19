package com.chinasoft.tax.activiti.listener;

import com.alibaba.fastjson.JSONObject;
import com.chinasoft.tax.dao.TTaxApplicationDetailMapper;
import com.chinasoft.tax.dao.TTaxApplicationMapper;
import com.chinasoft.tax.po.TTaxApplication;
import com.chinasoft.tax.po.TTaxApplicationDetail;
import com.chinasoft.tax.service.impl.TaxProcessServiceImpl;
import com.chinasoft.tax.vo.TaxApplicationDetailVo;
import com.chinasoft.tax.vo.TaxApplicationVo;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class TaskEndDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        //获取RuntimeService
        RuntimeService runtimeService = TaxProcessServiceImpl.getRuntimeService();
        TTaxApplicationMapper taxApplicationMapper = TaxProcessServiceImpl.getTTaxApplicationMapper();
        TTaxApplicationDetailMapper taxApplicationDetailMapper = TaxProcessServiceImpl.getTTaxApplicationDetailMapper();

        /** 判断流程是否结束，查询正在执行的执行对象表 */
        ProcessInstance rpi = runtimeService.createProcessInstanceQuery().processInstanceId(execution.getProcessInstanceId()).singleResult();
        if (rpi != null) {
            String pdi = rpi.getProcessDefinitionId();
            String key = pdi.substring(0, pdi.indexOf(":"));
            String jsonObject = JSONObject.toJSONString(execution.getVariable("bean"));
            TaxApplicationVo bean = JSONObject.parseObject(jsonObject, TaxApplicationVo.class);
            // 更新实体对象
            try {
                bean.setStatus(4);
                updateTaxFlowProcess(bean, taxApplicationMapper, taxApplicationDetailMapper);
            } catch (Exception e) {
                e.printStackTrace();
            }
            /**proEndService 是业务审批结束处理类， 想使用rpi.getProcessDefinitionKey()进行标记，但是在此处获取时，获取为空，所以从ProcessDefinitionId中截取的**/
            /** ProcessBusinessService  是获取业务bean的工厂类**/

            // 获取流程变量，网关
            // String status = (String) runtimeService.getVariable(execution.getId(), "operateApprove");
            // System.out.println(rpi.getBusinessKey());
            // proEndService.endProcess(rpi.getBusinessKey(),status);
        }
    }

    /**
     * 更新税金申请单
     *
     * @param taxApplicationVo
     * @return
     * @throws Exception
     */
    private boolean updateTaxFlowProcess(TaxApplicationVo taxApplicationVo, TTaxApplicationMapper taxApplicationMapper, TTaxApplicationDetailMapper taxApplicationDetailMapper) throws Exception {
        if (taxApplicationVo == null) {
            throw new Exception("税金申请信息参数为空");
        }
        TTaxApplication taxApplication = new TTaxApplication();
        BeanUtils.copyProperties(taxApplicationVo, taxApplication);
        if (taxApplication != null && !StringUtils.isEmpty(taxApplication.getId())) {
            int i = taxApplicationMapper.updateByPrimaryKeySelective(taxApplication);
            if (i <= 0) {
                throw new Exception("税金申请信息更新失败");
            }
        }
        List<TaxApplicationDetailVo> taxApplicationDetailVoList = taxApplicationVo.getDetails();
        for (TaxApplicationDetailVo bean : taxApplicationDetailVoList) {
            if (!StringUtils.isEmpty(bean.getId())) {
                TTaxApplicationDetail taxApplicationDetail = new TTaxApplicationDetail();
                BeanUtils.copyProperties(bean, taxApplicationDetail);
                int i = taxApplicationDetailMapper.updateByPrimaryKey(taxApplicationDetail);
                if (i <= 0) {
                    throw new Exception("税金申请子信息更新失败");
                }
            }
        }
        return true;
    }

}
