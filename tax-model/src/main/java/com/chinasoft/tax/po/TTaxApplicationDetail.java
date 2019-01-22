package com.chinasoft.tax.po;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_tax_application_detail")
public class TTaxApplicationDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 纳税所属期
     */

    @Column(name = "tax_period")
    private String taxPeriod;

    /**
     * 税种
     */

    @Column(name = "tax_dict")
    private String taxDict;

    /**
     * 应交税额
     */

    @Column(name = "payable_tax")
    private Double payableTax;

    /**
     * 应缴滞纳金
     */

    @Column(name = "late_fee_payable")
    private Double lateFeePayable;

    /**
     * 申请缴纳税款
     */
    @Column(name = "appl_tax_payment")
    private Double applTaxPayment;

    /**
     * 缴款截止日期
     */
    private Date deadline;

    /**
     * 实缴税额
     */
    @Column(name = "tax_paid")
    private Double taxPaid;

    /**
     * 实缴滞纳金
     */
    @Column(name = "overdue_payment")
    private Double overduePayment;

    /**
     * 实际缴纳税款
     */
    @Column(name = "actual_tax_payment")
    private Double actualTaxPayment;

    /**
     * 实际缴税时间
     */
    @Column(name = "payment_time")
    private Date paymentTime;

    /**
     * 备注
     */
    private String remarks;

    private Integer status;

    /**
     * 主表Id
     */
    @Column(name = "tax_application_id")
    private String taxApplicationId;

    /**
     * 税务申报表(存储资料表外键Id)
     */
    @Column(name = "tax_returns")
    private String taxReturns;

    /**
     * 税务申报表
     */
    @Column(name = "tax_returns_path")
    private String taxReturnsPath;

    /**
     * 财务报表附件名称
     */
    @Column(name="tax_returns_name")
    private String taxReturnsPathFileName;

    /**
     * 是否上传了税务申报表
     */
    @Column(name = "is_upload_tax_returns")
    private Integer isUploadTaxReturns;

    /**
     * 扣款凭证
     */
    @Column(name = "payment_certificate")
    private String paymentCertificate;

    /**
     * 扣款凭证附件名称
     */
    @Column(name = "payment_certificate_name")
    private String paymentCertificateFileName;

    /**
     * 扣款凭证附件路径
     */
    @Column(name = "payment_certificate_path")
    private String paymentCertificatePath;

    /**
     * 是否上传了扣款凭证
     */
    @Column(name = "is_upload_certificate")
    private Integer isUploadCertificate;

    /**
     * 其他上传的文件
     */
    @Column(name = "other_upload")
    private String otherUpload;

    @Column(name = "other_upload_id")
    private String otherUploadId;

    /**
     * 其他附件名称
     */
    @Column(name = "other_name")
    private String otherUploadFileName;



    ///////added by yaochenglong at 2018-11-23 start

    /**
     * 预税务申报表(存储资料表外键Id)
     */
    @Column(name = "pre_tax_returns")
    private String preTaxReturns;

    /**
     * 预税务申报表
     */
    @Column(name = "pre_tax_returns_path")
    private String preTaxReturnsPath;

    /**
     * 预申报表附件名称
     */
    @Column(name = "pre_tax_returns_name")
    private String preTaxReturnsPathFileName;

    /**
     * 是否上传了预税务申报表
     */
    @Column(name = "is_upload_pre_tax_returns")
    private Integer isUploadPreTaxReturns;


    ///////added by yaochenglong at 2018-11-23 end


    public String getPreTaxReturns() {
        return preTaxReturns;
    }

    public void setPreTaxReturns(String preTaxReturns) {
        this.preTaxReturns = preTaxReturns;
    }

    public String getPreTaxReturnsPath() {
        return preTaxReturnsPath;
    }

    public void setPreTaxReturnsPath(String preTaxReturnsPath) {
        this.preTaxReturnsPath = preTaxReturnsPath;
    }

    public Integer getIsUploadPreTaxReturns() {
        return isUploadPreTaxReturns;
    }

    public void setIsUploadPreTaxReturns(Integer isUploadPreTaxReturns) {
        this.isUploadPreTaxReturns = isUploadPreTaxReturns;
    }

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取纳税所属期
     *
     * @return tax_period - 纳税所属期
     */
    public String getTaxPeriod() {
        return taxPeriod;
    }

    /**
     * 设置纳税所属期
     *
     * @param taxPeriod 纳税所属期
     */
    public void setTaxPeriod(String taxPeriod) {
        this.taxPeriod = taxPeriod;
    }

    /**
     * 获取税种
     *
     * @return tax_dict - 税种
     */
    public String getTaxDict() {
        return taxDict;
    }

    /**
     * 设置税种
     *
     * @param taxDict 税种
     */
    public void setTaxDict(String taxDict) {
        this.taxDict = taxDict;
    }

    /**
     * 获取应交税额
     *
     * @return payable_tax - 应交税额
     */
    public Double getPayableTax() {
        return payableTax;
    }

    /**
     * 设置应交税额
     *
     * @param payableTax 应交税额
     */
    public void setPayableTax(Double payableTax) {
        this.payableTax = payableTax;
    }

    /**
     * 获取应缴滞纳金
     *
     * @return late_fee_payable - 应缴滞纳金
     */
    public Double getLateFeePayable() {
        return lateFeePayable;
    }

    /**
     * 设置应缴滞纳金
     *
     * @param lateFeePayable 应缴滞纳金
     */
    public void setLateFeePayable(Double lateFeePayable) {
        this.lateFeePayable = lateFeePayable;
    }

    /**
     * 获取申请缴纳税款
     *
     * @return appl_tax_payment - 申请缴纳税款
     */
    public Double getApplTaxPayment() {
        return applTaxPayment;
    }

    /**
     * 设置申请缴纳税款
     *
     * @param applTaxPayment 申请缴纳税款
     */
    public void setApplTaxPayment(Double applTaxPayment) {
        this.applTaxPayment = applTaxPayment;
    }

    /**
     * 获取缴款截止日期
     *
     * @return deadline - 缴款截止日期
     */
    public Date getDeadline() {
        return deadline;
    }

    /**
     * 设置缴款截止日期
     *
     * @param deadline 缴款截止日期
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    /**
     * 获取实缴税额
     *
     * @return tax_paid - 实缴税额
     */
    public Double getTaxPaid() {
        return taxPaid;
    }

    /**
     * 设置实缴税额
     *
     * @param taxPaid 实缴税额
     */
    public void setTaxPaid(Double taxPaid) {
        this.taxPaid = taxPaid;
    }

    /**
     * 获取实缴滞纳金
     *
     * @return overdue_payment - 实缴滞纳金
     */
    public Double getOverduePayment() {
        return overduePayment;
    }

    /**
     * 设置实缴滞纳金
     *
     * @param overduePayment 实缴滞纳金
     */
    public void setOverduePayment(Double overduePayment) {
        this.overduePayment = overduePayment;
    }

    /**
     * 获取实际缴税时间
     *
     * @return payment_time - 实际缴税时间
     */
    public Date getPaymentTime() {
        return paymentTime;
    }

    /**
     * 设置实际缴税时间
     *
     * @param paymentTime 实际缴税时间
     */
    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    /**
     * 获取备注
     *
     * @return remarks - 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注
     *
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取主表Id
     *
     * @return tax_application_id - 主表Id
     */
    public String getTaxApplicationId() {
        return taxApplicationId;
    }

    /**
     * 设置主表Id
     *
     * @param taxApplicationId 主表Id
     */
    public void setTaxApplicationId(String taxApplicationId) {
        this.taxApplicationId = taxApplicationId;
    }

    /**
     * 获取税务申报表(存储资料表外键Id)
     *
     * @return tax_returns - 税务申报表(存储资料表外键Id)
     */
    public String getTaxReturns() {
        return taxReturns;
    }

    /**
     * 设置税务申报表(存储资料表外键Id)
     *
     * @param taxReturns 税务申报表(存储资料表外键Id)
     */
    public void setTaxReturns(String taxReturns) {
        this.taxReturns = taxReturns;
    }

    /**
     * 获取税务申报表
     *
     * @return tax_returns_path - 税务申报表
     */
    public String getTaxReturnsPath() {
        return taxReturnsPath;
    }

    /**
     * 设置税务申报表
     *
     * @param taxReturnsPath 税务申报表
     */
    public void setTaxReturnsPath(String taxReturnsPath) {
        this.taxReturnsPath = taxReturnsPath;
    }

    /**
     * 获取是否上传了税务申报表
     *
     * @return is_upload_tax_returns - 是否上传了税务申报表
     */
    public Integer getIsUploadTaxReturns() {
        return isUploadTaxReturns;
    }

    /**
     * 设置是否上传了税务申报表
     *
     * @param isUploadTaxReturns 是否上传了税务申报表
     */
    public void setIsUploadTaxReturns(Integer isUploadTaxReturns) {
        this.isUploadTaxReturns = isUploadTaxReturns;
    }

    /**
     * 获取扣款凭证
     *
     * @return payment_certificate - 扣款凭证
     */
    public String getPaymentCertificate() {
        return paymentCertificate;
    }

    /**
     * 设置扣款凭证
     *
     * @param paymentCertificate 扣款凭证
     */
    public void setPaymentCertificate(String paymentCertificate) {
        this.paymentCertificate = paymentCertificate;
    }

    /**
     * 获取扣款凭证附件路径
     *
     * @return payment_certificate_path - 扣款凭证附件路径
     */
    public String getPaymentCertificatePath() {
        return paymentCertificatePath;
    }

    /**
     * 设置扣款凭证附件路径
     *
     * @param paymentCertificatePath 扣款凭证附件路径
     */
    public void setPaymentCertificatePath(String paymentCertificatePath) {
        this.paymentCertificatePath = paymentCertificatePath;
    }

    /**
     * 获取是否上传了扣款凭证
     *
     * @return is_upload_certificate - 是否上传了扣款凭证
     */
    public Integer getIsUploadCertificate() {
        return isUploadCertificate;
    }

    /**
     * 设置是否上传了扣款凭证
     *
     * @param isUploadCertificate 是否上传了扣款凭证
     */
    public void setIsUploadCertificate(Integer isUploadCertificate) {
        this.isUploadCertificate = isUploadCertificate;
    }

    /**
     * 获取其他上传的文件
     *
     * @return other_upload - 其他上传的文件
     */
    public String getOtherUpload() {
        return otherUpload;
    }

    /**
     * 设置其他上传的文件
     *
     * @param otherUpload 其他上传的文件
     */
    public void setOtherUpload(String otherUpload) {
        this.otherUpload = otherUpload;
    }

    /**
     * @return other_upload_id
     */
    public String getOtherUploadId() {
        return otherUploadId;
    }

    /**
     * @param otherUploadId
     */
    public void setOtherUploadId(String otherUploadId) {
        this.otherUploadId = otherUploadId;
    }

    public Double getActualTaxPayment() {
        return actualTaxPayment;
    }

    public void setActualTaxPayment(Double actualTaxPayment) {
        this.actualTaxPayment = actualTaxPayment;
    }

    public String getTaxReturnsPathFileName() {
        return taxReturnsPathFileName;
    }

    public void setTaxReturnsPathFileName(String taxReturnsPathFileName) {
        this.taxReturnsPathFileName = taxReturnsPathFileName;
    }

    public String getPaymentCertificateFileName() {
        return paymentCertificateFileName;
    }

    public void setPaymentCertificateFileName(String paymentCertificateFileName) {
        this.paymentCertificateFileName = paymentCertificateFileName;
    }

    public String getOtherUploadFileName() {
        return otherUploadFileName;
    }

    public void setOtherUploadFileName(String otherUploadFileName) {
        this.otherUploadFileName = otherUploadFileName;
    }

    public String getPreTaxReturnsPathFileName() {
        return preTaxReturnsPathFileName;
    }

    public void setPreTaxReturnsPathFileName(String preTaxReturnsPathFileName) {
        this.preTaxReturnsPathFileName = preTaxReturnsPathFileName;
    }
}