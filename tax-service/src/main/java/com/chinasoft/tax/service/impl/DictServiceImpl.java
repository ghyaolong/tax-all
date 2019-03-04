package com.chinasoft.tax.service.impl;

import com.chinasoft.tax.common.utils.IDGeneratorUtils;
import com.chinasoft.tax.common.utils.MyBeanUtils;
import com.chinasoft.tax.dao.TDictMapper;
import com.chinasoft.tax.enums.ExceptionCode;
import com.chinasoft.tax.po.TDict;
import com.chinasoft.tax.service.DictService;
import com.chinasoft.tax.vo.BizException;
import com.chinasoft.tax.vo.DictVo;
import com.chinasoft.tax.vo.MyPageInfo;
import com.chinasoft.tax.vo.PageVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private TDictMapper tDictMapper;

    @Override
    public MyPageInfo<DictVo> findCondition(PageVo pageVo, DictVo dictVo) {
        PageHelper.startPage(pageVo.getPageNumber(),pageVo.getPageSize(),true);
        Example example = new Example(TDict.class);
        example.setOrderByClause("create_time desc");
        Example.Criteria criteria = example.createCriteria();

        if(dictVo!=null){
            if(!StringUtils.isEmpty(dictVo.getName())){
                criteria.andLike("name","%"+dictVo.getName().trim()+"%");
            }
            if(!StringUtils.isEmpty(dictVo.getCode())){
                criteria.andLike("code","%"+dictVo.getCode().trim()+"%");
            }
            if(!StringUtils.isEmpty(dictVo.getType())){
                criteria.andLike("type","%"+dictVo.getType().toString()+"%");
            }
        }
        List<TDict> tDicts = tDictMapper.selectByExample(example);
        int count = tDictMapper.selectCountByExample(example);
        List<DictVo> dictVos = MyBeanUtils.copyList(tDicts, DictVo.class);
        MyPageInfo<DictVo> pageInfo = new MyPageInfo<>(dictVos);
        pageInfo.setTotalElements(count);
        pageInfo.setPageNum(pageVo.getPageNumber());
        return pageInfo;
    }

    @Override
    public List<DictVo> getByTypeId(Integer typeId) {
        Example example = new Example(TDict.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type",typeId);
        List<TDict> tDicts = tDictMapper.selectByExample(example);
        List<DictVo> dictVos = MyBeanUtils.copyList(tDicts, DictVo.class);
        return dictVos;
    }

    @Override
    public void add(DictVo dictVo) {
        log.info("添加字典,输入参数："+dictVo.toString());
        //字典唯一性验证
        Example example = new Example(TDict.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.orEqualTo("name",dictVo.getName());
        criteria.orEqualTo("code",dictVo.getCode());

        int count = tDictMapper.selectCountByExample(example);
        if(count>0){
            throw new BizException(ExceptionCode.DATA_AREADY_EXIST);
        }

        TDict tDict = MyBeanUtils.copy(dictVo, TDict.class);
        tDict.setId(IDGeneratorUtils.getUUID32());
        tDict.setCreateTime(new Date());
        tDictMapper.insertSelective(tDict);
        log.info("添加字典成功");
    }

    @Override
    public void edit(DictVo dictVo) {
        log.info("修改字典,输入参数："+dictVo);
        TDict tDict1 = tDictMapper.selectByPrimaryKey(dictVo.getId());
        Example example = new Example(TDict.class);
        if(!tDict1.getName().equals(dictVo.getName())){
            //字典唯一性验证
            Example.Criteria criteria = example.createCriteria();
            criteria.orEqualTo("name",dictVo.getName());
            criteria.orEqualTo("code",dictVo.getCode());
            int count = tDictMapper.selectCountByExample(example);
            if(count>0){
                throw new BizException(ExceptionCode.DATA_AREADY_EXIST.getCode(),"字典名称已存在");
            }
        }else if(!tDict1.getCode().equals(dictVo.getCode())){
            //字典唯一性验证
            Example.Criteria criteria = example.createCriteria();
            criteria.orEqualTo("code",dictVo.getCode());
            int count = tDictMapper.selectCountByExample(example);
            if(count>0){
                throw new BizException(ExceptionCode.DATA_AREADY_EXIST.getCode(),"字典代码已存在");
            }
        }

        TDict tDict = MyBeanUtils.copy(dictVo, TDict.class);
        tDictMapper.updateByPrimaryKeySelective(tDict);
        log.info("修改字典成功");
    }

    @Override
    public void del(String id) {
        log.info("删除字典，输入参数id="+id);
        tDictMapper.deleteByPrimaryKey(id);
        log.info("删除字典成功");
    }

    @Override
    public DictVo findByCode(String code) {
        Example example = new Example(TDict.class);
        example.createCriteria().andEqualTo("code",code);
        TDict tDict = tDictMapper.selectOneByExample(example);
        if(tDict!=null){
            DictVo copy = MyBeanUtils.copy(tDict, DictVo.class);
            return copy;
        }
        return null;
    }
}
