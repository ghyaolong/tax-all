package com.chinasoft.tax.vo;

import lombok.Data;

import java.util.Date;

@Data
public class SchedualConfVo {

    private String id;

    /**
     * 计划名称
     */
    private String name;

    /**
     * 数据库名称
     */
    private String dbName;

    /**
     * 备份路径
     */
    private String path;

    /**
     * corn表达式
     */
    private String corn;

    private Date creatTime;

    private String creator;

    private Integer isDel;

    private Date delTime;

    private String deltor;

    /**
     * 0:不可用   1：可用    2：停止   3：
     */
    private Integer status;

    /**
     * 是否只执行一次： 0：否    1：是
     */
    private Integer isOnce;

    /**
     * 需要执行的任务类
     */
    private String jobClassName;

    /**
     * 参数
     */
    private String parameter;
}
