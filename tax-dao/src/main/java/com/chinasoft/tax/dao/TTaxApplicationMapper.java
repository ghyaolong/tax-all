package com.chinasoft.tax.dao;

import com.chinasoft.tax.po.TTaxApplication;
import com.chinasoft.tax.utils.MyMapper;
import com.chinasoft.tax.vo.AuditLogVo;
import com.chinasoft.tax.vo.DoneVo;

import java.util.List;

public interface TTaxApplicationMapper extends MyMapper<TTaxApplication> {

    List<DoneVo> searchDoneByUserId(String userId);

    List<AuditLogVo> searchCommentByTaskId(String processInstanceId);
}