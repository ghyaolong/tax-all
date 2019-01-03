package com.chinasoft.tax.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${tax.tokenExpireTime}")
    private String test;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @GetMapping("/token")
    public String test(){
        log.info("test/token");
        String s = stringRedisTemplate.opsForValue().get("123");
        log.info("test/token"+":"+s);
        return test+":"+s;
    }

}
