package com.chinasoft.tax.service.impl;

import com.chinasoft.tax.common.utils.MyBeanUtils;
import com.chinasoft.tax.dao.TMaterialMapper;
import com.chinasoft.tax.po.TMaterial;
import com.chinasoft.tax.service.MaterialService;
import com.chinasoft.tax.po.MaterialInfoPo;
import com.chinasoft.tax.vo.MaterialVo;
import com.chinasoft.tax.vo.PageVo;
import com.chinasoft.tax.vo.SearchVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private TMaterialMapper tMaterialMapper;

    @Override
    public PageInfo<MaterialInfoPo> getAllPage(PageVo pageVo, SearchVo searchVo, String companyName, String materialTypeDict) {
        PageHelper.startPage(pageVo.getPageNumber(),pageVo.getPageSize(),true);
        if(searchVo!=null){
            List<MaterialInfoPo> allMaterial = tMaterialMapper.findAllMaterial(companyName, materialTypeDict, searchVo.getStartDate(), searchVo.getEndDate());
            PageInfo<MaterialInfoPo> page = new PageInfo<>(allMaterial);
            return page;
        }
        return null;
    }

    @Override
    public void add(MaterialVo materialVo) {
        log.info("保存资料文件,输入参数："+materialVo.toString());
        TMaterial tMaterial = MyBeanUtils.copy(materialVo, TMaterial.class);
        tMaterial.setCreateTime(new Date());
        tMaterialMapper.insertSelective(tMaterial);
        log.info("保存资料文件成功");
    }

    @Override
    public MaterialVo findById(String id) {
        TMaterial tMaterial = tMaterialMapper.selectByPrimaryKey(id);
        MaterialVo materialVo = MyBeanUtils.copy(tMaterial, MaterialVo.class);
        return materialVo;
    }
}
