package com.chinasoft.tax.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
public class TaxApplicationVo implements Serializable {

    private String id;

    /**
     * 公司id
     */
    private String companyId;

    /**
     * 公司名称
     */
    @Excel(name = "姓名")
    private String companyName;

    /**
     * 税务识别码
     */
    @Excel(name = "税务识别号码")
    private String tin;

    /**
     * 国家代码
     */
    private String countryCode;

    /**
     * 国家名称
     */
    @Excel(name="国家")
    private String countryName;

    /**
     * 申请人id
     */
    private String applicantId;

    /**
     * 申请人
     */

    @Excel(name="申请人")
    private String applicantName;

    /**
     * 备注
     */
    private String remarks;

    private Integer status;

    /**
     * 币种
     */
    private String currency;

    /**
     * 财务报表，文件表的外键id
     */
    private String financialReport;

    /**
     * 财务报表字段路径
     */
    @Excel(name="财务报表")
    private String financialReportPath;

    /**
     * 是否上传了财务报表
     */
    private Integer isUploadReport;

    /**
     * 税金申请详情
     */
    private List<TaxApplicationDetailVo> details;

    private String creator;

    private Date createTime;

    private Date saveTime;

    private Integer isDel;

    private Date delTime;

    private String deletor;

    /**
     * 操作类型 0：保存   1提交
     */
    private Integer executeType;

    /**
     * 流程编号
     */
    private String serialNumber;

    /**
     * 实例编号
     */
    private String processInstanceId;

    /**当前环节*/
    private String currentLink;

    /**当前处理人*/
    private String currentHandler;

    /**审批记录*/
    private List<AuditLogVo> auditLogVoList;

    /**
     * 资源编号
     */
    private String deploymentId;

    /**
     * 资源地址
     */
    private String filePath;
}
