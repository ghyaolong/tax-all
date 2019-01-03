package com.chinasoft.tax.vo;

import lombok.Data;

import java.util.Date;

@Data
public class MenuVo {
    private String id;

    private String parentId;

    private String name;

    private Integer type;

    private Long sortOrder;

    private String uri;

    private String creator;

    private Date createTime;

    private Integer isDel;

    private Date delTime;

    private String deletor;

    private Integer status;

    /**
     * 前端组件
     */
    private String component;

    private Integer level;

    private String icon;
}
