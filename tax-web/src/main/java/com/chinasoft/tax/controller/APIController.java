package com.chinasoft.tax.controller;

import com.chinasoft.tax.common.utils.ResponseUtil;
import com.chinasoft.tax.service.APIServcice;
import com.chinasoft.tax.vo.APIVo;
import com.chinasoft.tax.vo.Message;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class APIController {

    @Autowired
    private APIServcice apiServcice;

    @RequestMapping("/all")
    public Message getAPIAll(){
        List<APIVo> allAPIVO = apiServcice.getAllAPIVO();
        return ResponseUtil.responseBody(allAPIVO);
    }

    @RequestMapping("/page")
    public Message getAPIAllPage(){
        PageInfo<APIVo> pageInfo = apiServcice.getAllAPIVo(0, 1);
        return ResponseUtil.responseBody(pageInfo);
    }

    /**
     * 添加api
     * @param apiVo
     * @return
     */
    @PostMapping("/add")
    public Message add(@RequestBody APIVo apiVo){
        return ResponseUtil.responseBody("添加api成功");
    }

    /**
     * 编辑api
     * @param apiVo
     * @return
     */
    @PostMapping("/edit")
    public Message edit(@RequestBody APIVo apiVo){
        return ResponseUtil.responseBody("编辑api成功");
    }

    /**
     * 删除api
     * @param ids
     * @return
     */
    @DeleteMapping("/del/{ids}")
    public Message delByIds(String ids){
        return ResponseUtil.responseBody("删除api成功");
    }
}
