package com.chinasoft.tax.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class AuditLogVo implements Serializable {

    private String id;

    /**
     * 任务
     */
    @Excel(name = "任务")
    private String taskName;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 角色名称
     */
    @Excel(name = "角色名称")
    private String roleName;

    /**
     * 名称
     */
    @Excel(name = "姓名")
    private String name;

    /**
     * 审核结果
     */
    @Excel(name = "结论")
    private String auditResult;

    /**
     * 意见
     */
    @Excel(name = "意见")
    private String advice;

    /**
     * 审批时间
     */
    private Date auditDate;

    /**流水号*/
    private String flowNum;

    /**
     * 审批意见
     */
    private String message;

    /**
     * 操作节点编号
     */
    private String taskDefKey;

    public void setMessage(String message) {
        if (!StringUtils.isEmpty(message)) {
            String[] messageSplit = message.split(",");
            if (messageSplit.length >= 2) {
                auditResult = messageSplit[0];
                advice = messageSplit[1];
            }
        }
    }

    @Excel(name = "审批时间")
    private String poiAuditDate;

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
        poiAuditDate = getDate(auditDate);
    }

    /**
     * 日期格式
     */
    public final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 获取String格式的日期信息（yyyy-MM-dd）
     *
     * @param date
     * @return yyyy-MM-dd
     */
    private static String getDate(Date date) {
        if (date == null) {
            return "";
        } else {
            return DATE_FORMAT.format(date);
        }
    }
}
