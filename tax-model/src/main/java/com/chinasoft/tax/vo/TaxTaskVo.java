package com.chinasoft.tax.vo;

import lombok.Data;

import java.util.Date;

@Data
public class TaxTaskVo {

    /**
     * 流水号
     */
    private String serialNo;

    /**
     * 流程类型
     */
    private String processType;

    /**
     * 实体名称
     */
    private String entityName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 当前环节
     */
    private String currentLink;
}
