package com.chinasoft.tax.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_audit_log")
public class TAuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 任务
     */
    @Column(name = "task_name")
    private String taskName;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 名称
     */
    private String name;

    /**
     * 审核结果
     */
    @Column(name = "audit_result")
    private String auditResult;

    /**
     * 意见
     */
    private String advice;

    /**
     * 审批时间
     */
    @Column(name = "audit_date")
    private Date auditDate;

    /**流水号*/
    private String flowNum;

    public String getFlowNum() {
        return flowNum;
    }

    public void setFlowNum(String flowNum) {
        this.flowNum = flowNum;
    }

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
     * 获取任务
     *
     * @return task_name - 任务
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * 设置任务
     *
     * @param taskName 任务
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * 获取角色名称
     *
     * @return role_name - 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取审核结果
     *
     * @return audit_result - 审核结果
     */
    public String getAuditResult() {
        return auditResult;
    }

    /**
     * 设置审核结果
     *
     * @param auditResult 审核结果
     */
    public void setAuditResult(String auditResult) {
        this.auditResult = auditResult;
    }

    /**
     * 获取意见
     *
     * @return advice - 意见
     */
    public String getAdvice() {
        return advice;
    }

    /**
     * 设置意见
     *
     * @param advice 意见
     */
    public void setAdvice(String advice) {
        this.advice = advice;
    }

    /**
     * 获取审批时间
     *
     * @return audit_date - 审批时间
     */
    public Date getAuditDate() {
        return auditDate;
    }

    /**
     * 设置审批时间
     *
     * @param auditDate 审批时间
     */
    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }
}