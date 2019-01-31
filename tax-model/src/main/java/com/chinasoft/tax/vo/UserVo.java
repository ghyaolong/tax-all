package com.chinasoft.tax.vo;

import com.chinasoft.tax.po.TDepartment;
import lombok.Data;

import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Data
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

}
