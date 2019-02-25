package com.chinasoft.tax.po;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "t_tax_application")
public class TTaxApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 业务流水号
     */
    private String businessFlowNumber;

    /**
     * 公司id
     */
    @Column(name = "company_id")
    private String companyId;

    /**
     * 公司名称
     */
    @Column(name = "company_name")
    private String companyName;

    /**
     * 税务识别码
     */
    @Column(name = "TIN")
    private String tin;

    /**
     * 国家代码
     */
    @Column(name = "country_code")
    private String countryCode;

    /**
     * 国家名称
     */
    @Column(name = "country_name")
    private String countryName;

    /**
     * 申请人id
     */
    @Column(name = "applicant_id")
    private String applicantId;

    /**
     * 申请人
     */
    @Column(name = "applicant_name")
    private String applicantName;

    /**
     * 备注
     */
    private String remarks;

    private Integer status;

    /**
     * 币种
     */
    private String currency;

    /**
     * 财务报表，文件表的外键id
     */
    @Column(name = "financial_report")
    private String financialReport;

    /**
     * 财务报表路径
     */
    @Column(name = "financial_report_path")
    private String financialReportPath;

    /**
     * 财务报表附件名称
     */
    @Column(name="financial_report_name")
    private String oriName;

    /**
     * 是否上传了财务报表
     */
    @Column(name = "is_upload_report")
    private Integer isUploadReport;

    private String creator;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "save_time")
    private Date saveTime;

    @Column(name = "is_del")
    private Integer isDel;

    @Column(name = "del_time")
    private Date delTime;

    private String deletor;

    /**
     * 流水号
     */
    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "current_link")
    private String currentLink;

    @Column(name = "current_handler")
    private String currentHandler;

    private List<TTaxApplicationDetail> details;

    public String getFinancialReportPath() {
        return financialReportPath;
    }

    public void setFinancialReportPath(String financialReportPath) {
        this.financialReportPath = financialReportPath;
    }

    public String getCurrentLink() {
        return currentLink;
    }

    public void setCurrentLink(String currentLink) {
        this.currentLink = currentLink;
    }

    public String getCurrentHandler() {
        return currentHandler;
    }

    public void setCurrentHandler(String currentHandler) {
        this.currentHandler = currentHandler;
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
     * 获取公司id
     *
     * @return company_id - 公司id
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * 设置公司id
     *
     * @param companyId 公司id
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取公司名称
     *
     * @return company_name - 公司名称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置公司名称
     *
     * @param companyName 公司名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 获取税务识别码
     *
     * @return TIN - 税务识别码
     */
    public String getTin() {
        return tin;
    }

    /**
     * 设置税务识别码
     *
     * @param tin 税务识别码
     */
    public void setTin(String tin) {
        this.tin = tin;
    }

    /**
     * 获取国家代码
     *
     * @return country_code - 国家代码
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * 设置国家代码
     *
     * @param countryCode 国家代码
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * 获取国家名称
     *
     * @return country_name - 国家名称
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * 设置国家名称
     *
     * @param countryName 国家名称
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * 获取申请人id
     *
     * @return applicant_id - 申请人id
     */
    public String getApplicantId() {
        return applicantId;
    }

    /**
     * 设置申请人id
     *
     * @param applicantId 申请人id
     */
    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    /**
     * 获取申请人
     *
     * @return applicant_name - 申请人
     */
    public String getApplicantName() {
        return applicantName;
    }

    /**
     * 设置申请人
     *
     * @param applicantName 申请人
     */
    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
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
     * 获取币种
     *
     * @return currency - 币种
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * 设置币种
     *
     * @param currency 币种
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * 获取财务报表，文件表的外键id
     *
     * @return financial_report - 财务报表，文件表的外键id
     */
    public String getFinancialReport() {
        return financialReport;
    }

    /**
     * 设置财务报表，文件表的外键id
     *
     * @param financialReport 财务报表，文件表的外键id
     */
    public void setFinancialReport(String financialReport) {
        this.financialReport = financialReport;
    }

    /**
     * 获取是否上传了财务报表
     *
     * @return is_upload_report - 是否上传了财务报表
     */
    public Integer getIsUploadReport() {
        return isUploadReport;
    }

    /**
     * 设置是否上传了财务报表
     *
     * @param isUploadReport 是否上传了财务报表
     */
    public void setIsUploadReport(Integer isUploadReport) {
        this.isUploadReport = isUploadReport;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(Date saveTime) {
        this.saveTime = saveTime;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Date getDelTime() {
        return delTime;
    }

    public void setDelTime(Date delTime) {
        this.delTime = delTime;
    }

    public String getDeletor() {
        return deletor;
    }

    public void setDeletor(String deletor) {
        this.deletor = deletor;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getOriName() {
        return oriName;
    }

    public void setOriName(String oriName) {
        this.oriName = oriName;
    }

    public String getBusinessFlowNumber() {
        return businessFlowNumber;
    }

    public void setBusinessFlowNumber(String businessFlowNumber) {
        this.businessFlowNumber = businessFlowNumber;
    }

    public List<TTaxApplicationDetail> getDetails() {
        return details;
    }

    public void setDetails(List<TTaxApplicationDetail> details) {
        this.details = details;
    }
}