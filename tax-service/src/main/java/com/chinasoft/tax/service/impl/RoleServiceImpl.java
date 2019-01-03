package com.chinasoft.tax.service.impl;

import com.chinasoft.tax.common.utils.IDGeneratorUtils;
import com.chinasoft.tax.common.utils.MyBeanUtils;
import com.chinasoft.tax.constant.CommonStatus;
import com.chinasoft.tax.dao.*;
import com.chinasoft.tax.enums.ExceptionCode;
import com.chinasoft.tax.po.*;
import com.chinasoft.tax.service.RoleService;
import com.chinasoft.tax.vo.*;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 角色管理实现类
 * @Date: Created in 20:30 2018/10/23
 * @Author: yaochenglong
 */

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private TRoleMapper tRoleMapper;
    @Autowired
    private TRoleMenuMapper tRoleMenuMapper;
    @Autowired
    private TRoleCompanyMapper tRoleCompanyMapper;
    @Autowired
    private TRoleElementMapper tRoleElementMapper;
    @Autowired
    private TRolePermissionMapper tRolePermissionMapper;
    @Autowired
    private TPermissionMapper tPermissionMapper;

    /**
     * 获取所有角色
     * @return
     */
    @Override
    public List<RoleVo> getAll() {
        List<TRole> tRoles = tRoleMapper.selectAll();
        List<RoleVo> roleVoList = MyBeanUtils.copyList(tRoles, RoleVo.class);
        return roleVoList;
    }

    @Override
    public RoleVo getById(String id) {
        TRole tRole = tRoleMapper.selectByPrimaryKey(id);
        RoleVo roleVo = new RoleVo();
        BeanUtils.copyProperties(tRole,roleVo);
        return roleVo;
    }
    /**
     * 分页查找所有角色
     * @param pageVo
     * @param vo
     * @return
     */
    @Override
    public MyPageInfo<RoleVo> findAllByPage(PageVo pageVo, RoleVo vo){
        PageHelper.startPage(pageVo.getPageNumber(),pageVo.getPageSize());
        Example example = new Example(TRole.class);
        example.setOrderByClause("create_time");
        if(vo!=null){
            Example.Criteria criteria = example.createCriteria();
            criteria.andLike("name",vo.getName());
        }
        List<TRole> tRoles = tRoleMapper.selectByExample(example);
        int count = tRoleMapper.selectCountByExample(example);
        List<RoleVo> roleVoList = MyBeanUtils.copyList(tRoles, RoleVo.class);
        for (RoleVo roleVo : roleVoList) {
            List<TPermission> tPermissionList = tPermissionMapper.findByRoleId(roleVo.getId());
            List<PermissionVo> permissionVoList = MyBeanUtils.copyList(tPermissionList, PermissionVo.class);
            roleVo.setPermissions(permissionVoList);
        }
        MyPageInfo<RoleVo> pageInfo = new MyPageInfo<>(roleVoList);
        pageInfo.setTotalElements(count);
        pageInfo.setPageNum(pageVo.getPageNumber());
        return pageInfo;
    }


    /**
     * 分配菜单权限
     * @param roleId
     * @param menuIds
     */
    @Override
    public void assignMenu(String roleId,String menuIds){
        log.info("分配权限，入参roleId="+roleId+",menuIds=["+menuIds+"]");
        Example tRoleMenuVoExample = new Example(TRoleMenu.class);
        Example.Criteria tRoleMenuVoCriteria = tRoleMenuVoExample.createCriteria();
        tRoleMenuVoCriteria.andEqualTo("roleId", roleId);
        tRoleMenuMapper.deleteByExample(tRoleMenuVoExample);
        List<TRoleMenu> tRoleMenuList = new ArrayList<>();
        for (String menuId : menuIds.split(",")) {
            TRoleMenu tRoleMenu = new TRoleMenu();
            tRoleMenu.setId(IDGeneratorUtils.getUUID32());
            tRoleMenu.setRoleId(roleId);
            tRoleMenu.setMenuId(menuId);
            tRoleMenu.setCreateTime(new Date());
            tRoleMenu.setIsDel(CommonStatus.NORMAL_FLAG);
            tRoleMenuMapper.insertSelective(tRoleMenu);
            tRoleMenuList.add(tRoleMenu);
        }
        //todo 批量插入有问题，坑，回头自己写批量插入
        //tRoleMenuMapper.insertList(tRoleMenuList);
        log.info("分配权限成功，入参roleId="+roleId+",permissionIds=["+tRoleMenuList+"]");
    }

    @Override
    public void assignPermission(String roleId, String permissionIds) {
        log.info("分配权限，入参roleId="+roleId+",permissionIds=["+permissionIds+"]");
//        Example tRoleMenuVoExample = new Example(TRoleMenu.class);
//        Example.Criteria tRoleMenuVoCriteria = tRoleMenuVoExample.createCriteria();
//        tRoleMenuVoCriteria.andEqualTo("roleId", roleId);
//        tRoleMenuMapper.deleteByExample(tRoleMenuVoExample);


        delRolePermission(roleId);


        List<TRolePermission> tRoleMenuList = new ArrayList<>();
        for (String permissionId : permissionIds.split(",")) {
            TRolePermission tRolePermission = new TRolePermission();
            tRolePermission.setId(IDGeneratorUtils.getUUID32());
            tRolePermission.setRoleId(roleId);
            tRolePermission.setPermissionId(permissionId);
            tRolePermission.setCreateTime(new Date());
            tRolePermissionMapper.insertSelective(tRolePermission);
            tRoleMenuList.add(tRolePermission);
        }
        //todo 批量插入有问题，坑，回头自己写批量插入
        //tRoleMenuMapper.insertList(tRoleMenuList);
        log.info("分配权限成功，入参roleId="+roleId+",permissionIds=["+tRoleMenuList+"]");
    }

    /*public void delRolePermission(String roleId) {
        Example trolePermissionExmaple = new Example(TRolePermission.class);
        Example.Criteria criteria = trolePermissionExmaple.createCriteria();
        criteria.andEqualTo("roleId",roleId);
        tRolePermissionMapper.deleteByExample(trolePermissionExmaple);
    }*/

    /**
     * 分配公司
     * @param roleId
     * @param companyIds
     */
    @Override
    public void assiginCompany(String roleId,String companyIds){
        log.info("分配公司，入参roleId="+roleId+",companyIds=["+companyIds+"]");
        Example tRoleMenuVoExample = new Example(TRoleMenu.class);
        Example.Criteria tRoleMenuVoCriteria = tRoleMenuVoExample.createCriteria();
        tRoleMenuVoCriteria.andEqualTo("roleId", roleId);
        tRoleMenuMapper.deleteByExample(tRoleMenuVoExample);
        List<TRoleCompany> tRoleCompanyList = new ArrayList<>();
        for (String companyId : companyIds.split(",")) {
            TRoleCompany tRoleCompany = new TRoleCompany();
            tRoleCompany.setId(IDGeneratorUtils.getUUID32());
            tRoleCompany.setRoleId(roleId);
            tRoleCompany.setCompanyId(companyId);
            tRoleCompanyList.add(tRoleCompany);
            tRoleCompanyMapper.insertSelective(tRoleCompany);
        }
        //TODO 批量插入有问题
        //tRoleCompanyMapper.insertList(tRoleCompanyList);
        log.info("分配公司成功，入参roleId="+roleId+",companyIds=["+tRoleCompanyList+"]");
    }


    /**
     * 分配元素
     * @param roleId
     * @param elementIds
     */
    @Override
    public void assiginElement(String roleId,String elementIds){
        log.info("分配公司，入参roleId="+roleId+",companyIds=["+elementIds+"]");
        Example tRoleElementVoExample = new Example(TRoleElement.class);
        Example.Criteria tRoleMenuVoCriteria = tRoleElementVoExample.createCriteria();
        tRoleMenuVoCriteria.andEqualTo("roleId", roleId);
        tRoleElementMapper.deleteByExample(tRoleElementVoExample);
        List<TRoleElement> tRoleElementList = new ArrayList<>();
        for (String elementId : elementIds.split(",")) {
            TRoleElement tRoleElement = new TRoleElement();
            tRoleElement.setId(IDGeneratorUtils.getUUID32());
            tRoleElement.setRoleId(roleId);
            tRoleElement.setElementId(elementId);
            tRoleElementList.add(tRoleElement);
            tRoleElementMapper.insertSelective(tRoleElement);
        }
        //TODO 批量插入有问题
        //tRoleCompanyMapper.insertList(tRoleCompanyList);
        log.info("分配元素成功，入参roleId="+roleId+",elementIds=["+tRoleElementList+"]");
    }


    /*@Override
    public void addRole(RoleVo roleVo) {
        log.info("添加角色,入参roleVo=["+roleVo.toString()+"]");
        if(StringUtils.isEmpty(roleVo.getName())|| StringUtils.isEmpty(roleVo.getCode())){
            throw new BizException(ExceptionCode.REQUEST_PARAM_MISSING);
        }
        TRole tRole = new TRole();
        BeanUtils.copyProperties(roleVo,tRole);
        Example example = new Example(TRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name",roleVo.getName());
        criteria.orEqualTo("code", roleVo.getCode());
        criteria.orEqualTo("processKey",roleVo.getProcessKey());
        int i = tRoleMapper.selectCountByExample(example);
        if (i > 1) {
            log.error("该角色名称或者流程角色已经存在!");
            throw new BizException(ExceptionCode.ROLE_AREADY_EXIST.getCode(),ExceptionCode.ROLE_AREADY_EXIST.getMsg());

        }
        tRole.setId(IDGeneratorUtils.getUUID32());
        tRoleMapper.insertSelective(tRole);
        log.info("添加角色成功，输出roleVo=["+tRole.toString()+"]");
    }

    @Transactional
    @Override
    public void editRole(RoleVo roleVo) {
        if(StringUtils.isEmpty(roleVo.getId())||StringUtils.isEmpty(roleVo.getName())|| StringUtils.isEmpty(roleVo.getCode())){
            throw new BizException(ExceptionCode.REQUEST_PARAM_MISSING);
        }
        log.info("修改角色,入参roleVo=["+roleVo.toString()+"]");

        //TRole oriRoleVo = tRoleMapper.selectByPrimaryKey(roleVo.getId());
        TRole tRole = new TRole();
        BeanUtils.copyProperties(roleVo,tRole);
        if (!StringUtils.isEmpty(roleVo.getProcessKey())) {
            if (!"none".equals(roleVo.getProcessKey())) {
                Example roleExmaple = new Example(TRole.class);
                roleExmaple.createCriteria().andEqualTo("processKey", roleVo.getProcessKey());
                List<TRole> tRoles = tRoleMapper.selectByExample(roleExmaple);
                if (tRoles.size() > 0) {
                    throw new BizException(ExceptionCode.ROLE_AREADY_EXIST.getCode(), ExceptionCode.ROLE_AREADY_EXIST.getMsg());
                }
            }

        }

        tRoleMapper.updateByPrimaryKeySelective(tRole);
        log.info("修改角色成功，输出roleVo=["+tRole.toString()+"]");
    }*/


    @Override
    public void addRole(RoleVo roleVo) {
        log.info("添加角色,入参roleVo=["+roleVo.toString()+"]");
        if(StringUtils.isEmpty(roleVo.getName())|| StringUtils.isEmpty(roleVo.getCode())){
            throw new BizException(ExceptionCode.REQUEST_PARAM_MISSING);
        }
        TRole tRole = new TRole();
        BeanUtils.copyProperties(roleVo,tRole);
        Example example = new Example(TRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name",roleVo.getName());
        criteria.orEqualTo("code", roleVo.getCode());
        criteria.orLike("processKey","%"+roleVo.getProcessKey()+"%");
        int i = tRoleMapper.selectCountByExample(example);
        if(i>0){
            log.error("该角色名称或者流程角色已经存在!");
            throw new BizException(ExceptionCode.ROLE_AREADY_EXIST.getCode(),ExceptionCode.ROLE_AREADY_EXIST.getMsg());

        }
        tRole.setId(IDGeneratorUtils.getUUID32());
        tRole.setCreateTime(new Date());
        tRoleMapper.insertSelective(tRole);
        log.info("添加角色成功，输出roleVo=["+tRole.toString()+"]");
    }

    @Transactional
    @Override
    public void editRole(RoleVo roleVo) {
        if(StringUtils.isEmpty(roleVo.getId())||StringUtils.isEmpty(roleVo.getName())|| StringUtils.isEmpty(roleVo.getCode())){
            throw new BizException(ExceptionCode.REQUEST_PARAM_MISSING);
        }
        log.info("修改角色,入参roleVo=["+roleVo.toString()+"]");

        //TRole oriRoleVo = tRoleMapper.selectByPrimaryKey(roleVo.getId());
        TRole tRole = new TRole();
        BeanUtils.copyProperties(roleVo,tRole);
        if(!StringUtils.isEmpty(roleVo.getProcessKey())){
            if(!"none".equals(roleVo.getProcessKey())){
                Example roleExmaple = new Example(TRole.class);
                roleExmaple.createCriteria().andLike("processKey","%"+roleVo.getProcessKey()+"%");
                List<TRole> tRoles = tRoleMapper.selectByExample(roleExmaple);
                if(tRoles.size()>0){
                    throw new BizException(ExceptionCode.ROLE_AREADY_EXIST.getCode(),ExceptionCode.ROLE_AREADY_EXIST.getMsg());
                }
            }

        }

        tRoleMapper.updateByPrimaryKeySelective(tRole);
        log.info("修改角色成功，输出roleVo=["+tRole.toString()+"]");
    }

    @Override
    public void delById(String id) {
        log.info("删除角色,入参id=["+id+"]");
        TRole tRole = tRoleMapper.selectByPrimaryKey(id);
        //查询该角色下的权限和公司，如果有，则提示无法删除
        TRolePermission tRolePermission = new TRolePermission();
        tRolePermission.setRoleId(id);
        List<TRolePermission> tRolePermissionsList = tRolePermissionMapper.select(tRolePermission);
        if(!CollectionUtils.isEmpty(tRolePermissionsList)){
            throw new BizException(ExceptionCode.DEL_ROLE_HAS_PERMISSION);
        }
        TRoleCompany tRoleCompany = new TRoleCompany();
        tRoleCompany.setRoleId(id);
        List<TRoleCompany> tRoleCompanyList = tRoleCompanyMapper.select(tRoleCompany);
        if(!CollectionUtils.isEmpty(tRoleCompanyList)){
            throw new BizException(ExceptionCode.DEL_ROLE_HAS_COMPANY);
        }
        if(tRole!=null){
            tRole.setIsDel(CommonStatus.DEL_FLAG);
            tRoleMapper.deleteByPrimaryKey(id);
            log.info("修改角色成功，输出id=["+tRole.toString()+"]");
        }else{
            log.info("未能找到要删除的角色id="+id);
        }
    }

    @Override
    public void delRolePermission(String roleId) {
        Example trolePermissionExmaple = new Example(TRolePermission.class);
        Example.Criteria criteria = trolePermissionExmaple.createCriteria();
        criteria.andEqualTo("roleId",roleId);
        tRolePermissionMapper.deleteByExample(trolePermissionExmaple);
    }
}
