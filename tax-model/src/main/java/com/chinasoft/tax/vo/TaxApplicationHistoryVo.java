package com.chinasoft.tax.vo;

import lombok.Data;

import java.util.Date;

@Data
public class TaxApplicationHistoryVo {

    /**
     * 流程编号
     */
    private String serialNumber;

    /**
     * 流程类型
     */
    private String serialType;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 当前环节
     */
    private String currentLink;

    /**
     * 当前处理人
     */
    private String currentHandler;
}
