package com.chinasoft.tax.controller;

import com.chinasoft.tax.annotation.SystemLog;
import com.chinasoft.tax.aop.EnableGameleyLog;
import com.chinasoft.tax.aopUtils.ModifyName;
import com.chinasoft.tax.common.utils.ResponseUtil;
import com.chinasoft.tax.dao.TDictMapper;
import com.chinasoft.tax.enums.ExceptionCode;
import com.chinasoft.tax.qo.DictQo;
import com.chinasoft.tax.service.DictService;
import com.chinasoft.tax.vo.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.statement.select.ExceptOp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 字典管理
 * @Date: Created in 14:57 2018/11/5
 * @Author: yaochenglong
 */
@Slf4j
@RestController
@RequestMapping("/dict")
public class DitcController {

    @Autowired
    private DictService dictService;

    /**
     * 分页获取字典信息
     * @param dictQo
     * @return
     */
    @SystemLog(description = "分页获取字典信息")
    @PostMapping("/getAllPage")
    public Message getAllPage(@RequestBody DictQo dictQo){
        PageVo pageVo = dictQo.getPageVo();
        DictVo dictVo = dictQo.getDictVo();
        if(StringUtils.isEmpty(pageVo.getPageNumber())||StringUtils.isEmpty(pageVo.getPageSize())){
            throw new BizException(ExceptionCode.REQUEST_PARAM_ERROR);
        }
        MyPageInfo<DictVo> pageInfo = dictService.findCondition(pageVo,dictVo);
        return ResponseUtil.responseBody(pageInfo);
    }

    /**
     * 添加字典
     * @param dictVo
     * @return
     */
    @SystemLog(description = "添加字典")
    @PostMapping("/add")
    public Message add(@RequestBody DictVo dictVo){
        if(StringUtils.isEmpty(dictVo.getName())||StringUtils.isEmpty(dictVo.getCode())){
            throw new BizException(ExceptionCode.PARAM_IS_NOT_NULL);
        }
        dictService.add(dictVo);
        return ResponseUtil.responseBody("添加成功");
    }

    @EnableGameleyLog(name = ModifyName.UPDATE,serviceclass = TDictMapper.class)
    @PostMapping("/edit")
    public Message edit(@RequestBody DictVo dictVo){
        dictService.edit(dictVo);
        return ResponseUtil.responseBody("修改成功");
    }

    @SystemLog(description = "删除字典")
    @DeleteMapping("/del/{id}")
    public Message del(@PathVariable String id){
        dictService.del(id);
        return ResponseUtil.responseBody("删除成功");
    }

    /**
     * 获取该类型下的所有字典
     * @param typeId  0:国家，1：币种    2：税种  3：纳税期限表
     * @return
     */
    @SystemLog(description = "获取该类型下的所有字典")
    @GetMapping("getByType/{typeId}")
    public Message getByType(@PathVariable Integer typeId){
        if(typeId==null){
            throw new BizException(ExceptionCode.REQUEST_PARAM_ERROR);
        }
        List<DictVo> dictVoList = dictService.getByTypeId(typeId);
        return ResponseUtil.responseBody(dictVoList);
    }
}
