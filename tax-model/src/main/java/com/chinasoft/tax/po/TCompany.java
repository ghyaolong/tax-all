package com.chinasoft.tax.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_company")
public class TCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 公司名称
     */
    private String name;

    /**
     * 税务识别号码
     */
    @Column(name = "TIN")
    private String tin;

    /**
     * 成立日期
     */
    @Column(name = "establishment_time")
    private Date establishmentTime;

    /**
     * 所在国家
     */
    @Column(name = "country_code")
    private String countryCode;

    /**
     * 币种
     */
    @Column(name = "currency_code")
    private String currencyCode;

    /**
     * 备注
     */
    private String remarks;

    @Column(name = "create_time")
    private Date createTime;

    private String creator;

    @Column(name = "del_time")
    private Date delTime;

    private String deletor;

    @Column(name = "is_del")
    private Integer isDel;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "is_assign")
    private Integer isAssign;

    public Integer getIsAssign() {
        return isAssign;
    }

    public void setIsAssign(Integer isAssign) {
        this.isAssign = isAssign;
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
     * 获取公司名称
     *
     * @return name - 公司名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置公司名称
     *
     * @param name 公司名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取税务识别号码
     *
     * @return TIN - 税务识别号码
     */
    public String getTin() {
        return tin;
    }

    /**
     * 设置税务识别号码
     *
     * @param tin 税务识别号码
     */
    public void setTin(String tin) {
        this.tin = tin;
    }

    /**
     * 获取成立日期
     *
     * @return establishment_time - 成立日期
     */
    public Date getEstablishmentTime() {
        return establishmentTime;
    }

    /**
     * 设置成立日期
     *
     * @param establishmentTime 成立日期
     */
    public void setEstablishmentTime(Date establishmentTime) {
        this.establishmentTime = establishmentTime;
    }

    /**
     * 获取所在国家
     *
     * @return country_code - 所在国家
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * 设置所在国家
     *
     * @param countryCode 所在国家
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * 获取币种
     *
     * @return currency_code - 币种
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * 设置币种
     *
     * @param currencyCode 币种
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
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
     * @return user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
}