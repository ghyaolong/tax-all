package com.chinasoft.tax.qo;

import com.chinasoft.tax.vo.CompanyVo;
import com.chinasoft.tax.vo.PageVo;
import com.github.pagehelper.PageInfo;
import lombok.Data;

/**
 * @Description: 封装查询公司的查询对象
 * @Date: Created in 15:27 2018/11/2
 * @Author: yaochenglong
 */

@Data
public class CompanyQo {
    PageVo pageVo;
    CompanyVo companyVo;
}
