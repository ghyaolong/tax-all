package com.chinasoft.tax.service.impl;

import com.chinasoft.tax.common.utils.IDGeneratorUtils;
import com.chinasoft.tax.common.utils.MyBeanUtils;
import com.chinasoft.tax.constant.CommonConstant;
import com.chinasoft.tax.dao.TCompanyMapper;
import com.chinasoft.tax.dao.TCompanyTaxesMapper;
import com.chinasoft.tax.dao.TDictMapper;
import com.chinasoft.tax.enums.ExceptionCode;
import com.chinasoft.tax.po.TCompany;
import com.chinasoft.tax.po.TCompanyTaxes;
import com.chinasoft.tax.po.TDict;
import com.chinasoft.tax.service.CompanyService;
import com.chinasoft.tax.vo.*;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private TCompanyMapper tCompanyMapper;

    @Autowired
    private TDictMapper tDictMapper;

    @Autowired
    private TCompanyTaxesMapper tCompanyTaxesMapper;


    @Override
    public List<CompanyVo> getByUserId(String userId) {
        List<TCompany> tCompanyList = tCompanyMapper.getByUserId(userId);
        List<CompanyVo> companyVos = MyBeanUtils.copyList(tCompanyList, CompanyVo.class);


        return companyVos;
    }

    @Override
    public CompanyVo getByName(String name) {
        Example example = new Example(TCompany.class);
        example.createCriteria().andEqualTo("name",name);
        //example.createCriteria().andLike("name","%"+name+"%");
        List<TCompany> tCompanies = tCompanyMapper.selectByExample(example);
        List<CompanyVo> companyVos = MyBeanUtils.copyList(tCompanies, CompanyVo.class);
        if(CollectionUtils.isEmpty(companyVos)){
            throw new BizException(ExceptionCode.NULL_DATA);
        }else{
            CompanyVo companyVo = companyVos.get(0);
            if(!StringUtils.isEmpty(companyVo.getName())){
                Example dictExm = new Example(TDict.class);
                dictExm.createCriteria().andEqualTo("code",companyVo.getCurrencyCode()).andEqualTo("type",1);
                try {
                    TDict tDict = tDictMapper.selectOneByExample(dictExm);
                    companyVo.setCurrencyName(tDict.getName());
                }catch (Exception e){
                    log.error("查询字典错误",e);
                    throw new BizException(ExceptionCode.UNKNOWN_ERROR.getCode(),"字典数据不全");
                }
            }

            List<TDict> tDicts = tCompanyTaxesMapper.findByCompanyId(companyVo.getId());
            List<DictVo> dictVos = MyBeanUtils.copyList(tDicts, DictVo.class);
            companyVo.setDicts(dictVos);
            return companyVo;
        }
    }

    @Override
    public List<CompanyVo> getUnAssignCompany() {

        Example example = new Example(TCompany.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("isAssign",CommonConstant.COMPANY_UNASSGINED);
        List<TCompany> tCompanies = tCompanyMapper.selectByExample(example);
        List<CompanyVo> companyVos = MyBeanUtils.copyList(tCompanies, CompanyVo.class);
        return companyVos;
    }

    @Override
    public List<CompanyVo> getAll() {
        Example example= new Example(TCompany.class);
        example.setOrderByClause("create_time desc");
        List<TCompany> tCompanies = tCompanyMapper.selectByExample(example);
        List<CompanyVo> companyVos = MyBeanUtils.copyList(tCompanies, CompanyVo.class);
        return companyVos;
    }

    @Override
    public MyPageInfo<CompanyVo> findByCondition(PageVo pageVo, CompanyVo companyVo) {
        PageHelper.startPage(pageVo.getPageNumber(),pageVo.getPageSize());
        Example example = new Example(TCompany.class);
        //example.setOrderByClause("create_time desc");
        Example.Criteria criteria = example.createCriteria();

        if(companyVo!=null){
            if(!StringUtils.isEmpty(companyVo.getName())){
                criteria.orLike("name","%"+companyVo.getName()+"%");
            }
            if(!StringUtils.isEmpty(companyVo.getTin())){
                criteria.orLike("tin","%"+companyVo.getTin()+"%");
            }
            if(!StringUtils.isEmpty(companyVo.getCountryCode())){
                criteria.orLike("countryCode","%"+companyVo.getCountryCode()+"%");
            }
            if(companyVo.getIsAssign()!=null){
                criteria.andEqualTo("isAssign",companyVo.getIsAssign());
            }
        }
        List<TCompany> tCompaniesList = tCompanyMapper.selectByExample(example);
        int count = tCompanyMapper.selectCountByExample(example);
        List<CompanyVo> companyVos = MyBeanUtils.copyList(tCompaniesList, CompanyVo.class);
        //查询公司下所有的税种
        for (CompanyVo vo : companyVos) {
            List<TDict> tDicts = tCompanyTaxesMapper.findByCompanyId(vo.getId());
            List<DictVo> dictVos = MyBeanUtils.copyList(tDicts, DictVo.class);
            vo.setDicts(dictVos);
        }
        MyPageInfo<CompanyVo> pageInfo = new MyPageInfo<>(companyVos);
        pageInfo.setTotalElements(count);
        pageInfo.setPageNum(pageVo.getPageNumber());
        return pageInfo;
    }

    @Override
    public void add(CompanyVo companyVo) {
        log.info("保存公司，输入参数："+companyVo.toString());
        //公司名称唯一验证
        Example example = new Example(TCompany.class);
        example.createCriteria().andEqualTo("name",companyVo.getName());
        int count = tCompanyMapper.selectCountByExample(example);
        if(count>0){
            throw new BizException(ExceptionCode.DATA_AREADY_EXIST);
        }

        TCompany tCompany = MyBeanUtils.copy(companyVo, TCompany.class);
        tCompany.setId(IDGeneratorUtils.getUUID32());
        tCompany.setCreateTime(new Date());
        tCompany.setIsAssign(CommonConstant.COMPANY_UNASSGINED);
        tCompanyMapper.insertSelective(tCompany);
        log.info("保存公司成功");
    }

    @Override
    public void edit(CompanyVo companyVo) {
        log.info("修改公司，输入参数："+companyVo.toString());
        //公司名称唯一验证
        if(!StringUtils.isEmpty(companyVo.getName())){
            TCompany tCompany = tCompanyMapper.selectByPrimaryKey(companyVo.getId());
            if(!tCompany.getName().equals(companyVo.getName())||!tCompany.getTin().equals(companyVo.getTin())){
                Example example = new Example(TCompany.class);
                example.createCriteria().orEqualTo("name",companyVo.getName()).orEqualTo("tin",companyVo.getTin());
                int count = tCompanyMapper.selectCountByExample(example);
                if(count>0){
                    throw new BizException(ExceptionCode.DATA_AREADY_EXIST.getCode(),"公司名称或税务识别号码已存在");
                }
            }
        }

        TCompany tCompany = MyBeanUtils.copy(companyVo, TCompany.class);
        tCompanyMapper.updateByPrimaryKeySelective(tCompany);
        log.info("修改公司成功");

    }

//    private void saveCompanyTaxes(CompanyVo companyVo, TCompany tCompany) {
//        //保存税种
//        String dictIds = companyVo.getDictIds();
//        String[] split = dictIds.split(",");
//        for (String s : split) {
//            TCompanyTaxes tt = new TCompanyTaxes();
//            tt.setId(IDGeneratorUtils.getUUID32());
//            tt.setCompanyId(tCompany.getId());
//            tt.setDictId(s);
//            tt.setCompanyName(tCompany.getName());
//            tCompanyTaxesMapper.insertSelective(tt);
//        }
//    }

    @Override
    public void delById(String id) {
        log.info("删除公司，输入参数id="+id);
        tCompanyMapper.deleteByPrimaryKey(id);
        Example companyTaxesEmp = new Example(TCompanyTaxes.class);
        companyTaxesEmp.createCriteria().andEqualTo("companyId",id);
        tCompanyTaxesMapper.deleteByExample(companyTaxesEmp);
        log.info("删除公司成功");
    }

    @Override
    public void assignTaxes(String companyId, String taxesIds) {
        log.info("分配税种给公司,入参：companyId="+companyId+",taxesIds="+taxesIds);
        Example companyTaxesEmp = new Example(TCompanyTaxes.class);
        companyTaxesEmp.createCriteria().andEqualTo("companyId",companyId);
        tCompanyTaxesMapper.deleteByExample(companyTaxesEmp);
        //重新分配税种
        //保存税种
        String[] split = taxesIds.split(",");
        for (String s : split) {
            TCompanyTaxes tt = new TCompanyTaxes();
            tt.setId(IDGeneratorUtils.getUUID32());
            tt.setCompanyId(companyId);
            tt.setDictId(s);
            tCompanyTaxesMapper.insertSelective(tt);
        }
        log.info("分配税种成功");
    }
}
