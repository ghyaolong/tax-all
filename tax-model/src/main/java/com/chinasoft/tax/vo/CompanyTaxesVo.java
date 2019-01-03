package com.chinasoft.tax.vo;

import lombok.Data;

@Data
public class CompanyTaxesVo {

    private String id;

    private String companyId;

    private String companyName;

    /**
     * 税种id---字典表到id
     */
    private String dictId;

    private String dictName;

}
