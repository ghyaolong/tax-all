package com.chinasoft.tax.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class DoneVo implements Serializable {

    /**
     * 流程实例Id
     */
    private String procInstId;
    /**流程编号*/
    private String flowNum;
    /**公司ID*/
    private String companyId;
    /**公司名称*/
    private String companyName;
    /**创建时间*/
    private Date createTime;
    /**创建时间*/
    private Date saveTime;
    /**当前环节*/
    private String currentLink;
    /**当前处理人*/
    private String currentHandler;
    /**用户编号*/
    private String userId;
    /**任务编号*/
    private String serialNumber;
    /**业务数据*/
    private TaxApplicationVo taxApplicationVo;
    /**审批意见*/
    private List<AuditLogVo> auditLogVoList;

}
