package com.chinasoft.tax.vo;

import com.chinasoft.tax.po.TPermission;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RoleVo {

    private String id;

    private String name;

    private String code;

    private Date createTime;

    private String creator;

    private Integer isDel;

    private Date delTime;

    private String deletor;

    private Integer status;

    private Boolean defaultRole;

    private List<PermissionVo> permissions;

    /**流程角色，这里指流程id，来自<code>com.chinasoft.tax.enums.TaskCode</code>*/
    private String processKey;
}
