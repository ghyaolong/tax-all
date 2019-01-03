package com.chinasoft.tax.config.security;

import com.chinasoft.tax.enums.ExceptionCode;
import com.chinasoft.tax.service.UserService;
import com.chinasoft.tax.vo.BizException;
import com.chinasoft.tax.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserUtils {

    @Autowired
    private  UserService userService;

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
}
