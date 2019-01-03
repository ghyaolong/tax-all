package com.chinasoft.tax.controller;

import com.chinasoft.tax.annotation.SystemLog;
import com.chinasoft.tax.aop.EnableGameleyLog;
import com.chinasoft.tax.aopUtils.ModifyName;
import com.chinasoft.tax.common.utils.ResponseUtil;
import com.chinasoft.tax.constant.CommonConstant;
import com.chinasoft.tax.dao.TCompanyMapper;
import com.chinasoft.tax.enums.ExceptionCode;
import com.chinasoft.tax.qo.CompanyQo;
import com.chinasoft.tax.service.CompanyService;
import com.chinasoft.tax.vo.*;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.awt.SystemColor.info;

/**
 * @Description: 公司管理
 * @Date: Created in 15:09 2018/11/2
 * @Author: yaochenglong
 */
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    /**
     * 通过userId获取该用户所拥有的公司
     * @param userId
     * @return
     */
    @SystemLog(description = "通过userId获取该用户所拥有的公司")
    @GetMapping("/userId/{userId}")
    public Message getCompanyByUserId(@PathVariable String userId){
        if(StringUtils.isEmpty(userId)){
            throw new BizException(ExceptionCode.REQUEST_PARAM_ERROR);
        }
        List<CompanyVo> companyVoList = companyService.getByUserId(userId);
        return ResponseUtil.responseBody(companyVoList);
    }
    /**
     * 查询所有公司信息
     * @param companyQo
     * @return
     */
    @SystemLog(description = "分页查询所有公司信息")
    @PostMapping("/getAllPage")
    public Message getAllByPage(@RequestBody CompanyQo companyQo){
        PageVo pageVo = companyQo.getPageVo();
        CompanyVo companyVo = companyQo.getCompanyVo();
        MyPageInfo<CompanyVo> companyVoPageInfo = companyService.findByCondition(pageVo,companyVo);
        return ResponseUtil.responseBody(companyVoPageInfo);
    }

    @SystemLog(description = "查询所有公司信息")
    @GetMapping("/getAll")
    public Message getAll(){
        List<CompanyVo> voList = companyService.getAll();
        return ResponseUtil.responseBody(voList);
    }

    /**
     *
     * @param companyVo
     * @return
     */
    @SystemLog(description = "添加公司")
    @PostMapping("/add")
    public Message add(@RequestBody CompanyVo companyVo){
        companyService.add(companyVo);
        return ResponseUtil.responseBody("添加公司成功");
    }

    /**
     *
     * @param companyVo
     * @return
     */
    @EnableGameleyLog(name = ModifyName.UPDATE,serviceclass = TCompanyMapper.class)
    @PostMapping("/edit")
    public Message edit(@RequestBody CompanyVo companyVo){
        companyService.edit(companyVo);
        return ResponseUtil.responseBody("修改公司成功");
    }


    /**
     * 分配税种给公司
     * @param map companyId 公司Id
     * @param map  taxesIds税种id，多个id已逗号隔开
     * @return
     */
    @PostMapping("/assignTaxes")
    public Message assignTaxes(@RequestBody ModelMap map){
        String companyId = (String) map.get("companyId");
        String taxesIds = (String) map.get("taxesIds");
        companyService.assignTaxes(companyId,taxesIds);
        return ResponseUtil.responseBody("分配税种成功");
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("/del/{id}")
    public Message delById(@PathVariable String id){
        if(StringUtils.isEmpty(id)){
            throw new BizException(ExceptionCode.REQUEST_PARAM_MISSING);
        }
        companyService.delById(id);
        return ResponseUtil.responseBody("删除公司成功");
    }

    /**
     * 查询所有未被分配的公司，下拉选择公司，分配公司时候调用该方法
     * @return
     */
    @GetMapping("/getUnAssign")
    @SystemLog(description = "查询所有未被分配的公司")
    public Message getUnAssignedCompany(){
        List<CompanyVo> unAssignCompany = companyService.getUnAssignCompany();
        return ResponseUtil.responseBody(unAssignCompany);
    }

    /**
     * 通过公司名称查询公司信息
     * @param companyVo
     * @return
     */
    @PostMapping("/getByName")
    public Message getCompanyByName(@RequestBody CompanyVo companyVo){
        CompanyVo vo = companyService.getByName(companyVo.getName());
        return ResponseUtil.responseBody(vo);
    }
}