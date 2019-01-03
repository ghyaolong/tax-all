package com.chinasoft.tax.dao;

import com.chinasoft.tax.po.TCompany;
import com.chinasoft.tax.po.TaskTipPo;
import com.chinasoft.tax.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TCompanyMapper extends MyMapper<TCompany> {

    /**
     * 通过用户id查询公司
     * @param userId
     * @return
     */
    List<TCompany> getByUserId(String userId);

    /**
     * 获取该用户下status状态的任务统计
     * @param status
     * @return
     */
    List<TaskTipPo> getTaskTip(@Param("status") Integer status);
}