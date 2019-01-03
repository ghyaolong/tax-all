package com.chinasoft.tax.qo;

import com.chinasoft.tax.vo.PageVo;
import com.chinasoft.tax.vo.SearchVo;
import lombok.Data;

@Data
public class TaxQo {

    PageVo pageVo;
    String key;
    SearchVo searchVo;
}
