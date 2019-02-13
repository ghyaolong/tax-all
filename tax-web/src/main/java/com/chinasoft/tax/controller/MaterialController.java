package com.chinasoft.tax.controller;

import com.chinasoft.tax.annotation.SystemLog;
import com.chinasoft.tax.common.utils.ResponseUtil;
import com.chinasoft.tax.po.MaterialInfoPo;
import com.chinasoft.tax.qo.MaterialQo;
import com.chinasoft.tax.service.MaterialService;
import com.chinasoft.tax.vo.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/material")
@RestController
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @SystemLog(description = "资料查询")
    @PostMapping("/getAllPage")
    public Message getAllMaterial(@RequestBody MaterialQo materialQo){
        PageVo pageVo = materialQo.getPageVo();
        String companyName = materialQo.getCompanyName();
        String materialTypeDict = materialQo.getMaterialTypeDict();
        SearchVo searchVo = materialQo.getSearchVo();
        String companyId = materialQo.getCompanyIds();
        String taxDict = materialQo.getTaxDicts();
        String[] ids = null;
        String[] taxDicts = null;
        if(!StringUtils.isEmpty(companyId)){
            ids = companyId.split(",");
        }
        if(!StringUtils.isEmpty(taxDict)){
            taxDicts = taxDict.split(",");
        }
        MyPageInfo<MaterialInfoPo> allPage = materialService.getAllPage(pageVo, searchVo, ids,companyName,taxDicts,materialTypeDict);
        return ResponseUtil.responseBody(allPage);
    }


}
