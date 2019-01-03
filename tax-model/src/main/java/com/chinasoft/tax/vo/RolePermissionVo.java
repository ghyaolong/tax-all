package com.chinasoft.tax.vo;

import lombok.Data;

import java.util.Date;

@Data
public class RolePermissionVo {

    private String id;

    private String createBy;

    private Date createTime;

    private Integer delFlag;

    private String updateBy;

    private Date updateTime;

    private String permissionId;

    private String roleId;
}
