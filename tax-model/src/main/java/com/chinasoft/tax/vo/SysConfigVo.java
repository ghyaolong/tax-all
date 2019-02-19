package com.chinasoft.tax.vo;

import lombok.Data;

@Data
public class SysConfigVo {

    private String id;

    /**
     * 键
     */
    private String property;

    /**
     * 键的名称
     */
    private String propertyName;

    /**
     * 值
     */
    private String propertyValue;

    /**
     * 单位
     */
    private String unit;
}
