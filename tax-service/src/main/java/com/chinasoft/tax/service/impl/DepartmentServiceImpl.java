package com.chinasoft.tax.service.impl;

import com.chinasoft.tax.common.utils.IDGeneratorUtils;
import com.chinasoft.tax.common.utils.MyBeanUtils;
import com.chinasoft.tax.dao.TDepartmentMapper;
import com.chinasoft.tax.dao.TUserDepartmentMapper;
import com.chinasoft.tax.enums.ExceptionCode;
import com.chinasoft.tax.po.TDepartment;
import com.chinasoft.tax.po.TPermission;
import com.chinasoft.tax.po.TUserDepartment;
import com.chinasoft.tax.service.DepartmentService;
import com.chinasoft.tax.vo.BizException;
import com.chinasoft.tax.vo.DepartmentVo;
import com.chinasoft.tax.vo.PageVo;
import com.chinasoft.tax.vo.PermissionVo;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private TDepartmentMapper tDepartmentMapper;

    @Autowired
    private TUserDepartmentMapper tUserDepartmentMapper;

    @Override
    public List<DepartmentVo> getAll(String parentId) {
        Example example = new Example(TDepartment.class);
        example.setOrderByClause("create_time desc");
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isEmpty(parentId)) {
            criteria.andEqualTo("parentId", 0);
        } else {
            criteria.andEqualTo("parentId", parentId);
        }
        //根数据或者原始数据
        List<TDepartment> tDepartments = tDepartmentMapper.selectByExample(example);
        List<DepartmentVo> departmentVos = MyBeanUtils.copyList(tDepartments, DepartmentVo.class);

        for (DepartmentVo departmentVo : departmentVos) {
            List<DepartmentVo> allChild = getAllChild(departmentVo.getId(), departmentVo);
        }
        return departmentVos;
    }

    @Override
    public void add(DepartmentVo departmentVo) {
        log.info("添加部门,输入参数：" + departmentVo.toString());
        TDepartment tDepartment = MyBeanUtils.copy(departmentVo, TDepartment.class);
        tDepartment.setId(IDGeneratorUtils.getUUID32());
        tDepartment.setCreateTime(new Date());
        tDepartmentMapper.insertSelective(tDepartment);
        log.info("添加部门成功");
    }

    @Override
    public void edit(DepartmentVo departmentVo) {
        log.info("修改部门，输入参数:" + departmentVo.toString());
        TDepartment tDepartment = MyBeanUtils.copy(departmentVo, TDepartment.class);
        tDepartmentMapper.updateByPrimaryKeySelective(tDepartment);
        log.info("修改部门成功");
    }

    @Override
    public void delById(String id) {
        log.info("删除部门，输入参数id:" + id);
        Example example = new Example(TUserDepartment.class);
        example.createCriteria().andEqualTo("departmentId", id);
        int count = tUserDepartmentMapper.selectCountByExample(example);
        if (count > 0) {
            throw new BizException(ExceptionCode.DEPARTMENT_HAS_USER);
        }
        //删除该节点以及该节点下的子节点
        List<DepartmentVo> all = getAll(id);
        for (DepartmentVo departmentVo : all) {
            //tUserDepartmentMapper.deleteByPrimaryKey(departmentVo.getId());
            delById(departmentVo.getId());
        }
        tDepartmentMapper.deleteByPrimaryKey(id);
        log.info("删除部门成功");
    }

    @Override
    public void delUserDepartByUserId(String userId) {
        log.info("删除用户-部门，输入参数userId:" + userId);
        Example example = new Example(TUserDepartment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        tUserDepartmentMapper.deleteByExample(example);
        log.info("删除用户-部门成功");
    }

    /**
     * 递归查询子节点
     *
     * @param pId
     * @param vo
     * @return
     */
    private List<DepartmentVo> getAllChild(String pId, DepartmentVo vo) {
        Example example = new Example(TDepartment.class);
        example.setOrderByClause("create_time desc");
        example.createCriteria().andEqualTo("parentId", pId);
        List<TDepartment> tDepartmentList = tDepartmentMapper.selectByExample(example);
        List<DepartmentVo> departmentVos = MyBeanUtils.copyList(tDepartmentList, DepartmentVo.class);
        for (DepartmentVo departmentVo : departmentVos) {
            List<DepartmentVo> allChild = getAllChild(departmentVo.getId(), departmentVo);
            if (!CollectionUtils.isEmpty(allChild)) {
                vo.setChild(allChild);
            }
        }
        vo.setChild(departmentVos);
        return departmentVos;
    }


}
