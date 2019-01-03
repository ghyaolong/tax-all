package com.chinasoft.tax.service;

import com.chinasoft.tax.vo.UserCompanyVo;

import java.util.List;

public interface UserCompanyService {

    /**
     * 保存用户-公司关系
     * @param userCompanyVo
     */
    void add(UserCompanyVo userCompanyVo);

    /**
     * 分配公司给用户
     * @param userId
     * @param companyIds
     */
    void assignCompany(String userId,List<String> companyIds);
}
