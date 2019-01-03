package com.chinasoft.tax.service.impl;

import com.chinasoft.tax.common.utils.IDGeneratorUtils;
import com.chinasoft.tax.common.utils.MyBeanUtils;
import com.chinasoft.tax.dao.TQuartzJobMapper;
import com.chinasoft.tax.po.TQuartzJob;
import com.chinasoft.tax.service.SchedualService;
import com.chinasoft.tax.vo.MyPageInfo;
import com.chinasoft.tax.vo.PageVo;
import com.chinasoft.tax.vo.QuartzJobVo;
import com.chinasoft.tax.vo.SchedualConfVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class SchedualServiceImpl implements SchedualService {

    @Autowired
    private TQuartzJobMapper tQuartzJobMapper;

    @Override
    public MyPageInfo<QuartzJobVo> findAll(PageVo pageVo) {
        PageHelper.startPage(pageVo.getPageNumber(),pageVo.getPageSize(),true);
        List<TQuartzJob> tQuartzJobs = tQuartzJobMapper.selectAll();
        int count = tQuartzJobMapper.selectCountByExample(null);
        List<QuartzJobVo> quartzJobVos = MyBeanUtils.copyList(tQuartzJobs, QuartzJobVo.class);
        MyPageInfo<QuartzJobVo> page = new MyPageInfo<>(quartzJobVos);
        page.setTotalElements(count);
        page.setPageNum(pageVo.getPageNumber());
        return page;
    }

    @Override
    public List<QuartzJobVo> findByJobClassName(String jobClassName) {
        Example example = new Example(TQuartzJob.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("jobClassName",jobClassName);
        List<TQuartzJob> tQuartzJobs = tQuartzJobMapper.selectByExample(example);
        List<QuartzJobVo> quartzJobVos = MyBeanUtils.copyList(tQuartzJobs, QuartzJobVo.class);
        return quartzJobVos;
    }

    @Override
    public void add(QuartzJobVo quartzJobVo) {
        TQuartzJob tQuartzJob = MyBeanUtils.copy(quartzJobVo, TQuartzJob.class);
        tQuartzJob.setId(IDGeneratorUtils.getUUID32());
        tQuartzJob.setCreateTime(new Date());
        tQuartzJob.setCreateBy("admin");
        tQuartzJobMapper.insertSelective(tQuartzJob);
    }

    @Override
    public void edit(QuartzJobVo quartzJobVo) {
        TQuartzJob tQuartzJob = MyBeanUtils.copy(quartzJobVo, TQuartzJob.class);
        tQuartzJob.setUpdateTime(new Date());
        tQuartzJob.setUpdateBy("admin");
        tQuartzJobMapper.updateByPrimaryKeySelective(tQuartzJob);
    }


    @Override
    public void delByIds(String id) {
        tQuartzJobMapper.deleteByPrimaryKey(id);
    }
}
