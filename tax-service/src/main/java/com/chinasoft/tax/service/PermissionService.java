package com.chinasoft.tax.service;

import com.chinasoft.tax.vo.PermissionVo;

import java.util.List;

/**
 * @Description: 权限服务
 * @Date: Created in 20:22 2018/10/25
 * @Author: yaochenglong
 */

public interface PermissionService {

    /**
     * 通过id查找权限
     * @param id
     * @return
     */
    PermissionVo findById(String id);

    /**
     * 通过userId查找该用户所拥有的权限
     * @param userId
     * @return
     */
    List<PermissionVo> findByUserId(String userId);
    /**
     *
     * @param permissionVo
     */
    void addPermission(PermissionVo permissionVo);

    /**
     *
     * @param id
     */
    void delPermissionById(String id);

    /**
     * 修改权限
     * @param permissionVo
     */
    void editPermission(PermissionVo permissionVo);


    /**
     * 通过层级查找
     * 默认升序
     * @param level
     * @return
     */
    List<PermissionVo> findByLevelOrderBySortOrder(Integer level);

    /**
     * 通过parendId查找
     * @param parentId
     * @return
     */
    List<PermissionVo> findByParentIdOrderBySortOrder(String parentId);

    /**
     * 通过类型和状态获取
     * @param type
     * @param status
     * @return
     */
    List<PermissionVo> findByTypeAndStatusOrderBySortOrder(Integer type, Integer status);

    /**
     * 通过名称获取
     * @param title
     * @return
     */
    List<PermissionVo> findByTitle(String title);
}
