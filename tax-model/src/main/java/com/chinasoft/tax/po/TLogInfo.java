package com.chinasoft.tax.po;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_log_info")
public class TLogInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String username;

    @Column(name = "operation_time")
    private Date operationTime;

    /**
     * 调用的方法名称
     */
    private String method;

    /**
     * 调用的代码方法
     */
    @Column(name = "method_code")
    private String methodCode;

    /**
     * 操作之前的数据的入参数据
     */
    private String param;

    /**
     * 操作之前的数据
     */
    @Column(name = "data_before")
    private String dataBefore;

    /**
     * 操作之后的数据
     */
    @Column(name = "data_after")
    private String dataAfter;

    /**
     * 客户端ip
     */
    private String ip;

    @Column(name = "create_time")
    private Date createTime;

    private String creator;

    @Column(name = "is_del")
    private String isDel;

    @Column(name = "del_time")
    private String delTime;

    private String deletor;

    private Integer status;

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
    public void setUserName(String username) {
        this.username = username;
    }

    /**
     * @return operation_time
     */
    public Date getOperationTime() {
        return operationTime;
    }

    /**
     * @param operationTime
     */
    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    /**
     * 获取调用的方法名称
     *
     * @return method - 调用的方法名称
     */
    public String getMethod() {
        return method;
    }

    /**
     * 设置调用的方法名称
     *
     * @param method 调用的方法名称
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * 获取调用的代码方法
     *
     * @return method_code - 调用的代码方法
     */
    public String getMethodCode() {
        return methodCode;
    }

    /**
     * 设置调用的代码方法
     *
     * @param methodCode 调用的代码方法
     */
    public void setMethodCode(String methodCode) {
        this.methodCode = methodCode;
    }

    /**
     * 获取操作之前的数据的入参数据
     *
     * @return param - 操作之前的数据的入参数据
     */
    public String getParam() {
        return param;
    }

    /**
     * 设置操作之前的数据的入参数据
     *
     * @param param 操作之前的数据的入参数据
     */
    public void setParam(String param) {
        this.param = param;
    }

    /**
     * 获取操作之前的数据
     *
     * @return data_before - 操作之前的数据
     */
    public String getDataBefore() {
        return dataBefore;
    }

    /**
     * 设置操作之前的数据
     *
     * @param dataBefore 操作之前的数据
     */
    public void setDataBefore(String dataBefore) {
        this.dataBefore = dataBefore;
    }

    /**
     * 获取操作之后的数据
     *
     * @return data_after - 操作之后的数据
     */
    public String getDataAfter() {
        return dataAfter;
    }

    /**
     * 设置操作之后的数据
     *
     * @param dataAfter 操作之后的数据
     */
    public void setDataAfter(String dataAfter) {
        this.dataAfter = dataAfter;
    }

    /**
     * 获取客户端ip
     *
     * @return ip - 客户端ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置客户端ip
     *
     * @param ip 客户端ip
     */
    public void setIp(String ip) {
        this.ip = ip;
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
     * @return is_del
     */
    public String getIsDel() {
        return isDel;
    }

    /**
     * @param isDel
     */
    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    /**
     * @return del_time
     */
    public String getDelTime() {
        return delTime;
    }

    /**
     * @param delTime
     */
    public void setDelTime(String delTime) {
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

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}