package com.chinasoft.tax.service.impl;

import com.chinasoft.tax.common.utils.IDGeneratorUtils;
import com.chinasoft.tax.constant.CommonConstant;
import com.chinasoft.tax.dao.TCompanyMapper;
import com.chinasoft.tax.dao.TUserCompanyMapper;
import com.chinasoft.tax.po.TCompany;
import com.chinasoft.tax.po.TUserCompany;
import com.chinasoft.tax.service.UserCompanyService;
import com.chinasoft.tax.vo.UserCompanyVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Slf4j
@Service
public class UserCompanyServiceImpl implements UserCompanyService {

    @Autowired
    private TUserCompanyMapper tUserCompanyMapper;

    @Autowired
    private TCompanyMapper tCompanyMapper;

    @Override
    public void add(UserCompanyVo userCompanyVo) {
        TUserCompany tUserCompany = new TUserCompany();
        BeanUtils.copyProperties(userCompanyVo,tUserCompany);
        tUserCompanyMapper.insert(tUserCompany);
        log.debug("添加用户-公司成功，结果:"+tUserCompany);
    }

    @Override
    public void assignCompany(String userId, List<String> companyIds) {
        deleteByUserIds(userId);
        for (String companyId : companyIds) {
            TUserCompany tUserCompany = new TUserCompany();
            tUserCompany.setId(IDGeneratorUtils.getUUID32());
            tUserCompany.setUserId(userId);
            tUserCompany.setCompanyId(companyId);
            tUserCompanyMapper.insertSelective(tUserCompany);

            TCompany company = new TCompany();
            company.setId(companyId);
            company.setIsAssign(CommonConstant.COMPANY_ASSGINED);
            tCompanyMapper.updateByPrimaryKey(company);
        }
    }

    /**
     *
     * @param userId
     */
    private void deleteByUserIds(String userId){
        Example example = new Example(TUserCompany.class);
        example.createCriteria().andEqualTo("userId",userId);
        tUserCompanyMapper.deleteByExample(example);
    }
}
