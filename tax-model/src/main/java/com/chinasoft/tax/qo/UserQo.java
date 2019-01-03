package com.chinasoft.tax.qo;

import com.chinasoft.tax.vo.PageVo;
import com.chinasoft.tax.vo.SearchVo;
import com.chinasoft.tax.vo.UserVo;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Description: 封装分页查询用户对象
 * @Date: Created in 19:57 2018/11/2
 * @Author: yaochenglong
 */
@Data
public class UserQo {
    PageVo pageVo;
    SearchVo searchVo;
    UserVo userVo;
}
