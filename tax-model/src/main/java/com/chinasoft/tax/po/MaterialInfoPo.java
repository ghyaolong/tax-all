package com.chinasoft.tax.po;

import lombok.Data;

@Data
public class MaterialInfoPo {
     private String id;
     private String applicationId;

     private String companyId;

     private String companyName;

     private String createTime;

     private String taxApplicationId;

     private String oriName;

     private String fileName;

     private String path;

     private String type;

     private String materialTypeDict;
}