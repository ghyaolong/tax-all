package com.chinasoft.tax.dao;

import com.chinasoft.tax.po.TPermission;
import com.chinasoft.tax.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TPermissionMapper extends MyMapper<TPermission> {

    /**
     * 通过用户id获取该用户所拥有的权限
     * @param userId
     * @return
     */
    List<TPermission> findByUserId(String userId);

    /**
     * 通过用户id查询改用户id所拥有的权限
     * 查询rolePermission中间表
     * @param roleId
     * @return
     */
    List<TPermission> findByRoleId(String roleId);
}