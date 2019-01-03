package com.chinasoft.tax.service.impl;

import com.chinasoft.tax.common.utils.MyBeanUtils;
import com.chinasoft.tax.dao.TRolePermissionMapper;
import com.chinasoft.tax.po.TRolePermission;
import com.chinasoft.tax.service.RolePermissionService;
import com.chinasoft.tax.vo.RolePermissionVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Description: 角色-权限服务类
 * @Date: Created in 20:44 2018/10/25
 * @Author: yaochenglong
 */
@Slf4j
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private TRolePermissionMapper tRolePermissionMapper;

    @Override
    public List<RolePermissionVo> findByPermissionId(String permissionId) {
        Example example = new Example(TRolePermission.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("permissionId",permissionId);
        List<TRolePermission> tRolePermissionList = tRolePermissionMapper.selectByExample(example);
        List<RolePermissionVo> rolePermissionVoList = MyBeanUtils.copyList(tRolePermissionList, RolePermissionVo.class);
        return rolePermissionVoList;
    }

    @Override
    public void deleteByRoleId(String roleId) {
        log.info("删除角色-权限，入参roleId="+roleId);
        Example example = new Example(TRolePermission.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId",roleId);
        tRolePermissionMapper.deleteByExample(example);
        log.info("删除角色-权限成功");
    }
}
