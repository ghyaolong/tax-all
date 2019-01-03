package com.chinasoft.tax.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_dict")
public class TDict {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;

    private String code;

    /**
     * 0:国家，1：币种    2：税种  3：纳税期限表
     */
    private Integer type;

    private String remarks;

    private String creator;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "is_del")
    private Integer isDel;

    @Column(name = "del_time")
    private Date delTime;

    private String deletor;

    private Integer status;

    /**
     * 排序用，数字越小月靠前
     */
    @Column(name = "sort_order")
    private Long sortOrder;

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取0:国家，1：币种    2：税种  3：纳税期限表
     *
     * @return type - 0:国家，1：币种    2：税种  3：纳税期限表
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置0:国家，1：币种    2：税种  3：纳税期限表
     *
     * @param type 0:国家，1：币种    2：税种  3：纳税期限表
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    /**
     * 获取排序用，数字越小月靠前
     *
     * @return sort_order - 排序用，数字越小月靠前
     */
    public Long getSortOrder() {
        return sortOrder;
    }

    /**
     * 设置排序用，数字越小月靠前
     *
     * @param sortOrder 排序用，数字越小月靠前
     */
    public void setSortOrder(Long sortOrder) {
        this.sortOrder = sortOrder;
    }
}