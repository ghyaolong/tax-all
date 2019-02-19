package com.chinasoft.tax.po;

import lombok.Data;

import javax.persistence.Transient;
import java.util.Date;

@Data
public class TTaxStatisticsPo {
    private String id;

    /**
     * 纳税所属期
     */
    private String taxPeriod;

    @Transient
    private String startTaxPeriod;

    @Transient
    private String endTaxPeriod;

    /**
     * 税种
     */
    private String taxDict;

    /**
     * 税种,已逗号隔开
     */
    private String taxDicts;

    private String[] pTaxDicts;

    /**
     * 应交税额
     */
    private Float payableTax;

    /**
     * 应缴滞纳金
     */
    private Float lateFeePayable;

    /**
     * 申请缴纳税款
     */
    private Float applTaxPayment;

    /**
     * 缴款截止日期
     */
    private Date deadline;

    /**
     * 实缴税额
     */
    private Float taxPaid;

    /**
     * 实缴滞纳金
     */
    private Float overduePayment;

    /**
     * 实际缴税时间
     */
    private Date paymentTime;

    /**
     * 备注
     */
    private String remarks;

    private Integer status;
    private String companyId;
    /***
     * 公司名称
     */
    private String companyName;
    /**
     * 国家代码
     * */
    private String countryCode;
    /**
     * 国家名称
     */
    private String countryName;

    /**
     * 公司ids
     */
    private String companyIds;
    private String[] pCompanyIds;

    /**
     * 币种
     */
    private String currency;

    /**
     * 税金类型
     * ALL     实缴税金/实缴滞纳金
     * PAID    实缴税金
     * LATEFEE 实缴滞纳金
     */
    private String taxTypes;

    @Transient
    private String startDate;
    @Transient
    private String endDate;
}
