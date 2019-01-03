package com.chinasoft.tax.service;

import com.chinasoft.tax.vo.DepartmentVo;
import com.chinasoft.tax.vo.PageVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface DepartmentService {

    /**
     * 获取parentId下的所有节点信息
     * @return
     */
    List<DepartmentVo> getAll(String parentId);

    /**
     * 保存部门
     * @param departmentVo
     */
    void add(DepartmentVo departmentVo);

    /**
     *修改部门
     * @param departmentVo
     */
    void edit(DepartmentVo departmentVo);

    /**
     * 删除该部门以及该部门下的所有数据
     * @param id
     */
    void delById(String id);

    /**
     * 通过userId删除该该userId所关联的部门
     * @param userId
     */
    void delUserDepartByUserId(String userId);
}
