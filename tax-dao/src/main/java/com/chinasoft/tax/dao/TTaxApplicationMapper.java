package com.chinasoft.tax.dao;

import com.chinasoft.tax.po.TTaxApplication;
import com.chinasoft.tax.utils.MyMapper;
import com.chinasoft.tax.vo.AuditLogVo;
import com.chinasoft.tax.vo.DoneVo;
import com.chinasoft.tax.vo.TaxApplicationVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TTaxApplicationMapper extends MyMapper<TTaxApplication> {

    List<DoneVo> searchDoneByUserId(String userId);

    List<AuditLogVo> searchCommentByTaskId(String processInstanceId);

    List<TTaxApplication> searchHistory(@Param("id") String id, @Param("userId") String userId);
}