package com.chinasoft.tax.service;

import com.alibaba.fastjson.JSONObject;
import com.chinasoft.tax.vo.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;

import java.util.List;

public interface TaxProcessService {

    ProcessInstance startProcess(TaxApplicationVo taxApplicationVo);

    MyPageInfo<TaxApplicationVo> listTasks(PageVo pageVo, SearchVo searchVo);

    boolean audit(String taskId, Integer operateApprove, String comment, String userId, String currentHandler, JSONObject jsonObject);

    boolean resubmit(TaxApplicationVo bean, Integer operateApprove, String taskId);

    MyPageInfo<DoneVo> searchHistory(PageVo pageVo, SearchVo searchVo);

    List<Comment> searchAuditComment(String taskId);
}
