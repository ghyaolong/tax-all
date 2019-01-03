package com.chinasoft.tax.service;

import com.chinasoft.tax.vo.MyPageInfo;
import com.chinasoft.tax.vo.PageVo;
import com.chinasoft.tax.vo.SearchVo;
import com.chinasoft.tax.vo.TaxApplicationVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Description: 税金申请
 * @Date: Created in 21:01 2018/11/5
 * @Author: yaochenglong
 */
public interface TaxApplicationService {

    /**
     * 税金申请
     * @param taxApplicationVo
     */
    void add(TaxApplicationVo taxApplicationVo);

    /**
     * 补充税金申请
     * @param taxApplicationVo
     */
    void replenishment(TaxApplicationVo taxApplicationVo);

    /**
     * 修改税金申请
     * @param taxApplicationVo
     */
    void edit(TaxApplicationVo taxApplicationVo);

    /**
     * 查询带提交的任务
     * @param pageVo
     * @param searchVo 保存的时间
     * @param userId
     * @param status 任务状态
     * @return
     */
    MyPageInfo<TaxApplicationVo> getReadyCommit(PageVo pageVo, SearchVo searchVo, String userId, Integer status);

    /**
     * 删除待提任务
     * @param id
     */
    void delById(String id);

    /**
     * 通过id查询税金申请详情
     * @param id
     */
    TaxApplicationVo getById(String id);

    /**
     *
     * @param flowNum
     * @return
     */
    List<TaxApplicationVo> getTaxAuditLog(String flowNum);

    /**
     * 录入历史数据,不需要上传文件
     * @param taxApplicationVo
     */
    void input(TaxApplicationVo taxApplicationVo);
}
