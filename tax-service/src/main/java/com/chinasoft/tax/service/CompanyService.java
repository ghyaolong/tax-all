package com.chinasoft.tax.service;

import com.chinasoft.tax.vo.*;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Description:公司管理服务类
 * @Date: Created in 15:18 2018/11/2
 * @Author: yaochenglong
 */
public interface CompanyService {

    /**
     * 通过userId获取公司
     * @param userId
     * @return
     */
    List<CompanyVo> getByUserId(String userId);
    List<CompanyVo> findAll();


    /**
     * 通过公司名称获取公司信息
     * @param name
     * @return
     */
    CompanyVo getByName(String name);

    /**
     * 查询所有未被分配对公司
     * @return
     */
    List<CompanyVo> getUnAssignCompany();


    /**
     * 查询所有公司
     * @return
     */
    List<CompanyVo> getAll();

    /**
     * 分页查询公司
     * @param pageVo
     * @param companyVo
     * @return
     */
    MyPageInfo<CompanyVo> findByCondition(String userid, List<RoleVo> roleVos, PageVo pageVo, CompanyVo companyVo, SearchVo searchVo);

    /**
     * 添加公司
     * @param companyVo
     */
    void add(CompanyVo companyVo);

    /**
     * 编辑公司
     * @param companyVo
     */
    void edit(CompanyVo companyVo);

    /**
     * 通过id删除公司
     * @param id
     */
    void delById(String id);

    /**
     * 分配税种
     * @param companyId
     * @param taxesIds
     */
    void assignTaxes(String companyId, String taxesIds);

    List<CompanyVo> findByUserId(String userId);

    void assignUser(String companyId,String taxationIds, String reviewerIds, String viewerIds);
}
