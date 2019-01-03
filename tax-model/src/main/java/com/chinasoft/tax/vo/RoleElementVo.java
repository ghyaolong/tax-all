package com.chinasoft.tax.vo;

import lombok.Data;

import java.util.Date;

@Data
public class RoleElementVo {

    private String id;

    private String roleId;

    private String menuId;

    private String elementId;

    private String creator;

    private Date createTime;

    private Integer isDel;

    private Date delTime;

    private String deletor;
}
