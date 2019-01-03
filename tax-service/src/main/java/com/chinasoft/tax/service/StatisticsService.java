package com.chinasoft.tax.service;

import com.chinasoft.tax.po.TTaxStatisticsPo;

import java.util.List;

public interface StatisticsService {

    /**
     * 税金统计
     * @param po
     * @return
     */
    List<TTaxStatisticsPo> getAll(TTaxStatisticsPo po);
}
