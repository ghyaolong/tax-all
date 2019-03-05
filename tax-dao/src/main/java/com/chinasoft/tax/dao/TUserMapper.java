package com.chinasoft.tax.dao;

import com.chinasoft.tax.po.TUser;
import com.chinasoft.tax.utils.MyMapper;
import com.chinasoft.tax.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TUserMapper extends MyMapper<TUser> {

    /**
     * 获取当前用户的上级审核人
     * @param map
     * @return
     */
    List<TUser> getPrevReview(Map<String,String> map);

    List<TUser> findUserByKey(@Param("key") String key);

    List<TUser> findAllUserByRoleCode(@Param("roleCode") String roleCode);

    /**
     * 查询所有用户
     * @param userVo
     * @return
     */
    List<TUser> findAll(UserVo userVo);
}