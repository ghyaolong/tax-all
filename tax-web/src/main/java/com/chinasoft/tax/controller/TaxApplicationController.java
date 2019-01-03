package com.chinasoft.tax.controller;

import com.chinasoft.tax.annotation.SystemLog;
import com.chinasoft.tax.aop.EnableGameleyLog;
import com.chinasoft.tax.aopUtils.ModifyName;
import com.chinasoft.tax.common.utils.ResponseUtil;
import com.chinasoft.tax.config.security.CurrentUserUtils;
import com.chinasoft.tax.constant.CommonConstant;
import com.chinasoft.tax.dao.TTaxApplicationMapper;
import com.chinasoft.tax.enums.ExceptionCode;
import com.chinasoft.tax.qo.TaxQo;
import com.chinasoft.tax.service.TaxApplicationService;
import com.chinasoft.tax.service.UserService;
import com.chinasoft.tax.vo.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 税金申请
 * @Date: Created in 20:16 2018/11/5
 * @Author: yaochenglong
 */
@Slf4j
@RestController
@RequestMapping("/tax")
public class TaxApplicationController {

    @Autowired
    private TaxApplicationService taxApplicationService;

    @Autowired
    private CurrentUserUtils currentUserUtils;

    @Autowired
    private UserService userService;



    @SystemLog(description = "税金申请")
    @PostMapping("/add")
    public Message addTaxApplication(@RequestBody TaxApplicationVo taxApplicationVo){
        String userId = currentUserUtils.getUserId();
        String userName = currentUserUtils.getUserName();
        taxApplicationVo.setApplicantId(userId);
        taxApplicationVo.setApplicantName(userName);
        taxApplicationVo.setApplicantId(userId);
        taxApplicationService.add(taxApplicationVo);
        return ResponseUtil.responseBody("添加税金申请成功");
    }

    @EnableGameleyLog(name = ModifyName.UPDATE,serviceclass = TTaxApplicationMapper.class)
    @PostMapping("/replenishment")
    public Message replenishment(@RequestBody TaxApplicationVo taxApplicationVo){
        String userId = currentUserUtils.getUserId();
        String userName = currentUserUtils.getUserName();
        taxApplicationVo.setApplicantId(userId);
        taxApplicationVo.setApplicantName(userName);
        taxApplicationVo.setApplicantId(userId);
        taxApplicationService.replenishment(taxApplicationVo);
        return ResponseUtil.responseBody("补充税金申请成功");
    }


    @SystemLog(description = "查询税金申请记录")
    @GetMapping("/getTaxAuditLog/{flowNum}")
    public Message getTaxAuditLog(@PathVariable String flowNum){
        List<TaxApplicationVo> voList = taxApplicationService.getTaxAuditLog(flowNum);
        return ResponseUtil.responseBody(voList);
    }


    @SystemLog(description = "修改税金申请")
    @PostMapping("/edit")
    public Message editTaxApplication(@RequestBody TaxApplicationVo taxApplicationVo){
        String userId = currentUserUtils.getUserId();
        String userName = currentUserUtils.getUserName();
        taxApplicationVo.setApplicantId(userId);
        taxApplicationVo.setApplicantName(userName);
        taxApplicationVo.setApplicantId(userId);
        taxApplicationService.edit(taxApplicationVo);
        return ResponseUtil.responseBody("修改税金申请成功");
    }

    /**
     * 代提任务
     * 查询条件：流程类型   保存时间
     * @return
     */
    @SystemLog(description = "查询待提任务")
    @PostMapping("/readyCommit")
    public Message readyCommit(@RequestBody TaxQo taxQo){

        PageVo pageVo = taxQo.getPageVo();
        SearchVo searchVo = taxQo.getSearchVo();
        String currentUserId = currentUserUtils.getUserId();

        //currentUserId = "682265633886208";
        MyPageInfo<TaxApplicationVo> readyCommit = taxApplicationService.getReadyCommit(pageVo, searchVo, currentUserId,CommonConstant.EXECUTE_TYPE_SAVE);
        return ResponseUtil.responseBody(readyCommit);

    }

    /**
     * 查询待办任务
     * @return
     */
    @SystemLog(description = "查询待办任务")
    @PostMapping("/readyHandle")
    public Message readyHandle(@RequestBody TaxQo taxQo){

        PageVo pageVo = taxQo.getPageVo();
        SearchVo searchVo = taxQo.getSearchVo();
        String currentUserId = currentUserUtils.getUserId();

        //currentUserId = "682265633886208";
        MyPageInfo<TaxApplicationVo> commit = taxApplicationService.getReadyCommit(pageVo, searchVo, currentUserId,CommonConstant.TAX_COMMITER);
        return ResponseUtil.responseBody(commit);
    }

    /**
     * 查询已办任务
     * @return
     */
    @SystemLog(description = "查询已办任务")
    @PostMapping("/alreadyHandle")
    public Message alreadyHandle(@RequestBody TaxQo taxQo){

        PageVo pageVo = taxQo.getPageVo();
        SearchVo searchVo = taxQo.getSearchVo();
        String currentUserId = currentUserUtils.getUserId();
        String key = taxQo.getKey();

        UserVo userVo = userService.getUserInfoByUserIdAndKey(currentUserId, key);
        int taxStatus= CommonConstant.TAX_SAVE;
        if(userVo!=null){
            RoleVo roleVo = userVo.getRoles().get(0);
            if(roleVo!=null){
                switch (roleVo.getCode()){
                    case "ROLE_COMMISSIONER_OF_TAX":
                        taxStatus = CommonConstant.EXECUTE_TYPE_COMMIT;
                        break;
                    case "ROLE_ REVIEWER":
                        taxStatus = CommonConstant.TAX_COMMITER;
                        break;
                    case "ROLE_TAX_COMPLIANCE_SUPERVISOR":
                        taxStatus = CommonConstant.TAX_COMPLIANCE_OFFICER;
                        break;
                    case "ROLE_TAX_COMMISSIONER":
                        taxStatus = CommonConstant.TAX_COMMISSIONER;
                        break;
                    case "ROLE_TAX_DIRECTOR_OF_FUNDS":
                        taxStatus = CommonConstant.TAX_DIRECTOR_OF_FUNDS;
                        break;
                    case "ROLE_TAX_CHIEF_FINANCIAL_OFFICER":
                        taxStatus = CommonConstant.TAX_CHIEF_FINANCIAL_OFFICER;
                        break;
                    case "ROLE_ADMINISTRATOR":
                        taxStatus = 1;
                        break;
                        default:
                            throw new BizException(ExceptionCode.UNKNOWN_ERROR.getCode(),"不是审批流程中的用户");
                }
            }
        }
        MyPageInfo<TaxApplicationVo> commit = taxApplicationService.getReadyCommit(pageVo, searchVo, currentUserId,taxStatus);
        return ResponseUtil.responseBody(commit);
    }

    /**
     * 删除待提任务
     * @param id
     * @return
     */
    @SystemLog(description = "删除待提任务")
    @DeleteMapping("/delById/{id}")
    public Message delById(@PathVariable String id){
        taxApplicationService.delById(id);
        return ResponseUtil.responseBody("删除待提任务成功");
    }

    @GetMapping("/get/{id}")
    public Message getTaxApplicationById(@PathVariable String id){

        TaxApplicationVo taxApplicationVo = taxApplicationService.getById(id);
        return ResponseUtil.responseBody(taxApplicationVo);
    }


    /**
     * 录入历史数据，不需要上传文件。
     * @param taxApplicationVo
     * @return
     */
    @PostMapping("/inputData")
    public Message inputDataToDB(@RequestBody TaxApplicationVo taxApplicationVo){
        String userId = currentUserUtils.getUserId();
        String userName = currentUserUtils.getUserName();
        taxApplicationVo.setApplicantId(userId);
        taxApplicationVo.setApplicantName(userName);
        taxApplicationVo.setApplicantId(userId);
        taxApplicationService.input(taxApplicationVo);
        return ResponseUtil.responseBody("录入税金申请成功");
    }


}
