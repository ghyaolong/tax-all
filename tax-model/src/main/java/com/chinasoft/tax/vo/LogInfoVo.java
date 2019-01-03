package com.chinasoft.tax.vo;

import lombok.Data;

import java.util.Date;

@Data
public class LogInfoVo {

    private String id;

    private String userId;

    private String username;

    private Date operationTime;

    /**
     * 调用的方法名称
     */
    private String method;

    /**
     * 调用的代码方法
     */
    private String methodCode;

    /**
     * 操作之前的数据的入参数据
     */
    private String param;

    /**
     * 操作之前的数据
     */
    private String dataBefore;

    /**
     * 操作之后的数据
     */
    private String dataAfter;

    /**
     * 客户端ip
     */
    private String ip;

    private Date createTime;

    private String creator;

    private String isDel;

    private String delTime;

    private String deletor;

    private Integer status;
}
