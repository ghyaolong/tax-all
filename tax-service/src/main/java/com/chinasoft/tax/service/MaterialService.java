package com.chinasoft.tax.service;

import com.chinasoft.tax.po.MaterialInfoPo;
import com.chinasoft.tax.vo.MaterialVo;
import com.chinasoft.tax.vo.MyPageInfo;
import com.chinasoft.tax.vo.PageVo;
import com.chinasoft.tax.vo.SearchVo;
import com.github.pagehelper.PageInfo;

/**
 * @Description:资料管理
 * @Author: yaochenglong
 * @Date: 18:10 2018/11/11
 */

public interface MaterialService {
    /**
     * 通过公司名称，资料类型和上传时间查询资料
     * @param pageVo
     * @param searchVo
     * @param
     * @return
     */
    MyPageInfo<MaterialInfoPo> getAllPage(PageVo pageVo, SearchVo searchVo, String[] companyIds,String companyName,String[] taxDicts, String materialTypeDict);

    /**
     * 保存资料
     * @param materialVo
     */
    void add(MaterialVo materialVo);

    /**
     * 通过id查询资料信息
     * @param id
     * @return
     */
    MaterialVo findById(String id);

    /**
     * 通过fileName查询文件
     * @param fileName
     * @return
     */
    MaterialVo findByFileName(String fileName);
}
