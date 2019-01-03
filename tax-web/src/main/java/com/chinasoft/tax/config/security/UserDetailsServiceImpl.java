package com.chinasoft.tax.config.security;

import com.chinasoft.tax.enums.ExceptionCode;
import com.chinasoft.tax.service.UserService;
import com.chinasoft.tax.vo.BizException;
import com.chinasoft.tax.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserVo user = userService.getUserByUserName(username);
        if(user==null){
            throw new BizException(ExceptionCode.LOGIN_INFO_IS_NOT_EXIST);
        }
        return new SecurityUserDetails(user);
    }
}
