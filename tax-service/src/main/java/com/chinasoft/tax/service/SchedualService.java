package com.chinasoft.tax.service;


import com.chinasoft.tax.vo.MyPageInfo;
import com.chinasoft.tax.vo.PageVo;
import com.chinasoft.tax.vo.QuartzJobVo;
import com.chinasoft.tax.vo.SchedualConfVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface SchedualService {

    MyPageInfo<QuartzJobVo> findAll(PageVo pageVo);

    /**
     * 通过类名获取
     *
     * @param jobClassName
     * @return
     */
    List<QuartzJobVo> findByJobClassName(String jobClassName);

    /**
     * 添加定时任务
     */
    void add(QuartzJobVo quartzJobVo);

    /**
     * 编辑任务
     *
     * @param quartzJobVo
     */
    void edit(QuartzJobVo quartzJobVo);

    /**
     * 删除任务
     *
     * @param id
     */
    void delByIds(String id);

}
