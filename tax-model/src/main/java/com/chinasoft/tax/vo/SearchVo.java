package com.chinasoft.tax.vo;

import lombok.Data;

import java.io.Serializable;
/**
 * @Description: 查询条件，这里主要针对时间
 * @Date: Created in 11:53 2018/10/23
 * @Author: yaochenglong
 */

@Data
public class SearchVo implements Serializable {

    private String startDate;

    private String endDate;

    private String userId;

    // 公司编号
    private String companyId;
    private String companyName;
    // 申请人编号
    private String applicationId;
    private String applicationName;
    // 流程状态 0-审批中 1-已完结
    private Integer flowStatus;
    // 流程编号
    private String serialNumber;
}