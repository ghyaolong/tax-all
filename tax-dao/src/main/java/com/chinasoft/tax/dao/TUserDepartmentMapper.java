package com.chinasoft.tax.dao;

import com.chinasoft.tax.po.TDepartment;
import com.chinasoft.tax.po.TUserDepartment;
import com.chinasoft.tax.utils.MyMapper;

import java.util.List;

public interface TUserDepartmentMapper extends MyMapper<TUserDepartment> {

    /**
     * 查询userId用户所拥有的部门
     * @param userId
     * @return
     */
    List<TDepartment> findByUserId(String userId);
}