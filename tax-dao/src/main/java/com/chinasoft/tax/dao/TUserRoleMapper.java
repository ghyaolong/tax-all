package com.chinasoft.tax.dao;

import com.chinasoft.tax.po.TRole;
import com.chinasoft.tax.po.TUser;
import com.chinasoft.tax.po.TUserRole;
import com.chinasoft.tax.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TUserRoleMapper extends MyMapper<TUserRole> {

    /**
     * 获取userId用户所拥有的权限
     * @param userId
     * @return
     */
    List<TRole> findByUserId(String userId);

    /**
     * 通过用户id和流程key获取角色
     * @param userId
     * @param key
     * @return
     */
    TRole findByUserIdAndKey(@Param("userId") String userId, @Param("key") String key);

}