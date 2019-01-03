package com.chinasoft.tax.service.impl;

import com.chinasoft.tax.common.utils.MyBeanUtils;
import com.chinasoft.tax.dao.TApiMapper;
import com.chinasoft.tax.po.TApi;
import com.chinasoft.tax.service.APIServcice;
import com.chinasoft.tax.vo.APIVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: API资源管理员
 * @Date: Created in 14:36 2018/10/22
 * @Author: yaochenglong
 */

@Service
public class APIServiceImpl implements APIServcice {

    @Autowired
    private TApiMapper tApiMapper;

    @Override
    public List<APIVo> getAllAPIVO() {
        List<TApi> tApiList = tApiMapper.selectAll();
        List<APIVo> apiVoList = MyBeanUtils.copyList(tApiList, APIVo.class);
        return apiVoList;
    }
    @Override
    public PageInfo<APIVo> getAllAPIVo(int currentPage, int pageSize){
        PageHelper.startPage(currentPage,pageSize);
        List<TApi> tApiList = tApiMapper.selectAll();
        List<APIVo> apiVoList = MyBeanUtils.copyList(tApiList, APIVo.class);
        PageInfo<APIVo> pageInfo = new PageInfo<>(apiVoList);
        return pageInfo;
    }
}
