package com.chinasoft.tax.po;

import lombok.Data;

import javax.persistence.Column;


@Data
public class MaterialInfoPo {
     private String id;
     private String applicationId;

     private String companyId;

     private String companyName;

     /***
      * 税种
      */
     private String taxDict;

     private String taxDictName;

     /**
      * 币种
      */
     private String currency;

     private String currencyName;

     private String createTime;

     private String taxApplicationId;

     private String oriName;

     private String fileName;

     private String path;

     private String type;

     private String materialTypeDict;

     private String detailId;
}
