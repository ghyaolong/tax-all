package com.chinasoft.tax.service.impl;

import com.chinasoft.tax.common.utils.MyBeanUtils;
import com.chinasoft.tax.dao.TMaterialMapper;
import com.chinasoft.tax.po.TMaterial;
import com.chinasoft.tax.service.MaterialService;
import com.chinasoft.tax.po.MaterialInfoPo;
import com.chinasoft.tax.vo.MaterialVo;
import com.chinasoft.tax.vo.MyPageInfo;
import com.chinasoft.tax.vo.PageVo;
import com.chinasoft.tax.vo.SearchVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private TMaterialMapper tMaterialMapper;

    @Override
    public MyPageInfo<MaterialInfoPo> getAllPage(PageVo pageVo, SearchVo searchVo, String companyName, String materialTypeDict) {
        PageHelper.startPage(pageVo.getPageNumber(),pageVo.getPageSize(),true);
        if(searchVo!=null){
            if(!StringUtils.isEmpty(searchVo.getStartDate())){
                searchVo.setStartDate(searchVo.getStartDate()+" 00:00:00");
            }
            if(!StringUtils.isEmpty(searchVo.getEndDate())){
                searchVo.setEndDate(searchVo.getEndDate()+" 23:59:59");
            }
            List<MaterialInfoPo> allMaterial = tMaterialMapper.findAllMaterial(companyName, materialTypeDict, searchVo.getStartDate(), searchVo.getEndDate());
            MyPageInfo<MaterialInfoPo> page = new MyPageInfo<>(allMaterial);
            if(!CollectionUtils.isEmpty(allMaterial)){
                page.setTotalElements(allMaterial.size());
                page.setPageNum(pageVo.getPageNumber());
            }
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
