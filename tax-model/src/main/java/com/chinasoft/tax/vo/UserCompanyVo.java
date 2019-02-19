package com.chinasoft.tax.vo;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class UserCompanyVo {

    private String id;

    private String userId;

    private String username;

    private String companyId;

    private String companyName;

    private String creator;

    private Date createTime;

    private Integer isDel;

    private Date delTime;

    private String deletor;

    private String roleCode;
}
