package com.chinasoft.tax.po;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "t_application_material")
public class TApplicationMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 申请人(税务专员)
     */
    @Column(name = "application_id")
    private String applicationId;

    /**
     * 资料表id
     */
    @Column(name="material_id")
    private String materialId;

    @Column(name = "tax_application_id")
    private String taxApplicationId;

    /**
     * 资料文件附属类型0：税金申请表    1：税金申请详情表
     */
    private Integer type;

    /**
     *公司id
     */
    @Column(name = "company_id")
    private String companyId;

    /**
     *
     */
    @Column(name = "company_name")
    private String companyName;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;



}