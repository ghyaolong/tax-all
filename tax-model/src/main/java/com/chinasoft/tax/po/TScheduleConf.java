package com.chinasoft.tax.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_schedule_conf")
public class TScheduleConf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 计划名称
     */
    private String name;

    /**
     * 数据库名称
     */
    @Column(name = "db_name")
    private String dbName;

    /**
     * 备份路径
     */
    private String path;

    /**
     * corn表达式
     */
    private String corn;

    @Column(name = "creat_time")
    private Date creatTime;

    private String creator;

    @Column(name = "is_del")
    private Integer isDel;

    @Column(name = "del_time")
    private Date delTime;

    private String deltor;

    /**
     * 0:不可用   1：可用    2：停止   3：
     */
    private Integer status;

    /**
     * 是否只执行一次： 0：否    1：是
     */
    @Column(name = "is_once")
    private Integer isOnce;

    /**
     * 需要执行的任务类
     */
    @Column(name = "job_class_name")
    private String jobClassName;

    /**
     * 参数
     */
    private String parameter;

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
     * 获取计划名称
     *
     * @return name - 计划名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置计划名称
     *
     * @param name 计划名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取数据库名称
     *
     * @return db_name - 数据库名称
     */
    public String getDbName() {
        return dbName;
    }

    /**
     * 设置数据库名称
     *
     * @param dbName 数据库名称
     */
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    /**
     * 获取备份路径
     *
     * @return path - 备份路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置备份路径
     *
     * @param path 备份路径
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取corn表达式
     *
     * @return corn - corn表达式
     */
    public String getCorn() {
        return corn;
    }

    /**
     * 设置corn表达式
     *
     * @param corn corn表达式
     */
    public void setCorn(String corn) {
        this.corn = corn;
    }

    /**
     * @return creat_time
     */
    public Date getCreatTime() {
        return creatTime;
    }

    /**
     * @param creatTime
     */
    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
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
    public Integer getIsDel() {
        return isDel;
    }

    /**
     * @param isDel
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
     * @return deltor
     */
    public String getDeltor() {
        return deltor;
    }

    /**
     * @param deltor
     */
    public void setDeltor(String deltor) {
        this.deltor = deltor;
    }

    /**
     * 获取0:不可用   1：可用    2：停止   3：
     *
     * @return status - 0:不可用   1：可用    2：停止   3：
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0:不可用   1：可用    2：停止   3：
     *
     * @param status 0:不可用   1：可用    2：停止   3：
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取是否只执行一次： 0：否    1：是
     *
     * @return is_once - 是否只执行一次： 0：否    1：是
     */
    public Integer getIsOnce() {
        return isOnce;
    }

    /**
     * 设置是否只执行一次： 0：否    1：是
     *
     * @param isOnce 是否只执行一次： 0：否    1：是
     */
    public void setIsOnce(Integer isOnce) {
        this.isOnce = isOnce;
    }

    /**
     * 获取需要执行的任务类
     *
     * @return job_class_name - 需要执行的任务类
     */
    public String getJobClassName() {
        return jobClassName;
    }

    /**
     * 设置需要执行的任务类
     *
     * @param jobClassName 需要执行的任务类
     */
    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }

    /**
     * 获取参数
     *
     * @return parameter - 参数
     */
    public String getParameter() {
        return parameter;
    }

    /**
     * 设置参数
     *
     * @param parameter 参数
     */
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}