package com.chinasoft.tax.dao;

import com.chinasoft.tax.po.TCompany;
import com.chinasoft.tax.po.TUserCompany;
import com.chinasoft.tax.utils.MyMapper;

import java.util.List;

public interface TUserCompanyMapper extends MyMapper<TUserCompany> {

    /**
     * 查询<code>userId<code/>所拥有的公司列表
     * @param userId
     * @return
     */
    List<TCompany> findByUserId(String userId);
}