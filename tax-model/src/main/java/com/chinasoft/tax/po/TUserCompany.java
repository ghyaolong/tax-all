package com.chinasoft.tax.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_user_company")
public class TUserCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String username;

    @Column(name = "company_id")
    private String companyId;

    @Column(name = "company_name")
    private String companyName;

    private String creator;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "is_del")
    private Integer isDel;

    @Column(name = "del_time")
    private Date delTime;

    private String deletor;

    @Column(name = "role_code")
    private String roleCode;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
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

    /**
     * @return user_name
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return company_id
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * @param companyId
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    /**
     * @return company_name
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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