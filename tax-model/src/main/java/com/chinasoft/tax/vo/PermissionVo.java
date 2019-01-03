package com.chinasoft.tax.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class PermissionVo {

    private String id;

    private String createBy;

    private Date createTime;

    private Integer delFlag;

    private String updateBy;

    private Date updateTime;

    private String description;

    private String name;

    private String parentId;

    private Integer type;

    private BigDecimal sortOrder;

    private String component;

    private String path;

    private String title;

    private String icon;

    private Integer level;

    private String buttonType;

    private Integer status;

    private String url;

    private List<PermissionVo> children;

    private List<String> permTypes;


    private Boolean expand = true;


    private Boolean checked = false;


    private Boolean selected = false;
}
