package com.chinasoft.tax.controller;

import com.chinasoft.tax.annotation.SystemLog;
import com.chinasoft.tax.aop.EnableGameleyLog;
import com.chinasoft.tax.common.utils.ResponseUtil;
import com.chinasoft.tax.config.security.permission.MySecurityMetadataSource;
import com.chinasoft.tax.constant.CommonConstant;
import com.chinasoft.tax.dao.TPermissionMapper;
import com.chinasoft.tax.enums.ExceptionCode;
import com.chinasoft.tax.service.PermissionService;
import com.chinasoft.tax.service.RolePermissionService;
import com.chinasoft.tax.vo.BizException;
import com.chinasoft.tax.vo.Message;
import com.chinasoft.tax.vo.PermissionVo;
import com.chinasoft.tax.vo.RolePermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Description: 权限管理
 * @Date: Created in 20:20 2018/10/25
 * @Author: yaochenglong
 */
@RequestMapping("/permission")
@RestController
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private MySecurityMetadataSource mySecurityMetadataSource;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;



    /**
     * 获取用户页面菜单数据
     * @param userId
     * @return
     */
    @SystemLog(description = "获取用户页面菜单数据")
    @RequestMapping(value = "/getMenuList/{userId}",method = RequestMethod.GET)
    @Cacheable(key = "'userMenuList:'+#userId")
    public Message getAllMenuList(@PathVariable String userId){

        //用户所有权限 已排序去重
        List<PermissionVo> list = permissionService.findByUserId(userId);

        List<PermissionVo> menuList = new ArrayList<>();
        //筛选一级页面
        for(PermissionVo p : list){
            if(CommonConstant.PERMISSION_PAGE.equals(p.getType())&&CommonConstant.LEVEL_ONE.equals(p.getLevel())){
                menuList.add(p);
            }
        }
        //筛选二级页面
        List<PermissionVo> secondMenuList = new ArrayList<>();
        for(PermissionVo p : list){
            if(CommonConstant.PERMISSION_PAGE.equals(p.getType())&&CommonConstant.LEVEL_TWO.equals(p.getLevel())){
                secondMenuList.add(p);
            }
        }
        //筛选二级页面拥有的按钮权限
        List<PermissionVo> buttonPermissions = new ArrayList<>();
        for(PermissionVo p : list){
            if(CommonConstant.PERMISSION_OPERATION.equals(p.getType())&&CommonConstant.LEVEL_THREE.equals(p.getLevel())){
                buttonPermissions.add(p);
            }
        }

        //匹配二级页面拥有权限
        for(PermissionVo p : secondMenuList){
            List<String> permTypes = new ArrayList<>();
            for(PermissionVo pe : buttonPermissions){
                if(p.getId().equals(pe.getParentId())){
                    permTypes.add(pe.getButtonType());
                }
            }
            p.setPermTypes(permTypes);
        }
        //匹配一级页面拥有二级页面
        for(PermissionVo p : menuList){
            List<PermissionVo> secondMenu = new ArrayList<>();
            for(PermissionVo pe : secondMenuList){
                if(p.getId().equals(pe.getParentId())){
                    secondMenu.add(pe);
                }
            }
            p.setChildren(secondMenu);
        }
        return ResponseUtil.responseBody(menuList);
    }


    /**
     * 获取权限菜单树
     * @return
     */
    @GetMapping("/getAllList")
    @Cacheable(key = "'allList'")
    public Message getAllList(){

        //一级
        List<PermissionVo> list = permissionService.findByLevelOrderBySortOrder(CommonConstant.LEVEL_ONE);
        //二级
        for(PermissionVo p1 : list){
            List<PermissionVo> children1 = permissionService.findByParentIdOrderBySortOrder(p1.getId());
            p1.setChildren(children1);
            //三级
            for(PermissionVo p2 : children1){
                List<PermissionVo> children2 = permissionService.findByParentIdOrderBySortOrder(p2.getId());
                p2.setChildren(children2);
            }
        }
        return ResponseUtil.responseBody(list);
    }



    @SystemLog(description = "添加权限")
    @PostMapping("/add")
    @CacheEvict(key = "'menuList'")
    public Message add(@RequestBody PermissionVo permission){

        // 判断拦截请求的操作权限按钮名是否已存在
        if(CommonConstant.PERMISSION_OPERATION.equals(permission.getType())){
            List<PermissionVo> list = permissionService.findByTitle(permission.getTitle());
            if(list!=null&&list.size()>0){
                throw new BizException(ExceptionCode.DATA_AREADY_EXIST);
            }
        }
        permissionService.addPermission(permission);
        //重新加载权限
        mySecurityMetadataSource.loadResourceDefine();
        //手动删除缓存
        stringRedisTemplate.delete("permission::allList");
        return ResponseUtil.responseBody(permission);
    }

    @EnableGameleyLog(name = "编辑权限",serviceclass = TPermissionMapper.class)
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public Message edit(@RequestBody PermissionVo permission){

        // 判断拦截请求的操作权限按钮名是否已存在
        if(CommonConstant.PERMISSION_OPERATION.equals(permission.getType())){
            // 若名称修改
            PermissionVo p = permissionService.findById(permission.getId());
            if(!p.getTitle().equals(permission.getTitle())){
                List<PermissionVo> list = permissionService.findByTitle(permission.getTitle());
                if(list!=null&&list.size()>0){
                    throw new BizException(ExceptionCode.DATA_AREADY_EXIST);
                }
            }
        }
        permissionService.editPermission(permission);
        mySecurityMetadataSource.loadResourceDefine();
        //手动批量删除缓存
        Set<String> keys = stringRedisTemplate.keys("userPermission:" + "*");
        stringRedisTemplate.delete(keys);
        Set<String> keysUser = stringRedisTemplate.keys("user:" + "*");
        stringRedisTemplate.delete(keysUser);
        Set<String> keysUserMenu = stringRedisTemplate.keys("permission::userMenuList:*");
        stringRedisTemplate.delete(keysUserMenu);
        stringRedisTemplate.delete("permission::allList");
        return ResponseUtil.responseBody(permission);
    }

    @SystemLog(description = "删除权限")
    @RequestMapping(value = "/delByIds/{ids}",method = RequestMethod.DELETE)
    @CacheEvict(key = "'menuList'")
    public Message delByIds(@PathVariable String ids){

        for(String id:ids.split(",")){
            List<RolePermissionVo> list = rolePermissionService.findByPermissionId(id);
            if(list!=null&&list.size()>0){
                throw new BizException(ExceptionCode.ROLE_HAS_PERMISSION);
            }
        }
        for(String id:ids.split(",")){
            permissionService.delPermissionById(id);
        }
        mySecurityMetadataSource.loadResourceDefine();
        //手动删除缓存
        //手动删除缓存
        stringRedisTemplate.delete("permission::allList");
        return ResponseUtil.responseBody("删除数据成功");
    }

}
