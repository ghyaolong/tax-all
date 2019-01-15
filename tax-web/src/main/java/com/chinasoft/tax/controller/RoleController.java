package com.chinasoft.tax.controller;

import com.chinasoft.tax.annotation.SystemLog;
import com.chinasoft.tax.aop.EnableGameleyLog;
import com.chinasoft.tax.common.utils.ResponseUtil;
import com.chinasoft.tax.dao.TRoleMapper;
import com.chinasoft.tax.enums.ExceptionCode;
import com.chinasoft.tax.service.RolePermissionService;
import com.chinasoft.tax.service.RoleService;
import com.chinasoft.tax.vo.BizException;
import com.chinasoft.tax.vo.Message;
import com.chinasoft.tax.vo.PageVo;
import com.chinasoft.tax.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @Description: 用户角色管理
 * @Date: Created in 19:43 2018/10/23
 * @Author: yaochenglong
 */

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 查询所有角色
     * @return
     */
    @GetMapping("/all")
    public Message getAllRole(){
        return ResponseUtil.responseBody(roleService.getAll());
    }

    /**
     * 分页获取角色
     * @param pageVo
     * @return
     */
    @PostMapping("/all/page")
    public Message getAllRoleByPage(@RequestBody PageVo pageVo){
        return ResponseUtil.responseBody(roleService.findAllByPage(pageVo,null));
    }

    /**
     * 设置或取消默认角色
     * @param modelMap     id和isDefault
     * @return
     */
    @RequestMapping(value = "/setDefault",method = RequestMethod.POST)
    public Message setDefault(@RequestBody ModelMap modelMap){
        String id = (String) modelMap.get("id");
        Boolean isDefault = (Boolean) modelMap.get("isDefault");
        /*@RequestParam String id,
        @RequestParam Boolean isDefault*/

        RoleVo roleVo = roleService.getById(id);
        if(roleVo==null){
            throw new BizException(ExceptionCode.ROLE_NOT_EXIST);
            //return new ResultUtil<Object>().setErrorMsg("角色不存在");
        }
        roleVo.setDefaultRole(isDefault);
        roleService.editRole(roleVo);
        return ResponseUtil.responseBody("设置成功");
    }

    /**
     * 添加角色
     * @return
     */
    @SystemLog(description = "添加角色")
    @PostMapping("/add")
    public Message addRole(@RequestBody RoleVo roleVo){
        roleService.addRole(roleVo);
        return ResponseUtil.responseBody("添加角色成功");
    }

    /**
     * 修改角色
     * @return
     */
    @EnableGameleyLog(name = "修改角色",serviceclass = TRoleMapper.class)
    @PostMapping("/edit")
    public Message editRole(@RequestBody RoleVo roleVo){
        roleService.editRole(roleVo);
        return ResponseUtil.responseBody("修改角色成功");
    }

    /**
     * 重新分配权限
     * @param map
     *  roleId
     *  menuIds: 已","隔开
     * @return
     */
    /*@PostMapping("/assignPermission")
    public Message assignPermission(@RequestBody ModelMap map){
        String roleId = (String) map.get("roleId");
        String permissionIds = (String) map.get("permissionIds");
        if(StringUtils.isEmpty(roleId)){
            throw new BizException(ExceptionCode.REQUEST_PARAM_MISSING);
        }
        if(StringUtils.isEmpty(permissionIds)){
            //删除该角色下的所有权限
            roleService.delRolePermission(roleId);
        }else{
            roleService.assignPermission(roleId,permissionIds);
        }

        //手动批量删除缓存
        Set<String> keysUser = stringRedisTemplate.keys("user:" + "*");
        stringRedisTemplate.delete(keysUser);
        Set<String> keysUserRole = stringRedisTemplate.keys("userRole:" + "*");
        stringRedisTemplate.delete(keysUserRole);
        Set<String> keysUserPerm = stringRedisTemplate.keys("userPermission:" + "*");
        stringRedisTemplate.delete(keysUserPerm);
        Set<String> keysUserMenu = stringRedisTemplate.keys("permission::userMenuList:*");
        stringRedisTemplate.delete(keysUserMenu);
        return ResponseUtil.responseBody("分配权限成功");
    }*/
    @PostMapping("/assignPermission")
    public Message assignPermission(@RequestBody ModelMap map){
        String roleId = (String) map.get("roleId");
        String permissionIds = (String) map.get("permissionIds");
        if(StringUtils.isEmpty(roleId)){
            throw new BizException(ExceptionCode.REQUEST_PARAM_MISSING);
        }
        if(StringUtils.isEmpty(permissionIds)){
            //删除该角色下的所有权限
            roleService.delRolePermission(roleId);
        }else{
            roleService.assignPermission(roleId,permissionIds);
        }

        //手动批量删除缓存
        Set<String> keysUser = stringRedisTemplate.keys("user:" + "*");
        stringRedisTemplate.delete(keysUser);
        Set<String> keysUserRole = stringRedisTemplate.keys("userRole:" + "*");
        stringRedisTemplate.delete(keysUserRole);
        Set<String> keysUserPerm = stringRedisTemplate.keys("userPermission:" + "*");
        stringRedisTemplate.delete(keysUserPerm);
        Set<String> keysUserMenu = stringRedisTemplate.keys("permission::userMenuList:*");
        stringRedisTemplate.delete(keysUserMenu);
        return ResponseUtil.responseBody("分配权限成功");
    }

    /**
     * 重新分配公司
     * @param map 包括参数
     * roleId
     * companyIds：已","隔开
     *
     * @return
     */
    @PostMapping("/assignCompany")
    public Message assignCompany(@RequestBody ModelMap map){
        String roleId = (String) map.get("roleId");
        String companyIds = (String) map.get("companyIds");
        if(StringUtils.isEmpty(roleId)|| StringUtils.isEmpty(companyIds)){
            throw new BizException(ExceptionCode.REQUEST_PARAM_MISSING);
        }
        roleService.assiginCompany(roleId,companyIds);
        return ResponseUtil.responseBody("分配公司成功");
    }



    /**
     * 重新分配元素
     * @param map 包括参数
     * roleId
     * companyIds：已","隔开
     *
     * @return
     */
    @PostMapping("/assignElement")
    public Message assignElement(@RequestBody ModelMap map){
        String roleId = (String) map.get("roleId");
        String elementIds = (String) map.get("elementIds");
        if(StringUtils.isEmpty(roleId)|| StringUtils.isEmpty(elementIds)){
            throw new BizException(ExceptionCode.REQUEST_PARAM_MISSING);
        }
        roleService.assiginElement(roleId,elementIds);
        return ResponseUtil.responseBody("分配元素成功");
    }

    /**
     * 分配api
     * @param map
     * @return
     */
    @PostMapping("/assignApis")
    public Message assignApis(@RequestBody ModelMap map){

        return ResponseUtil.responseBody("分配Api成功");
    }

    /**
     * 删除角色
     * @param ids
     * @return
     */
    @RequestMapping(value = "/del/{ids}",method = RequestMethod.DELETE)
    public Message delRoleByIds(@PathVariable String[] ids){
        for(String id:ids){
            if("496138616573952".equals(id)){
                throw new BizException(ExceptionCode.CANNOT_DELETE);
            }
            roleService.delById(id);
        }
        return ResponseUtil.responseBody("删除角色成功");
    }


}
