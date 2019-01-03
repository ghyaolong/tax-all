package com.chinasoft.tax.vo;

import lombok.Data;

import java.util.Date;

/**
 * API VO
 */
@Data
public class APIVo {

    private String id;

    private String apiName;

    private String apiUri;

    private String creator;

    private Date createTime;

    private Integer isDel;

    private Date delTime;

    private String deletor;

    private Integer status;
}
