package com.chinasoft.tax.vo;

import lombok.Data;

import java.util.Date;

@Data
public class DictVo {

    private String id;

    private String name;

    private String code;

    /**
     * 0:国家，1：币种    2：税种  3：纳税期限表
     */
    private Integer type;

    private String remarks;

    private String creator;

    private Date createTime;

    private Integer isDel;

    private Date delTime;

    private String deletor;

    private Integer status;
}
