package com.chinasoft.tax.dao;

import com.chinasoft.tax.po.TCompanyTaxes;
import com.chinasoft.tax.po.TDict;
import com.chinasoft.tax.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TCompanyTaxesMapper extends MyMapper<TCompanyTaxes> {

    /**通过公司id获取字典*/
    List<TDict> findByCompanyId(@Param("companyId") String companyId);
}