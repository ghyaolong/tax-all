package com.chinasoft.tax.controller;

import com.chinasoft.tax.common.utils.ResponseUtil;
import com.chinasoft.tax.po.TTaxStatisticsPo;
import com.chinasoft.tax.service.StatisticsService;
import com.chinasoft.tax.vo.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:统计查询
 * @Author: yaochenglong
 * @Date: 21:58 2018/11/12
 */

@Slf4j
@RequestMapping("/statistics")
@RestController
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    /**
     * 查询统计
     * @param tTaxStatisticsPo
     * @return
     */
    @PostMapping("/findAll")
    public Message getStatistics(@RequestBody(required = false) TTaxStatisticsPo tTaxStatisticsPo){
        List<TTaxStatisticsPo> all = statisticsService.getAll(tTaxStatisticsPo);
        return ResponseUtil.responseBody(all);
    }
}
