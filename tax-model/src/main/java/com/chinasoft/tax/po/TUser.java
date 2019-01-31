package com.chinasoft.tax.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_user")
public class TUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 0:女  1：男
     */
    private Integer sex;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String username;

    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 工号
     */
    @Column(name = "work_number")
    private String workNumber;

    private String tel;

    @Column(name = "roleId")
    private String roleid;

    @Column(name = "departId")
    private String departid;

    /**
     * 是否冻结0:冻结  1:正常
     */
    private Integer status;

    @Column(name = "frozen_time")
    private Date frozenTime;

    private String creator;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 0：已删除   1：未删除
     */
    @Column(name = "is_del")
    private Integer isDel;

    @Column(name = "del_time")
    private Date delTime;

    private String deletor;

    /**
     * E编码
     */
    @Column(name = "eCode")
    private String eCode;

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
     * 获取0:女  1：男
     *
     * @return sex - 0:女  1：男
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置0:女  1：男
     *
     * @param sex 0:女  1：男
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取用户名
     *
     * @return user_name - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取真实姓名
     *
     * @return real_name - 真实姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置真实姓名
     *
     * @param realName 真实姓名
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取盐
     *
     * @return salt - 盐
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 设置盐
     *
     * @param salt 盐
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取工号
     *
     * @return work_number - 工号
     */
    public String getWorkNumber() {
        return workNumber;
    }

    /**
     * 设置工号
     *
     * @param workNumber 工号
     */
    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    /**
     * @return tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return roleId
     */
    public String getRoleid() {
        return roleid;
    }

    /**
     * @param roleid
     */
    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    /**
     * @return departId
     */
    public String getDepartid() {
        return departid;
    }

    /**
     * @param departid
     */
    public void setDepartid(String departid) {
        this.departid = departid;
    }

    /**
     * 获取是否冻结0:冻结  1:正常
     *
     * @return status - 是否冻结0:冻结  1:正常
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置是否冻结0:冻结  1:正常
     *
     * @param status 是否冻结0:冻结  1:正常
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return frozen_time
     */
    public Date getFrozenTime() {
        return frozenTime;
    }

    /**
     * @param frozenTime
     */
    public void setFrozenTime(Date frozenTime) {
        this.frozenTime = frozenTime;
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
     * 获取0：已删除   1：未删除
     *
     * @return is_del - 0：已删除   1：未删除
     */
    public Integer getIsDel() {
        return isDel;
    }

    /**
     * 设置0：已删除   1：未删除
     *
     * @param isDel 0：已删除   1：未删除
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

    public String geteCode() {
        return eCode;
    }

    public void seteCode(String eCode) {
        this.eCode = eCode;
    }
}