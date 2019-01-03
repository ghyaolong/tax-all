package com.chinasoft.tax.po;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "t_role_element")
public class TRoleElement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "role_id")
    private String roleId;

    @Column(name = "menuId_id")
    private String menuId;

    @Column(name = "element_id")
    private String elementId;

    private String creator;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "is_del")
    private Integer isDel;

    @Column(name = "del_time")
    private Date delTime;

    private String deletor;


}