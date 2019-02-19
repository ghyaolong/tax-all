package com.chinasoft.tax.controller;

import com.chinasoft.tax.common.utils.ResponseUtil;
import com.chinasoft.tax.service.SysConfigService;
import com.chinasoft.tax.vo.Message;
import com.chinasoft.tax.vo.SysConfigVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统配置管理类
 * 主要配置上传文件的大小和上传文件的类型
 */
@RestController
@RequestMapping("/sysConfig")
public class SysConfigController {

    @Autowired
    private SysConfigService sysConfigService;

    @PostMapping("/editConfig")
    public Message editConfig(@RequestBody SysConfigVo sysConfigVo){
        sysConfigService.editConfig(sysConfigVo);
        return ResponseUtil.responseBody("修改成功");
    }

    @GetMapping("/getAll")
    public Message findAll(){
        List<SysConfigVo> all = sysConfigService.findAll();
        return ResponseUtil.responseBody(all);
    }

    /**
     * 通过key获取值，入获取可以上传的文件类型
     * @return
     */
    @GetMapping("/getMsg/{key}")
    public Message getMsgByKey(@PathVariable String key){
        SysConfigVo msgByKey = sysConfigService.getMsgByKey(key);
        return ResponseUtil.responseBody(msgByKey);
    }


}
