package com.chinasoft.tax.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CompanyVo implements java.io.Serializable{

    private String id;
    /**
     * 公司名称
     */
    private String name;

    /**
     * 税务识别号码
     */
    private String tin;

    /**
     * 成立日期
     */
    private Date establishmentTime;

    /**
     * 所在国家
     */
    private String countryCode;

    /**
     * 币种
     */
    private String currencyCode;

    /**
     * 币种名称
     */
    private String currencyName;


    /**
     * 税金类型
     * ALL     实缴税金/实缴滞纳金
     * PAID    实缴税金
     * LATEFEE 实缴指南金
     */
    private String taxType;

    /**
     * 备注
     */
    private String remarks;

    private Date createTime;

    private String creator;

    private Date delTime;

    private String deletor;

    private Integer isDel;

    private String userId;

    private Integer isAssign;

    /**税种id(字典表的id)*/
    private String dictIds;

    /**税种集合*/
    private List<DictVo> dicts;
}
