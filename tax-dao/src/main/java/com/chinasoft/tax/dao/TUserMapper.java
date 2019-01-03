package com.chinasoft.tax.dao;

import com.chinasoft.tax.po.TUser;
import com.chinasoft.tax.utils.MyMapper;
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
}