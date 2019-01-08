package com.chinasoft.tax.config.security;

import com.chinasoft.tax.common.utils.MyBeanUtils;
import com.chinasoft.tax.dao.TUserRoleMapper;
import com.chinasoft.tax.enums.ExceptionCode;
import com.chinasoft.tax.po.TRole;
import com.chinasoft.tax.service.UserService;
import com.chinasoft.tax.vo.BizException;
import com.chinasoft.tax.vo.RoleVo;
import com.chinasoft.tax.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CurrentUserUtils {

    @Autowired
    private  UserService userService;
    @Autowired
    private TUserRoleMapper tUserRoleMapper;

    /**
     * 获取当前用户名称
     * @return
     */
    public  String getUserName(){
        try {
            UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return user.getUsername();

        }catch (Exception e){
            throw new BizException(ExceptionCode.TOKEN_ERROR);
        }
    }

    /**
     * 获取当前用户id
     * @return
     */
    public  String getUserId(){
        try {
            UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserVo vo = userService.getUserByUserName(user.getUsername());
            if(vo!=null){
                return vo.getId();
            }

        }catch (Exception e){
            throw new BizException(ExceptionCode.TOKEN_ERROR);
        }
        return null;
    }

    public List<RoleVo> getCurrUserRole(){
        try {
            UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserVo vo = userService.getUserByUserName(user.getUsername());
            List<TRole> troles = tUserRoleMapper.findByUserId(vo.getId());
            List<RoleVo> roleVos = MyBeanUtils.copyList(troles, RoleVo.class);
            if(roleVos!=null){
                return roleVos;
            }

        }catch (Exception e){
            throw new BizException(ExceptionCode.TOKEN_ERROR);
        }
        return null;
    }
}
