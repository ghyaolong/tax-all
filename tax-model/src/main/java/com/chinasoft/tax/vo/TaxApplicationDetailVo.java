package com.chinasoft.tax.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class TaxApplicationDetailVo implements Serializable {

    private String id;

    /**
     * 纳税所属期
     */
    @Excel(name = "所属期间")
    private String taxPeriod;

    /**
     * 税种
     */
    @Excel(name = "税种")
    private String taxDict;

    /**
     * 应交税额
     */
    @Excel(name = "应交税额")
    private Double payableTax;

    /**
     * 应缴滞纳金
     */
    @Excel(name = "应缴滞纳金")
    private Double lateFeePayable;

    /**
     * 申请缴纳税款
     */
    @Excel(name = "申请缴纳税款")
    private Double applTaxPayment;

    /**
     * 缴款截止日期
     */
    private Date deadline;

    /**
     * 实缴税额
     */
    @Excel(name = "实缴税额")
    private Double taxPaid;

    /**
     * 实缴滞纳金
     */
    @Excel(name = "实缴滞纳金")
    private Double overduePayment;

    /**
     * 实缴税款
     */
    @Excel(name = "实际缴纳税款")
    private Double actualTaxPayment;

    /**
     * 实际缴税时间
     */
    private Date paymentTime;

    /**
     * 备注
     */
    private String remarks;

    private Integer status;

    /**
     * 主表Id
     */
    private String taxApplicationId;

    /**
     * 税务申报表(存储资料表外键Id)
     */
    private String taxReturns;

    /**
     * 税务申报表
     */
    private String taxReturnsPath;

    /**
     * 是否上传了税务申报表
     */
    private Integer isUploadTaxReturns;

    /**
     * 扣款凭证
     */
    private String paymentCertificate;

    /**
     * 扣款凭证附件路径
     */
    private String paymentCertificatePath;

    /**
     * 是否上传了扣款凭证
     */
    private Integer isUploadCertificate;

    /**
     * 其他上传的文件
     */
    private String otherUpload;

    private String otherUploadId;

    /**
     * 预税务申报表(存储资料表外键Id)
     */
    private String preTaxReturns;

    /**
     * 预税务申报表
     */
    private String preTaxReturnsPath;

    /**
     * 是否上传了预税务申报表
     */
    private Integer isUploadPreTaxReturns;

    /**
     * 当前环节
     */
    private String currentLink;

    /**
     * 当前处理人
     */
    private String currentHandler;

    /**
     * 当前处理人姓名
     */
    private String currentHandlerName;

    /** 处理POI中日期格式*/

    @Excel(name = "缴款截止日期")
    private String poiDeadline;
    @Excel(name = "实际缴税时间")
    private String poiPaymentTime;

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
        poiDeadline = getDate(deadline);
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
        poiPaymentTime = getDate(paymentTime);
    }

    /**
     * 日期格式
     */
    public final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 获取String格式的日期信息（yyyy-MM-dd）
     *
     * @param date
     * @return yyyy-MM-dd
     */
    private static String getDate(Date date) {
        if (date == null) {
            return "";
        } else {
            return DATE_FORMAT.format(date);
        }
    }
}
