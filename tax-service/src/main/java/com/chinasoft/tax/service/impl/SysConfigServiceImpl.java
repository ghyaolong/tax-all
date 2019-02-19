package com.chinasoft.tax.service.impl;

import com.chinasoft.tax.common.utils.MyBeanUtils;
import com.chinasoft.tax.dao.TSysConfigMapper;
import com.chinasoft.tax.po.TSysConfig;
import com.chinasoft.tax.service.SysConfigService;
import com.chinasoft.tax.vo.SysConfigVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class SysConfigServiceImpl implements SysConfigService {

    @Autowired
    private TSysConfigMapper tSysConfigMapper;

    @Override
    public void editConfig(SysConfigVo sysConfigVo) {
        TSysConfig tSysConfig = MyBeanUtils.copy(sysConfigVo, TSysConfig.class);
        Example example = new Example(TSysConfig.class);
        tSysConfigMapper.updateByPrimaryKeySelective(tSysConfig);
    }

    @Override
    public SysConfigVo getMsgByKey(String property) {
        Example example = new Example(TSysConfig.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("property",property);
        TSysConfig tSysConfig = tSysConfigMapper.selectOneByExample(example);
        SysConfigVo sysConfigVo = MyBeanUtils.copy(tSysConfig, SysConfigVo.class);
        return sysConfigVo;
    }

    @Override
    public List<SysConfigVo> findAll() {
        List<TSysConfig> tSysConfigs = tSysConfigMapper.selectAll();
        List<SysConfigVo> sysConfigVos = MyBeanUtils.copyList(tSysConfigs, SysConfigVo.class);
        return sysConfigVos;
    }
}
