package com.chinasoft.tax.service.impl;

import com.chinasoft.tax.common.utils.IDGeneratorUtils;
import com.chinasoft.tax.common.utils.MyBeanUtils;
import com.chinasoft.tax.constant.CommonConstant;
import com.chinasoft.tax.dao.*;
import com.chinasoft.tax.enums.ExceptionCode;
import com.chinasoft.tax.po.*;
import com.chinasoft.tax.service.CompanyService;
import com.chinasoft.tax.vo.*;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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


    @Autowired
    private TUserCompanyMapper tUserCompanyMapper;

    @Autowired
    private TUserMapper tUserMapper;


    @Override
    public List<CompanyVo> getByUserId(String userId) {
        List<TCompany> tCompanyList = tCompanyMapper.getByUserId(userId);
        List<CompanyVo> companyVos = MyBeanUtils.copyList(tCompanyList, CompanyVo.class);
        return companyVos;
    }

    public List<CompanyVo> findAll(){
        List<TCompany> tCompanies = tCompanyMapper.selectAll();
        List<CompanyVo> companyVos = MyBeanUtils.copyList(tCompanies, CompanyVo.class);
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
    public MyPageInfo<CompanyVo> findByCondition(String userId,List<RoleVo> roleVo,PageVo pageVo, CompanyVo companyVo,SearchVo searchVo) {
        boolean isAdmin = false;
        for (RoleVo rVo : roleVo) {
            //如果是管理员，就显示所有公司，如果不是管理员，就显示当前用户的公司
            if(rVo.getCode().equals("ROLE_ADMINISTRATOR")){
                isAdmin = true;
                break;
            }
        }

        //companyVo.setECode("001");

        PageHelper.startPage(pageVo.getPageNumber(),pageVo.getPageSize());
        Example example = new Example(TCompany.class);
        //example.setOrderByClause("create_time desc");
        Example.Criteria criteria = example.createCriteria();

        if(companyVo!=null){
            if(!StringUtils.isEmpty(companyVo.getName())){
                criteria.orLike("name","%"+companyVo.getName().trim()+"%");
            }
            if(!StringUtils.isEmpty(companyVo.getTin())){
                criteria.orLike("tin","%"+companyVo.getTin().trim()+"%");
            }
            if(!StringUtils.isEmpty(companyVo.getCountryCode())){
                criteria.orLike("countryCode","%"+companyVo.getCountryCode()+"%");
            }
            if(companyVo.getIsAssign()!=null){
                criteria.andEqualTo("isAssign",companyVo.getIsAssign());
            }

            if(!StringUtils.isEmpty(companyVo.getECode())){
                Example userExa = new Example(TUser.class);
                userExa.createCriteria().andEqualTo("eCode",companyVo.getECode());
                List<TUser> tUsers = tUserMapper.selectByExample(userExa);
                if(!CollectionUtils.isEmpty(tUsers)){
                    String id = tUsers.get(0).getId();
                    Example tUserCompany = new Example(TUserCompany.class);
                    tUserCompany.createCriteria().andEqualTo("userId",id);
                    List<TUserCompany> tUserCompanies = tUserCompanyMapper.selectByExample(tUserCompany);
                    //StringBuffer sb = new StringBuffer();
                    for (TUserCompany userCompany : tUserCompanies) {
                        criteria.orEqualTo("id",userCompany.getCompanyId());
                        //sb.append("'").append(userCompany.getCompanyId()).append("'").append(",");
                    }
//                    if(!StringUtils.isEmpty(sb.toString())){
//                        log.info(sb.substring(0,sb.length()-1));
//                        //criteria.andCondition("id in","("+sb.substring(0,sb.length()-1)+")");
//                    }

                }else{
                    criteria.andEqualTo("id","!@$%^&*(");
                }
            }else{
                if(!isAdmin){

                    List<String> companyIds = new ArrayList<>();
                    Example ucExample = new Example(TUserCompany.class);
                    ucExample.createCriteria().andEqualTo("userId",userId);
                    List<TUserCompany> tUserCompanies = tUserCompanyMapper.selectByExample(ucExample);
                    for (TUserCompany tUserCompany : tUserCompanies) {
                        companyIds.add(tUserCompany.getCompanyId());
                    }

                    if(!CollectionUtils.isEmpty(companyIds)){
                        criteria.orIn("id",(Iterable)companyIds);
                    }
                }
            }

            if(searchVo!=null){
                if(!StringUtils.isEmpty(searchVo.getStartDate())&&!StringUtils.isEmpty(searchVo.getEndDate())){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    try {
                        criteria.andBetween("createTime",sdf.parse(searchVo.getStartDate()+"  00:00:00"),sdf.parse(searchVo.getEndDate()+" 23:59:59"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
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

            //查询该公司下分配的人
            String taxationIds = getUserOfCompany(vo.getId(),CommonConstant.USER_TAXATION);
            String reviewerIds = getUserOfCompany(vo.getId(),CommonConstant.USER_REVIEWS);
            String viewerIds = getUserOfCompany(vo.getId(),CommonConstant.USER_VIEWS);
            vo.setTaxationIds(taxationIds);
            vo.setReviewerIds(reviewerIds);
            vo.setViewerIds(viewerIds);
        }
        MyPageInfo<CompanyVo> pageInfo = new MyPageInfo<>(companyVos);
        pageInfo.setTotalElements(count);
        pageInfo.setPageNum(pageVo.getPageNumber());
        return pageInfo;

    }

    private String getUserOfCompany(String companyId,String roleCode){
        Example userComExmaple = new Example(TUserCompany.class);
        Example.Criteria criteria1 = userComExmaple.createCriteria();
        criteria1.andEqualTo("roleCode",roleCode).andEqualTo("companyId",companyId);
        List<TUserCompany> tUserCompanies = tUserCompanyMapper.selectByExample(userComExmaple);
        StringBuffer sb = new StringBuffer();
        for (TUserCompany tUserCompany : tUserCompanies) {
            sb.append(tUserCompany.getUserId()).append(",");
        }
        if(!StringUtils.isEmpty(sb.toString())){
           return sb.substring(0,sb.length()-1);
        }
        return "";
    }

    @Override
    public void add(CompanyVo companyVo) {
        log.info("保存公司，输入参数："+companyVo.toString());
        //公司名称唯一验证
        Example example = new Example(TCompany.class);
        example.createCriteria().orEqualTo("name",companyVo.getName()).orEqualTo("tin",companyVo.getTin());
        int count = tCompanyMapper.selectCountByExample(example);
        if(count>0){
            throw new BizException(ExceptionCode.DATA_AREADY_EXIST.getCode(),"公司已存在");
        }

        TCompany tCompany = MyBeanUtils.copy(companyVo, TCompany.class);
        tCompany.setId(IDGeneratorUtils.getUUID32());
        tCompany.setCreateTime(new Date());
        //tCompany.setEstablishmentTime(new Date());
        tCompany.setIsAssign(CommonConstant.COMPANY_UNASSGINED);
        tCompanyMapper.insertSelective(tCompany);


        //默认给当前公司分配所有税种
        Example dictExmaple = new Example(TDict.class);
        dictExmaple.createCriteria().andEqualTo("type", 2);
        List<TDict> tDicts = tDictMapper.selectByExample(dictExmaple);
        StringBuffer sbTaxIds = new StringBuffer();
        for (TDict tDict : tDicts) {
            sbTaxIds.append(tDict.getId()).append(",");
        }
        assignTaxes(tCompany.getId(),sbTaxIds.substring(0,sbTaxIds.length()-1));

        log.info("保存公司成功");
    }

    @Override
    public void edit(CompanyVo companyVo) {
        log.info("修改公司，输入参数："+companyVo.toString());
        //公司名称唯一验证
        TCompany tCompany1 = tCompanyMapper.selectByPrimaryKey(companyVo.getId());
        Example example = new Example(TCompany.class);
        if(!tCompany1.getName().equals(companyVo.getName())){
            if(!StringUtils.isEmpty(companyVo.getName())){

                TCompany tCompany = tCompanyMapper.selectByPrimaryKey(companyVo.getId());
                if(!tCompany.getName().equals(companyVo.getName())||!tCompany.getTin().equals(companyVo.getTin())){

                    example.createCriteria().orEqualTo("name",companyVo.getName());
                    int count = tCompanyMapper.selectCountByExample(example);
                    if(count>0){
                        throw new BizException(ExceptionCode.DATA_AREADY_EXIST.getCode(),"公司名称已存在");
                    }
                }
            }
        }else if(!tCompany1.getTin().equals(companyVo.getTin())){
            //判断税务识别号码是否唯一
            example.createCriteria().orEqualTo("tin",companyVo.getTin());
            int count = tCompanyMapper.selectCountByExample(example);
            if(count>0){
                throw new BizException(ExceptionCode.DATA_AREADY_EXIST.getCode(),"税务识别号码已存在");
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

    @Override
    public List<CompanyVo> findByUserId(String userId) {
        List<TCompany> tCompanies = tCompanyMapper.findByUserId(userId);
        List<CompanyVo> companyVos = MyBeanUtils.copyList(tCompanies, CompanyVo.class);
        return companyVos;
    }

    @Override
    public void assignUser(String companyId,String taxationIds, String reviewerIds, String viewerIds) {
        saveUserCompany(companyId, taxationIds,CommonConstant.USER_TAXATION);

        saveUserCompany(companyId, reviewerIds,CommonConstant.USER_REVIEWS);

        saveUserCompany(companyId, viewerIds,CommonConstant.USER_VIEWS);
        TCompany company = new TCompany();
        company.setId(companyId);
        if(!StringUtils.isEmpty(taxationIds)||!StringUtils.isEmpty(reviewerIds)||!StringUtils.isEmpty(viewerIds)){
            company.setIsAssign(CommonConstant.COMPANY_ASSGINED);
        }else{
            company.setIsAssign(CommonConstant.COMPANY_UNASSGINED);
        }
        tCompanyMapper.updateByPrimaryKeySelective(company);

    }

    private void saveUserCompany(String companyId, String userIds,String roleCode) {

        //通过roleCode先删除
        Example example = new Example(TUserCompany.class);
        example.createCriteria().andEqualTo("roleCode",roleCode).andEqualTo("companyId",companyId);
        tUserCompanyMapper.deleteByExample(example);
        if(!StringUtils.isEmpty(userIds)){
            String[] split = userIds.split(",");
            for (String s : split) {
                TUserCompany uc = new TUserCompany();
                uc.setId(IDGeneratorUtils.getUUID32());
                uc.setCompanyId(companyId);
                uc.setCreateTime(new Date());
                uc.setUserId(s);
                uc.setRoleCode(roleCode);
                tUserCompanyMapper.insertSelective(uc);
            }
        }
    }
}
