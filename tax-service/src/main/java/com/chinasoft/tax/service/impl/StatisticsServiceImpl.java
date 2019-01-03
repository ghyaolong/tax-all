package com.chinasoft.tax.service.impl;

import com.chinasoft.tax.dao.TTaxApplicationDetailMapper;
import com.chinasoft.tax.po.TTaxStatisticsPo;
import com.chinasoft.tax.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private TTaxApplicationDetailMapper tTaxApplicationDetailMapper;

    @Override
    public List<TTaxStatisticsPo> getAll(TTaxStatisticsPo po) {
        return tTaxApplicationDetailMapper.findStatistics(po);
    }
}
