package com.chinasoft.tax.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DepartmentVo {

    private String id;

    private String parentId;

    private String name;

    private String creator;

    private Date createTime;

    private Integer isDel;

    private Date delTime;

    private String deletor;

    private Integer status;

    private List<DepartmentVo> child;
}
