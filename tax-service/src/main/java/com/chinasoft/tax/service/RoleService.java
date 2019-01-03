package com.chinasoft.tax.service;

import com.chinasoft.tax.vo.MyPageInfo;
import com.chinasoft.tax.vo.PageVo;
import com.chinasoft.tax.vo.RoleVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Description: 角色管理
 * @Date: Created in 20:02 2018/10/23
 * @Author: yaochenglong
 */

public interface RoleService {

    /**
     * 获取所有角色
     * @return
     */
    List<RoleVo> getAll();

    /**
     * 通过id查找
     * @param id
     * @return
     */
    RoleVo getById(String id);

    /**
     * 添加角色
     * @param roleVo
     */
    void addRole(RoleVo roleVo);

    /**
     * 分页查找所有角色
     * @param pageVo
     * @param vo
     * @return
     */
    MyPageInfo<RoleVo> findAllByPage(PageVo pageVo, RoleVo vo);

    /**
     * 分配菜单权限
     * @param roleId
     * @param menuIds
     */
    void assignMenu(String roleId, String menuIds);

    /**
     * 分配权限
     * @param roleId
     * @param permissionIds
     */
    void assignPermission(String roleId, String permissionIds);

    /**
     * 分配公司
     * @param roleId
     * @param companyIds
     */
    void assiginCompany(String roleId, String companyIds);

    /**
     * 分配元素
     * @param roleId
     * @param elementIds 页面元素，以逗号隔开
     */
    void assiginElement(String roleId, String elementIds);

    /**
     * 修改角色
      * @param roleVo
     */
    void editRole(RoleVo roleVo);


    /**
     * 删除角色
     * @param id
     */
    void delById(String id);

    void delRolePermission(String roleId);


}
