package com.chinasoft.tax.controller;

import com.chinasoft.tax.annotation.SystemLog;
import com.chinasoft.tax.aop.EnableGameleyLog;
import com.chinasoft.tax.aopUtils.ModifyName;
import com.chinasoft.tax.common.utils.IDGeneratorUtils;
import com.chinasoft.tax.common.utils.MyBeanUtils;
import com.chinasoft.tax.common.utils.ResponseUtil;
import com.chinasoft.tax.dao.TCompanyMapper;
import com.chinasoft.tax.dao.TDepartmentMapper;
import com.chinasoft.tax.po.TDepartment;
import com.chinasoft.tax.service.DepartmentService;
import com.chinasoft.tax.vo.DepartmentVo;
import com.chinasoft.tax.vo.Message;
import com.chinasoft.tax.vo.PageVo;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 部门管理
 * @Date: Created in 14:18 2018/11/2
 * @Author: yaochenglong
 */
@Slf4j
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 获取所有部门信息
     *
     * @return
     */
    @SystemLog(description = "查询所有部门信息")
    @RequestMapping("/getAll/{pId}")
    public Message getAll(@PathVariable String pId) {
        List<DepartmentVo> all = departmentService.getAll(pId);
        return ResponseUtil.responseBody(all);
    }

    /**
     * 添加部门
     * @param departmentVo
     * @return
     */
    @SystemLog(description = "添加部门")
    @PostMapping("/add")
    public Message add(@RequestBody DepartmentVo departmentVo) {
        departmentService.add(departmentVo);
        return ResponseUtil.responseBody("添加部门成功");
    }

    /**
     * 编辑部门
     * @param departmentVo
     * @return
     */
    @PostMapping("/edit")
    @EnableGameleyLog(name = ModifyName.UPDATE,serviceclass = TDepartmentMapper.class)
    public Message edit(@RequestBody DepartmentVo departmentVo) {
        departmentService.edit(departmentVo);
        return ResponseUtil.responseBody("修改部门成功");
    }

    /**
     * 删除该部门以及该部门下的所有数据
     *
     * @param id
     * @return
     */
    @SystemLog(description = "删除部门")
    @DeleteMapping("/del/{id}")
    public Message delById(@PathVariable String id) {
        departmentService.delById(id);
        return ResponseUtil.responseBody("删除部门成功");
    }

    /**
     * 删除该角色所关联的部门信息
     * @param userId
     * @return
     */
    @SystemLog(description = "删除该角色所关联的部门信息")
    @DeleteMapping("/delUserDepartByUserId/{userId}")
    public Message delUserDepartByUserId(@PathVariable String userId) {

        departmentService.delUserDepartByUserId(userId);
        return ResponseUtil.responseBody("删除成功");
    }

}
