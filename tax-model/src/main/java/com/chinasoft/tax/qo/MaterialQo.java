package com.chinasoft.tax.qo;

import com.chinasoft.tax.vo.PageVo;
import com.chinasoft.tax.vo.SearchVo;
import lombok.Data;

@Data
public class MaterialQo {
    private PageVo pageVo;
    private String companyId;
    private String companyIds;
    private String companyName;
    private String materialTypeDict;
    private String taxDicts;
    private SearchVo searchVo;
}
