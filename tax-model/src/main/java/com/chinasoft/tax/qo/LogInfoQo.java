package com.chinasoft.tax.qo;

import com.chinasoft.tax.vo.LogInfoVo;
import com.chinasoft.tax.vo.PageVo;
import com.chinasoft.tax.vo.SearchVo;
import lombok.Data;

@Data
public class LogInfoQo {
    PageVo pageVo;
    LogInfoVo logInfoVo;
    SearchVo searchVo;
}
