package com.chinasoft.tax.po;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "t_role")
public class TRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;

    private String code;

    @Column(name = "create_time")
    private Date createTime;

    private String creator;

    @Column(name = "is_del")
    private Integer isDel;

    @Column(name = "del_time")
    private Date delTime;

    private String deletor;

    private Integer status;
    /**
     * 是否是默认角色
     */
    @Column(name="default_role")
    private Boolean defaultRole;

    @Column(name = "process_key")
    private String processKey;

}