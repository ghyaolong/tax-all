package com.chinasoft.tax.po;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_material")
public class TMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "material_type")
    private Integer materialType;

    /**
     * 流程类型(0:税金申请   1：滞纳金申请)
     */
    @Column(name = "process_type")
    private Integer processType;

    /**
     * 文件名 - 源
     */
    @Column(name = "ori_name")
    private String oriName;

    /**
     * 文件名 - 当前
     */
    @Column(name = "file_name")
    private String fileName;

    /**
     * 文件名称后缀
     */
    private String suffix;

    /**
     * 文件存储路径
     */
    private String path;

    @Column(name = "create_time")
    private Date createTime;

    private String creator;

    @Column(name = "is_del")
    private Integer isDel;

    @Column(name = "del_time")
    private Date delTime;

    private String deletor;

    private Integer status;

    /**
     * 文件类型
     */
    private String type;

    /**
     * 资料类型代码，对应资料类型表的code字段
     */
    @Column(name = "material_type_dict")
    private String materialTypeDict;

    public String getMaterialTypeDict() {
        return materialTypeDict;
    }

    public void setMaterialTypeDict(String materialTypeDict) {
        this.materialTypeDict = materialTypeDict;
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
     * @return material_type
     */
    public Integer getMaterialType() {
        return materialType;
    }

    /**
     * @param materialType
     */
    public void setMaterialType(Integer materialType) {
        this.materialType = materialType;
    }

    /**
     * 获取流程类型(0:税金申请   1：滞纳金申请)
     *
     * @return process_type - 流程类型(0:税金申请   1：滞纳金申请)
     */
    public Integer getProcessType() {
        return processType;
    }

    /**
     * 设置流程类型(0:税金申请   1：滞纳金申请)
     *
     * @param processType 流程类型(0:税金申请   1：滞纳金申请)
     */
    public void setProcessType(Integer processType) {
        this.processType = processType;
    }

    /**
     * 获取文件名
     *
     * @return ori_name - 文件名
     */
    public String getOriName() {
        return oriName;
    }

    /**
     * 设置文件名
     *
     * @param oriName 文件名
     */
    public void setOriName(String oriName) {
        this.oriName = oriName;
    }

    /**
     * 获取文件名
     *
     * @return file_name - 文件名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置文件名
     *
     * @param fileName 文件名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 获取文件名称后缀
     *
     * @return suffix - 文件名称后缀
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * 设置文件名称后缀
     *
     * @param suffix 文件名称后缀
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    /**
     * 获取文件存储路径
     *
     * @return path - 文件存储路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置文件存储路径
     *
     * @param path 文件存储路径
     */
    public void setPath(String path) {
        this.path = path;
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
     * 获取文件类型
     *
     * @return type - 文件类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置文件类型
     *
     * @param type 文件类型
     */
    public void setType(String type) {
        this.type = type;
    }
}