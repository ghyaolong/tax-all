package com.chinasoft.tax.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;


public class UserVo {

    private String id;

    /**
     * 0:女  1：男
     */
    private Integer sex;

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 密码
     */
    private String password;

    /**
     * 确认密码
     */
    private String password2;


    /**
     * 新密码
     */
    private String newPassword;

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
    private String workNumber;

    private String tel;

    /**
     * 是否冻结0:冻结  1:正常
     */
    private Integer status;

    private Date frozenTime;

    private String creator;

    private Date createTime;

    /**
     * 0：已删除   1：未删除
     */
    private Integer isDel;

    private Date delTime;

    private String deletor;

    /**
     * 用户拥有角色
     */
    private List<RoleVo> roles;

    /**
     * 用于接收前端用户保存的角色ids
     */
    private String roleIds;

    private String departid;

    /**
     * 用户角色名称，用于在用户列表展示用户角色
     */
    private String roleNames;

    /**
     * 用户拥有的权限
     */
    private List<PermissionVo> permissions;

    /**
     * 用户拥有的部门
     */
    private List<DepartmentVo> departments;

    /**
     * 用于接收前端用户发送的部门ids
     */
    private String departmentIds;

    /**
     * 用户拥有的公司
     */
    private List<CompanyVo> companys;

    /**
     * 用于接收前端用户发送的公司ids
     */
    private String companyIds;

    /**
     * E编码
     */
    private String eCode;

    private String startDate;

    private String endDate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getFrozenTime() {
        return frozenTime;
    }

    public void setFrozenTime(Date frozenTime) {
        this.frozenTime = frozenTime;
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

    public List<RoleVo> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleVo> roles) {
        this.roles = roles;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getDepartid() {
        return departid;
    }

    public void setDepartid(String departid) {
        this.departid = departid;
    }

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    public List<PermissionVo> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionVo> permissions) {
        this.permissions = permissions;
    }

    public List<DepartmentVo> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentVo> departments) {
        this.departments = departments;
    }

    public String getDepartmentIds() {
        return departmentIds;
    }

    public void setDepartmentIds(String departmentIds) {
        this.departmentIds = departmentIds;
    }

    public List<CompanyVo> getCompanys() {
        return companys;
    }

    public void setCompanys(List<CompanyVo> companys) {
        this.companys = companys;
    }

    public String getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(String companyIds) {
        this.companyIds = companyIds;
    }

    public String getECode() {
        return eCode;
    }

    public void setECode(String eCode) {
        this.eCode = eCode;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
