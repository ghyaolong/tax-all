package com.chinasoft.tax.service;

import com.chinasoft.tax.vo.APIVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * API restful 资源类
 * @author yaochenglong
 */
public interface APIServcice {

    List<APIVo> getAllAPIVO();

    PageInfo<APIVo> getAllAPIVo(int currentPage, int pageSize);
}
