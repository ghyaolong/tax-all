package com.chinasoft.tax.controller;

import com.chinasoft.tax.annotation.SystemLog;
import com.chinasoft.tax.common.utils.ResponseUtil;
import com.chinasoft.tax.enums.ExceptionCode;
import com.chinasoft.tax.qo.LogInfoQo;
import com.chinasoft.tax.service.LogInfoService;
import com.chinasoft.tax.vo.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 日志管理
 * @Date: Created in 19:37 2018/11/7
 * @Author: yaochenglong
 */

@Slf4j
@RequestMapping("/logInfo")
@RestController
public class LogInfoController {
    @Autowired
    private LogInfoService logInfoService;

    @PostMapping("/allPage")
    public Message getAllPage(@RequestBody LogInfoQo logInfoQo){
        log.info("查询日志,输入参数："+logInfoQo.toString());
        PageVo pageVo = logInfoQo.getPageVo();
        LogInfoVo logInfoVo = logInfoQo.getLogInfoVo();
        SearchVo searchVo = logInfoQo.getSearchVo();
        if(pageVo==null){
            throw new BizException(ExceptionCode.REQUEST_PARAM_ERROR);
        }
        MyPageInfo<LogInfoVo> allPage = logInfoService.getAllPage(pageVo,searchVo, logInfoVo);
        return ResponseUtil.responseBody(allPage);
    }

    @SystemLog(description = "删除所有日志记录")
    @DeleteMapping("/delAll")
    public Message delAll(){
        logInfoService.delAll();
        return ResponseUtil.responseBody("删除成功");
    }

    @DeleteMapping("/delByIds/{ids}")
    public Message delByIds(@PathVariable String ids){
        String[] split = ids.split(",");
        for (String s : split) {
            logInfoService.delById(s);
        }
        return ResponseUtil.responseBody("删除成功");
    }
}
