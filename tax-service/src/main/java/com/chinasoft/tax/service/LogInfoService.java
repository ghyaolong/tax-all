package com.chinasoft.tax.service;

import com.chinasoft.tax.vo.LogInfoVo;
import com.chinasoft.tax.vo.MyPageInfo;
import com.chinasoft.tax.vo.PageVo;
import com.github.pagehelper.PageInfo;

public interface LogInfoService {


    /**
     * 分页获取日志信息
     * @param pageVo
     * @return
     */
    MyPageInfo<LogInfoVo> getAllPage(PageVo pageVo, LogInfoVo logInfoVo);

    /**
     * 保存日志
     * @param logInfoVo
     */
    void add(LogInfoVo logInfoVo);

    void delAll();

    void delById(String s);
}
