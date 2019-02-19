package com.chinasoft.tax.service;

import com.chinasoft.tax.vo.SysConfigVo;

import java.util.List;

public interface SysConfigService {
    /**
     * 修改配置
     * @param sysConfigVo
     */
    void editConfig(SysConfigVo sysConfigVo);

    /**
     * 查询key对应的值
     * @param key
     * @return
     */
    SysConfigVo getMsgByKey(String key);

    List<SysConfigVo> findAll();
}
