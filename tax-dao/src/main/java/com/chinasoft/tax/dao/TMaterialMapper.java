package com.chinasoft.tax.dao;

import com.chinasoft.tax.po.TMaterial;
import com.chinasoft.tax.utils.MyMapper;
import com.chinasoft.tax.po.MaterialInfoPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TMaterialMapper extends MyMapper<TMaterial> {

    /**
     * 查询资料信息
     * @param companyName
     * @param companyIds 公司ids
     * @param materialTypeDict
     * @param startTime
     * @param endTime
     * @return
     */
    List<MaterialInfoPo> findAllMaterial(@Param("companyName") String companyName, @Param("companyIds") String[] companyIds,@Param("taxDicts") String[] taxDicts,@Param("materialTypeDict") String materialTypeDict, @Param("startTime") String startTime, @Param("endTime") String endTime);
}