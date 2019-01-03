package com.chinasoft.tax.config.security.jwt;

import com.chinasoft.tax.beanUtils.IpInfoUtil;
import com.chinasoft.tax.beanUtils.ResponseUtils;
import com.chinasoft.tax.common.utils.ResponseUtil;
import com.chinasoft.tax.config.security.SecurityUserDetails;
import com.chinasoft.tax.constant.SecurityConstant;
import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 登录成功处理类
 * @Date: Created in 20:13 2018/10/2@Component7
 * @Author: yaochenglong
 */
@Slf4j
@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Value("${tax.tokenExpireTime}")
    private Integer tokenExpireTime  =10 ;

    @Value("${tax.saveLoginTime}")
    private Integer saveLoginTime = 7;

    @Autowired
    private IpInfoUtil ipInfoUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        //用户选择保存登录状态几天
        String saveTime = request.getParameter(SecurityConstant.SAVE_LOGIN);
        if(StringUtils.isNotBlank(saveTime)&&Boolean.valueOf(saveTime)){
            tokenExpireTime = saveLoginTime * 60 * 24;
        }
        String username = ((UserDetails)authentication.getPrincipal()).getUsername();
        List<GrantedAuthority> list = (List<GrantedAuthority>) ((UserDetails)authentication.getPrincipal()).getAuthorities();
        List<String> authorities = new ArrayList<>();
        for(GrantedAuthority g : list){
            authorities.add(g.getAuthority());
        }
        ipInfoUtil.getUrl(request);
        //登陆成功生成JWT
        String token = Jwts.builder()
                //主题 放入用户名
                .setSubject(username)
                //自定义属性 放入用户拥有请求权限
                .claim(SecurityConstant.AUTHORITIES, new Gson().toJson(authorities))
                //失效时间
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpireTime * 60 * 1000))
                //签名算法和密钥
                .signWith(SignatureAlgorithm.HS512, SecurityConstant.JWT_SIGN_KEY)
                .compact();
        token = SecurityConstant.TOKEN_SPLIT + token;

        //ResponseUtil.out(response, ResponseUtil.resultMap(true,200,"登录成功", token));
        ResponseUtils.out(response,ResponseUtil.responseBody("0","登录成功",null,token));
        //ResponseUtil.responseBody("0","登录成功",null,token);
    }
}
