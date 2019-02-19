package com.chinasoft.tax.service.impl;

import com.chinasoft.tax.dao.TTaxApplicationDetailMapper;
import com.chinasoft.tax.po.TTaxStatisticsPo;
import com.chinasoft.tax.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private TTaxApplicationDetailMapper tTaxApplicationDetailMapper;

    @Override
    public List<TTaxStatisticsPo> getAll(TTaxStatisticsPo po) {
        String companyId = po.getCompanyIds();
        String taxDict = po.getTaxDicts();
        String[] ids = null;
        String[] taxDicts = null;
        if(!StringUtils.isEmpty(companyId)){
            ids = companyId.split(",");
        }
        if(!StringUtils.isEmpty(taxDict)){
            taxDicts = taxDict.split(",");
        }
        po.setPTaxDicts(taxDicts);
        po.setPCompanyIds(ids);

        return tTaxApplicationDetailMapper.findStatistics(po);
    }
}