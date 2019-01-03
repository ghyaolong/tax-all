package com.chinasoft.tax.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tax_config")
public class TaxConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

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
     * 税种代码
     */
    @Column(name = "tax_code")
    private String taxCode;

    /**
     * 税种名称
     */
    @Column(name = "tax_name")
    private String taxName;

    /**
     * 税种期限代码
     */
    @Column(name = "tax_period_code")
    private String taxPeriodCode;

    /**
     * 税种期限名称
     */
    @Column(name = "tax_period_name")
    private String taxPeriodName;

    private String remarks;

    @Column(name = "create_time")
    private Date createTime;

    private String creator;

    @Column(name = "is_del")
    private Integer isDel;

    @Column(name = "del_time")
    private Date delTime;

    private String deletor;

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
     * 获取税种代码
     *
     * @return tax_code - 税种代码
     */
    public String getTaxCode() {
        return taxCode;
    }

    /**
     * 设置税种代码
     *
     * @param taxCode 税种代码
     */
    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    /**
     * 获取税种名称
     *
     * @return tax_name - 税种名称
     */
    public String getTaxName() {
        return taxName;
    }

    /**
     * 设置税种名称
     *
     * @param taxName 税种名称
     */
    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    /**
     * 获取税种期限代码
     *
     * @return tax_period_code - 税种期限代码
     */
    public String getTaxPeriodCode() {
        return taxPeriodCode;
    }

    /**
     * 设置税种期限代码
     *
     * @param taxPeriodCode 税种期限代码
     */
    public void setTaxPeriodCode(String taxPeriodCode) {
        this.taxPeriodCode = taxPeriodCode;
    }

    /**
     * 获取税种期限名称
     *
     * @return tax_period_name - 税种期限名称
     */
    public String getTaxPeriodName() {
        return taxPeriodName;
    }

    /**
     * 设置税种期限名称
     *
     * @param taxPeriodName 税种期限名称
     */
    public void setTaxPeriodName(String taxPeriodName) {
        this.taxPeriodName = taxPeriodName;
    }

    /**
     * @return remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * @param creator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * @return is_del
     */
    public Integer getIsDel() {
        return isDel;
    }

    /**
     * @param isDel
     */
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    /**
     * @return del_time
     */
    public Date getDelTime() {
        return delTime;
    }

    /**
     * @param delTime
     */
    public void setDelTime(Date delTime) {
        this.delTime = delTime;
    }

    /**
     * @return deletor
     */
    public String getDeletor() {
        return deletor;
    }

    /**
     * @param deletor
     */
    public void setDeletor(String deletor) {
        this.deletor = deletor;
    }
}