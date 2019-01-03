package com.chinasoft.tax.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ApplicationMaterialVo {

    private String id;

    /**
     * 申请人(税务专员)
     */
    private String applicationId;

    /**
     * 资料表id
     */
    private String materialId;

    private String taxApplicationId;

    /**
     * 资料文件附属类型0：税金申请表    1：税金申请详情表
     */
    private Integer type;

    /**
     *公司id
     */
    private String companyId;

    /**
     *
     */
    private String companyName;

    /**
     * 创建时间
     */
    private Date createTime;
}
