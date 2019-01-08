package com.chinasoft.tax.vo;

import lombok.Data;

import java.util.Date;

@Data
public class MaterialVo {
    private String id;

    private Integer materialType;


    /**
     * 税种
     */
    private String taxDict;

    /***
     * 币种
     */
    private String currency;
    /**
     * 资料类型代码，对应资料类型表的code字段
     */
    private String materialTypeDict;

    /**
     * 流程类型(0:税金申请   1：滞纳金申请)  已合并，舍弃
     */
    @Deprecated
    private Integer processType;

    /**
     * 文件名
     */
    private String oriName;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件名称后缀
     */
    private String suffix;

    /**
     * 文件存储路径
     */
    private String path;

    private Date createTime;

    private String creator;

    private Integer isDel;

    private Date delTime;

    private String deletor;

    private Integer status;

    /**
     * 文件类型  doc  ppt  xmls  jpg等等，目前不实用
     */
    private String type;
}
