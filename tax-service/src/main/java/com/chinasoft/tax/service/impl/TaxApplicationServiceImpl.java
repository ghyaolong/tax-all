package com.chinasoft.tax.service.impl;

import com.chinasoft.tax.common.utils.*;
import com.chinasoft.tax.constant.CommonConstant;
import com.chinasoft.tax.dao.TApplicationMaterialMapper;
import com.chinasoft.tax.dao.TAuditLogMapper;
import com.chinasoft.tax.dao.TTaxApplicationDetailMapper;
import com.chinasoft.tax.dao.TTaxApplicationMapper;
import com.chinasoft.tax.enums.ExceptionCode;
import com.chinasoft.tax.po.TApplicationMaterial;
import com.chinasoft.tax.po.TAuditLog;
import com.chinasoft.tax.po.TTaxApplication;
import com.chinasoft.tax.po.TTaxApplicationDetail;
import com.chinasoft.tax.service.MaterialService;
import com.chinasoft.tax.service.TaxApplicationService;
import com.chinasoft.tax.vo.*;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TaxApplicationServiceImpl implements TaxApplicationService {

    @Resource
    private TTaxApplicationMapper tTaxApplicationMapper;

    @Resource
    private TTaxApplicationDetailMapper tTaxApplicationDetailMapper;

    @Resource
    private TApplicationMaterialMapper tApplicationMaterialMapper;

    @Resource
    private TAuditLogMapper tAuditLogMapper;

    @Autowired
    private MaterialService materialService;


    @Value("${tax.file.upload}")
    private String filePath;

    @Transactional
    @Override
    public void add(TaxApplicationVo taxApplicationVo) {
        TTaxApplication tTaxApplication = MyBeanUtils.copy(taxApplicationVo, TTaxApplication.class);
        tTaxApplication.setId(IDGeneratorUtils.getUUID32());
        if(taxApplicationVo.getExecuteType() == CommonConstant.EXECUTE_TYPE_SAVE){
            tTaxApplication.setSaveTime(new Date());
            tTaxApplication.setStatus(CommonConstant.EXECUTE_TYPE_SAVE);
        }else{
            tTaxApplication.setSaveTime(new Date());
            tTaxApplication.setCreateTime(new Date());
            tTaxApplication.setSerialNumber(IDGeneratorUtils.getFlowNum());
            tTaxApplication.setStatus(CommonConstant.EXECUTE_TYPE_COMMIT);

            //如果是提交，则必须上传财务报表
            if(StringUtils.isEmpty(tTaxApplication.getFinancialReport())){
                throw new BizException(ExceptionCode.REQUEST_PARAM_ERROR.getCode(),"请上传财务报表");
            }else{
                MyFileUtils.evictUselessFile(filePath,taxApplicationVo.getOriName());
                saveApplicationMaterial(taxApplicationVo, tTaxApplication);
                tTaxApplication.setIsUploadReport(CommonConstant.FILE_UPLOADED);
            }
        }

        tTaxApplicationMapper.insertSelective(tTaxApplication);
        List<TaxApplicationDetailVo> detailVos = taxApplicationVo.getDetails();
        List<TTaxApplicationDetail> tTaxApplicationDetails = MyBeanUtils.copyList(detailVos, TTaxApplicationDetail.class);
        for (TTaxApplicationDetail tTaxApplicationDetail : tTaxApplicationDetails) {

            //提交需要上传 预申报表
            if(taxApplicationVo.getExecuteType() == CommonConstant.EXECUTE_TYPE_COMMIT){
                if(StringUtils.isEmpty(tTaxApplicationDetail.getPreTaxReturns())){
                    throw new BizException(ExceptionCode.REQUEST_PARAM_ERROR.getCode(),"请上传预申报表");
                }else{
                    MyFileUtils.evictUselessFile(filePath,tTaxApplicationDetail.getPreTaxReturnsPathFileName());
                    tTaxApplicationDetail.setIsUploadPreTaxReturns(CommonConstant.FILE_UPLOADED);
                }
            }

            tTaxApplicationDetail.setId(IDGeneratorUtils.getUUID32());
            tTaxApplicationDetail.setTaxApplicationId(tTaxApplication.getId());
            tTaxApplicationDetailMapper.insertSelective(tTaxApplicationDetail);


        }
    }



    @Override
    public void input(TaxApplicationVo taxApplicationVo) {
        TTaxApplication tTaxApplication = MyBeanUtils.copy(taxApplicationVo, TTaxApplication.class);
        // 生成流水号
        String businessFlowNumber = BusinessFlowNumberUtil.getBusinessFlowNumber();
        tTaxApplication.setBusinessFlowNumber(businessFlowNumber);

        tTaxApplication.setId(IDGeneratorUtils.getUUID32());
        tTaxApplication.setSaveTime(new Date());
        tTaxApplication.setCreateTime(new Date());
        tTaxApplication.setStatus(CommonConstant.TAX_DONE);

        tTaxApplicationMapper.insertSelective(tTaxApplication);
        List<TaxApplicationDetailVo> detailVos = taxApplicationVo.getDetails();
        List<TTaxApplicationDetail> tTaxApplicationDetails = MyBeanUtils.copyList(detailVos, TTaxApplicationDetail.class);
        for (TTaxApplicationDetail tTaxApplicationDetail : tTaxApplicationDetails) {
            tTaxApplicationDetail.setId(IDGeneratorUtils.getUUID32());
            tTaxApplicationDetail.setTaxApplicationId(tTaxApplication.getId());
            tTaxApplicationDetailMapper.insertSelective(tTaxApplicationDetail);
        }
    }

    @Override
    public void edit(TaxApplicationVo taxApplicationVo) {
        TTaxApplication tTaxApplication = MyBeanUtils.copy(taxApplicationVo, TTaxApplication.class);
        if(taxApplicationVo.getExecuteType() == CommonConstant.EXECUTE_TYPE_SAVE){
            tTaxApplication.setStatus(CommonConstant.EXECUTE_TYPE_SAVE);
        }else{
            tTaxApplication.setCreateTime(new Date());
            tTaxApplication.setStatus(CommonConstant.EXECUTE_TYPE_COMMIT);

            //如果是提交，则必须上传财务报表
            if(StringUtils.isEmpty(tTaxApplication.getFinancialReport())){
                throw new BizException(ExceptionCode.REQUEST_PARAM_ERROR.getCode(),"请上传财务报表");
            }else{
                MyFileUtils.evictUselessFile(filePath,taxApplicationVo.getOriName());
                saveApplicationMaterial(taxApplicationVo, tTaxApplication);
                tTaxApplication.setIsUploadReport(CommonConstant.FILE_UPLOADED);
            }
        }

        tTaxApplicationMapper.updateByPrimaryKeySelective(tTaxApplication);

        List<TaxApplicationDetailVo> detailVos = taxApplicationVo.getDetails();
        List<TTaxApplicationDetail> tTaxApplicationDetails = MyBeanUtils.copyList(detailVos, TTaxApplicationDetail.class);
        for (TTaxApplicationDetail tTaxApplicationDetail : tTaxApplicationDetails) {

            String id = tTaxApplicationDetail.getId();
            //提交需要上传 预申报表
            if(taxApplicationVo.getExecuteType() == CommonConstant.EXECUTE_TYPE_COMMIT){
                if(StringUtils.isEmpty(tTaxApplicationDetail.getPreTaxReturns())){
                    throw new BizException(ExceptionCode.REQUEST_PARAM_ERROR.getCode(),"请上传预申报表");
                }else{
                    MyFileUtils.evictUselessFile(filePath,tTaxApplicationDetail.getPreTaxReturnsPathFileName());
                    tTaxApplicationDetail.setIsUploadPreTaxReturns(CommonConstant.FILE_UPLOADED);
                }
            }

            tTaxApplicationDetail.setTaxApplicationId(tTaxApplication.getId());

            if (!StringUtils.isEmpty(id)) {
                tTaxApplicationDetailMapper.updateByPrimaryKeySelective(tTaxApplicationDetail);
            } else {
                tTaxApplicationDetail.setId(IDGeneratorUtils.getUUID32());
                tTaxApplicationDetailMapper.insertSelective(tTaxApplicationDetail);
            }
        }
    }

    /**
     * 补充税金申请，主要补充 申报表 ，扣款凭证
     * @param taxApplicationVo
     */
    @Override
    public void replenishment(TaxApplicationVo taxApplicationVo) {
        if(StringUtils.isEmpty(taxApplicationVo.getFinancialReport())&&taxApplicationVo.getIsUploadReport()==CommonConstant.FILE_UPLOADED){
            throw new BizException(ExceptionCode.REQUEST_PARAM_ERROR.getCode(),"请再一次上传财务报表");
        }
        //2表示最终版都财务报表已经上传
        taxApplicationVo.setIsUploadReport(2);

        List<TaxApplicationDetailVo> detailVos = taxApplicationVo.getDetails();
        List<TTaxApplicationDetail> tTaxApplicationDetails = MyBeanUtils.copyList(detailVos, TTaxApplicationDetail.class);

        for (TTaxApplicationDetail tTaxApplicationDetail : tTaxApplicationDetails) {

            if(StringUtils.isEmpty(tTaxApplicationDetail.getPaymentCertificate())){
                throw new BizException(ExceptionCode.REQUEST_PARAM_ERROR.getCode(),"请上传扣款凭证");
            }

            if(StringUtils.isEmpty(tTaxApplicationDetail.getTaxReturns())){
                throw new BizException(ExceptionCode.REQUEST_PARAM_ERROR.getCode(),"请上传税务申报表");
            }

            tTaxApplicationDetail.setTaxApplicationId(tTaxApplicationDetail.getId());
            tTaxApplicationDetailMapper.updateByPrimaryKeySelective(tTaxApplicationDetail);
        }

        taxApplicationVo.setStatus(CommonConstant.TAX_DONE);
        TTaxApplication tTaxApplication = MyBeanUtils.copy(taxApplicationVo, TTaxApplication.class);
        tTaxApplicationMapper.updateByPrimaryKeySelective(tTaxApplication);

    }

    /**
     * 保存税金申请的资料文件
     * @param taxApplicationVo
     * @param tTaxApplication
     */
    private void saveApplicationMaterial(TaxApplicationVo taxApplicationVo, TTaxApplication tTaxApplication) {
        TApplicationMaterial am = new TApplicationMaterial();
        am.setId(IDGeneratorUtils.getUUID32());
        am.setMaterialId(taxApplicationVo.getFinancialReport());
        am.setApplicationId(taxApplicationVo.getApplicantId());
        am.setTaxApplicationId(tTaxApplication.getId());
        am.setType(CommonConstant.UPLOAD_TAX);
        am.setCompanyId(taxApplicationVo.getCompanyId());
        am.setCompanyName(taxApplicationVo.getCompanyName());
        am.setCreateTime(new Date());
        tApplicationMaterialMapper.insertSelective(am);
    }


    @Override
    public MyPageInfo<TaxApplicationVo> getReadyCommit(PageVo pageVo, SearchVo searchVo,String userId,Integer status){
        //PageHelper.startPage(pageVo.getPageNumber(),pageVo.getPageSize(),true);
        Example example = new Example(TTaxApplication.class);
        example.setOrderByClause("save_time desc");
        Example.Criteria criteria = example.createCriteria();
        if(status == CommonConstant.EXECUTE_TYPE_COMMIT){
            criteria.andCondition("status","1,7");
        }else{
            criteria.andEqualTo("status",status);
        }
        criteria.andEqualTo("applicantId",userId);

        if(searchVo!=null){
//            if(!StringUtils.isEmpty(searchVo.getStartDate())){
//                DateTime startTime = DateUtil.parse(searchVo.getStartDate());
//                criteria.andGreaterThanOrEqualTo("saveTime",startTime);
//            }
            /**这里如果单独使用lessThanorEqualTo条件的话，tk-MyBatis，在sql语句都正常都情况下
             查询不出结果来，如果使用Between方法都话，就可以查询出来，这个问题回头再看
             */
//            if(!StringUtils.isEmpty(searchVo.getEndDate())){
//                DateTime endTime = DateUtil.parse(searchVo.getEndDate());
//                criteria.andLessThanOrEqualTo("saveTime",endTime);
//            }
            if(!StringUtils.isEmpty(searchVo.getStartDate())&&!StringUtils.isEmpty(searchVo.getEndDate())) {
                String startDate = String.format("%s 00:00:00", searchVo.getStartDate());
                String endDate = String.format("%s 23:59:59", searchVo.getEndDate());
                criteria.andBetween("saveTime", startDate, endDate);
            }
        }

        List<TTaxApplication> tTaxApplicationList = tTaxApplicationMapper.selectByExample(example);
        int count = tTaxApplicationMapper.selectCountByExample(example);
        List<TaxApplicationVo> taxApplicationVos = MyBeanUtils.copyList(tTaxApplicationList, TaxApplicationVo.class);
        MyPageInfo<TaxApplicationVo> pageInfo = new MyPageInfo<>(taxApplicationVos);
        pageInfo.setTotalElements(count);
        pageInfo.setPageNum(pageVo.getPageNumber());
        return pageInfo;
    }

    @Override
    public void delById(String id) {
        TTaxApplication tTaxApplication = tTaxApplicationMapper.selectByPrimaryKey(id);
        if(tTaxApplication!=null && tTaxApplication.getStatus() == CommonConstant.EXECUTE_TYPE_SAVE){
            tTaxApplicationMapper.deleteByPrimaryKey(id);
            Example  example = new Example(TTaxApplicationDetail.class);
            example.createCriteria().andEqualTo("taxApplicationId",tTaxApplication.getId());
            tTaxApplicationDetailMapper.deleteByExample(example);
        }else{
            throw new BizException(ExceptionCode.DEL_TAX_ERROR);
        }
    }

    @Override
    public TaxApplicationVo getById(String id) {
        TTaxApplication tTaxApplication = tTaxApplicationMapper.selectByPrimaryKey(id);
        if(tTaxApplication!=null){
            TaxApplicationVo vo = MyBeanUtils.copy(tTaxApplication, TaxApplicationVo.class);
            Example  example = new Example(TTaxApplicationDetail.class);
            example.createCriteria().andEqualTo("taxApplicationId",tTaxApplication.getId());
            List<TTaxApplicationDetail> tTaxApplicationDetails = tTaxApplicationDetailMapper.selectByExample(example);
            if(!CollectionUtils.isEmpty(tTaxApplicationDetails)){
                List<TaxApplicationDetailVo> detailVos = MyBeanUtils.copyList(tTaxApplicationDetails, TaxApplicationDetailVo.class);

                for (TaxApplicationDetailVo detailVo : detailVos) {
                    if(!StringUtils.isEmpty(detailVo.getTaxReturnsPath())){
                        MaterialVo taxReturnPath = materialService.findByFileName(detailVo.getTaxReturnsPath());
                        detailVo.setTaxReturnsPathFileName(taxReturnPath.getOriName());
                    }
                    if(!StringUtils.isEmpty(detailVo.getPaymentCertificatePath())) {
                        MaterialVo certificatePath = materialService.findByFileName(detailVo.getPaymentCertificatePath());
                        detailVo.setPaymentCertificateFileName(certificatePath.getOriName());
                     }
                    if(!StringUtils.isEmpty(detailVo.getPreTaxReturnsPath())){

                        MaterialVo preTaxReturn = materialService.findByFileName(detailVo.getPreTaxReturnsPath());
                        detailVo.setPreTaxReturnsPathFileName(preTaxReturn.getOriName());
                    }
                    if(!StringUtils.isEmpty(detailVo.getOtherUpload())){
                        MaterialVo otherName = materialService.findByFileName(detailVo.getOtherUpload());
                        detailVo.setOtherUploadFileName(otherName.getOriName());
                    }
                }
                vo.setDetails(detailVos);
            }
            return vo;
        }
        return null;
    }

    @Override
    public List<TaxApplicationVo> getTaxAuditLog(String flowNum) {
        Example example = new Example(TTaxApplication.class);
        example.createCriteria().andEqualTo("serialNumber",flowNum);
        List<TTaxApplication> tTaxApplications = tTaxApplicationMapper.selectByExample(example);
        List<TaxApplicationVo> taxApplicationVos = MyBeanUtils.copyList(tTaxApplications, TaxApplicationVo.class);
        for (TaxApplicationVo taxApplicationVo : taxApplicationVos) {
            Example detailExample = new Example(TTaxApplicationDetail.class);
            detailExample.createCriteria().andEqualTo("taxApplicationId",taxApplicationVo.getId());
            List<TTaxApplicationDetail> tTaxApplicationDetails = tTaxApplicationDetailMapper.selectByExample(detailExample);
            List<TaxApplicationDetailVo> taxApplicationDetailVos = MyBeanUtils.copyList(tTaxApplicationDetails, TaxApplicationDetailVo.class);
            taxApplicationVo.setDetails(taxApplicationDetailVos);

            //查询审批记录
            Example auditLogExmaple = new Example(TAuditLog.class);
            auditLogExmaple.createCriteria().andEqualTo("flowNum",taxApplicationVo.getSerialNumber());
            List<TAuditLog> tAuditLogs = tAuditLogMapper.selectByExample(auditLogExmaple);
            List<AuditLogVo> auditLogVos = MyBeanUtils.copyList(tAuditLogs, AuditLogVo.class);
            taxApplicationVo.setAuditLogVoList(auditLogVos);
        }
        return taxApplicationVos;
    }
}
