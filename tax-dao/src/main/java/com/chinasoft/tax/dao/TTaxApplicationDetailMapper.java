package com.chinasoft.tax.dao;

import com.chinasoft.tax.po.TTaxApplicationDetail;
import com.chinasoft.tax.po.TTaxStatisticsPo;
import com.chinasoft.tax.utils.MyMapper;

import java.util.List;

public interface TTaxApplicationDetailMapper extends MyMapper<TTaxApplicationDetail> {

    List<TTaxStatisticsPo> findStatistics(TTaxStatisticsPo tTaxStatisticsPo);
}