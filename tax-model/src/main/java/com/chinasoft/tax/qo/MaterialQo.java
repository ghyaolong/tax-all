package com.chinasoft.tax.qo;

import com.chinasoft.tax.vo.PageVo;
import com.chinasoft.tax.vo.SearchVo;
import lombok.Data;

@Data
public class MaterialQo {
    private PageVo pageVo;
    private String companyId;
    private String companyName;
    private String materialTypeDict;
    private SearchVo searchVo;
}
