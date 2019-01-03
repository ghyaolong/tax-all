package com.chinasoft.tax.controller;

import com.chinasoft.tax.common.utils.ResponseUtil;
import com.chinasoft.tax.vo.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: yaochenglong
 * @Date: 22:27 2018/11/16
 */

@Slf4j
@RestController
@RequestMapping("/common")
public class SecurityController {

    @RequestMapping(value = "/needLogin",method = RequestMethod.GET)
    public Message needLogin(){
        return ResponseUtil.responseBody("401","您还未登录");
    }
}
