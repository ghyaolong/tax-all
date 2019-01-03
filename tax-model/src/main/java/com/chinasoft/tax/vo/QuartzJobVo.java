package com.chinasoft.tax.vo;

import lombok.Data;

import java.util.Date;

@Data
public class QuartzJobVo {

    private String id;

    private String createBy;

    private Date createTime;

    private Integer delFlag;

    private String updateBy;

    private Date updateTime;

    private String cronExpression;

    private String description;

    private String jobClassName;

    private String parameter;

    private Integer status;
}
