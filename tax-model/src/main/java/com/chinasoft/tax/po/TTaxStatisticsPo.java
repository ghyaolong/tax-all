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
     * 应交税额
     */
    private Long payableTax;

    /**
     * 应缴滞纳金
     */
    private Long lateFeePayable;

    /**
     * 申请缴纳税款
     */
    private Long applTaxPayment;

    /**
     * 缴款截止日期
     */
    private Date deadline;

    /**
     * 实缴税额
     */
    private Long taxPaid;

    /**
     * 实缴滞纳金
     */
    private Long overduePayment;

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
    private String companyName;
    private String countryCode;
    private String countryName;
    private String currency;

    @Transient
    private String startDate;
    @Transient
    private String endDate;
}
