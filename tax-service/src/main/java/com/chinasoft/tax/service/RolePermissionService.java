package com.chinasoft.tax.service;

import com.chinasoft.tax.vo.RolePermissionVo;

import java.util.List;

/**
 * @Description: 角色权限服务类
 * @Date: Created in 20:43 2018/10/25
 * @Author: yaochenglong
 */
public interface RolePermissionService {
    /**
     * 通过permissionId获取
     * @param permissionId
     * @return
     */
    List<RolePermissionVo> findByPermissionId(String permissionId);

    /**
     * 通过roleId删除
     * @param roleId
     */
    void deleteByRoleId(String roleId);
}
