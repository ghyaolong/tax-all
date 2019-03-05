package com.chinasoft.tax.controller;

import com.chinasoft.tax.annotation.SystemLog;
import com.chinasoft.tax.aop.EnableGameleyLog;
import com.chinasoft.tax.common.utils.ResponseUtil;
import com.chinasoft.tax.dao.TUserMapper;
import com.chinasoft.tax.enums.ExceptionCode;
import com.chinasoft.tax.qo.UserQo;
import com.chinasoft.tax.service.UserCompanyService;
import com.chinasoft.tax.service.UserService;
import com.chinasoft.tax.vo.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:
 * @Author: yaochenglong
 * @Date: 16:21 2018/10/21
 */
@Slf4j
@RestController
@RequestMapping("/user")
@CacheConfig(cacheNames = "user")
@Transactional
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserCompanyService userCompanyService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 多条件分页获取用户列表
     * @return
     */
    @PostMapping("/all")
    public Message getAllUser(@RequestBody UserQo userQo){
        PageVo pageVo = userQo.getPageVo();
        SearchVo searchVo = userQo.getSearchVo();
        UserVo userVo = userQo.getUserVo();
        MyPageInfo<UserVo> pageInfo = null;
        try {
            pageInfo = userService.findByCondition(pageVo,searchVo,userVo);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ResponseUtil.responseBody(pageInfo);
    }

    /**
     *获取当前登录用户接口
     * @return
     */
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public Message getUserInfo(){

        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserVo userVo = userService.getUserByUserName(user.getUsername());
        // 清除持久上下文环境 避免后面语句导致持久化
        //entityManager.clear();
        userVo.setPassword(null);
        return ResponseUtil.responseBody(userVo);
    }

    /**
     * 用户登录
     * @param vo
     * @return
     *//*
    @PostMapping("/login")
    public Message login(UserVo vo) {
        if(StringUtils.isEmpty(vo.getUsername())||StringUtils.isEmpty(vo.getPassword())){
            throw new BizException(ExceptionCode.USER_NAME_IS_NOT_NULL);
        }
        if(StringUtils.isEmpty(vo.getPassword())){
            throw new BizException(ExceptionCode.PASSWORD_IS_NOT_NULL);
        }
        boolean bool = userService.login(vo);
        //String token = jwtTokenUtil.generateToken(userDetails);
        //log.info("用户登录token:"+token);
        return ResponseUtil.responseBody("登录成功");
    }*/


    /**
     * 用户登出
     * 使用jwt所以，此处需要前端做处理。
     * @param vo
     * @return
     */
    public Message logout(UserVo vo){
        return ResponseUtil.responseBody("登出成功");
    }

    /**
     * 修改密码
     *
     * @param map
     * map 参数包含  id   password  newPass 三个参数
     * @return
     */
    @EnableGameleyLog(name = "修改密码",serviceclass = TUserMapper.class)
    @PostMapping("/editPassword")
    public Message editPassword(@RequestBody ModelMap map) {
        String id = (String) map.get("id");
        String password = (String) map.get("password");
        String newPassword = (String) map.get("newPass");

        if(StringUtils.isEmpty(id)||StringUtils.isEmpty(password)||StringUtils.isEmpty(newPassword)){
            throw new BizException(ExceptionCode.CONFIRM_PASSWORD_NOT_MATCH);
        }
        UserVo userVo = userService.getUserById(id);
        if(!new BCryptPasswordEncoder().matches(password,userVo.getPassword())){
            throw new BizException(ExceptionCode.OLD_PASSWORD_INCORRECT);
        }
        String newEncryptPass= new BCryptPasswordEncoder().encode(newPassword);
        userVo.setPassword(newEncryptPass);
        userService.updatePassord(userVo);
        return ResponseUtil.responseBody("修改成功");
    }

    /**
     * 这里不存在注册用户，所有的账户需要管理员去添加，去分配
     * 后台管理员添加用户，
     * @param vo
     * @return
     */
    @SystemLog(description = "添加用户")
    @PostMapping("/add")
    public Message add(@RequestBody UserVo vo){
        if(vo==null){
            throw new BizException(ExceptionCode.REQUEST_PARAM_ERROR);
        }
        if(StringUtils.isEmpty(vo.getUsername())||
           StringUtils.isEmpty(vo.getPassword())||
           StringUtils.isEmpty(vo.getWorkNumber())
           ){
            throw new BizException(ExceptionCode.REQUEST_PARAM_ERROR);
        }

//        //用户名：英文+数字
//        boolean b = checkInput(vo.getUsername());
//        if(!b){
//            throw new BizException(ExceptionCode.REQUEST_PARAM_ERROR.getCode(),"用户名只能包含数字＋英文");
//        }
        vo.setRealName(vo.getUsername());
        userService.addUser(vo);
        return ResponseUtil.responseBody("添加成功");
    }

    private boolean checkInput(String input){
        Pattern pattern = Pattern.compile("^[A-Za-z0-9]+$");
        Matcher m = pattern.matcher(input);
        if( !m.matches() ){ //匹配不到,說明輸入的不符合條件
            return false;
        }
        return true;
    }


    /**
     * 分配公司给用户
     * @param map
     * @return
     */
    @SystemLog(description = "分配公司给用户")
    @RequestMapping("/assignCompany")
    public Message assignCompany(@RequestBody ModelMap map){
        String userId = (String) map.get("userId");
        String companyIds = (String) map.get("companyIds");
        if(StringUtils.isEmpty(userId)||StringUtils.isEmpty(companyIds)){
            throw new BizException(ExceptionCode.REQUEST_PARAM_ERROR);
        }
        String[] split = companyIds.split(",");
        userCompanyService.assignCompany(userId, Arrays.asList(split));
        return ResponseUtil.responseBody("分配成功");
    }

    /**
     * 通过id逻辑删除用户
     * @param ids
     * @return
     */
    @SystemLog(description = "通过id逻辑删除用户")
    @RequestMapping(value = "/delByIds/{ids}",method = RequestMethod.DELETE)
    public Message delAllByIds(@PathVariable String[] ids){
        for(String id:ids){
            if("682265633886208".equals(id)){
                throw new BizException(ExceptionCode.CANNOT_DELETE);
            }
            userService.delUser(id);
        }
        return ResponseUtil.responseBody("删除用户成功");
    }

    @EnableGameleyLog(name = "编辑用户",serviceclass = TUserMapper.class)
    @PostMapping("/edit")
    public Message editUser(@RequestBody UserVo userVo){
        userService.editUser(userVo);
        //删除缓存
        stringRedisTemplate.delete("user::"+userVo.getUsername());
        //手动删除缓存
        stringRedisTemplate.delete("userRole::"+userVo.getId());

        return ResponseUtil.responseBody("修改用户成功");
    }

    /**
     * 获取当前登录用户的上级审核人
     * @return
     */
    @GetMapping("/getReviewer")
    public Message getReviewer(){
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserVo userVo = userService.getUserByUserName(user.getUsername());
        List<UserVo> userVoList = userService.getPrevReview(userVo.getId());
        return ResponseUtil.responseBody(userVoList);
    }

    /**
     * 通过userId和流程key获取用户信息
     * @param userId
     * @param key
     * @return
     */
    @GetMapping("/getUserInfo/{userId}/{key}")
    public Message getUserInfoByUserIdAndKey(@PathVariable String userId,@PathVariable String key){
        UserVo userVo = userService.getUserInfoByUserIdAndKey(userId, key);
        return ResponseUtil.responseBody(userVo);
    }

    /**
     * 通过key获取该用户该key的角色的用户
     * @param key
     * @return
     */
    @GetMapping("/getUser/{key}")
    public Message getUserInfoByProcessKey(@PathVariable String key){
        List<UserVo> userVo = userService.getUserInfoByKey(key);
        return  ResponseUtil.responseBody(userVo);
    }

    /**
     * 获取拥有改角色code的所有用户
     * @param roleCode
     * @return
     */
    @GetMapping("/getUsers/{roleCode}")
    public Message getUserByRoleCode(@PathVariable String roleCode){
        List<UserVo> allUserByRoleCode = userService.getAllUserByRoleCode(roleCode);
        return ResponseUtil.responseBody(allUserByRoleCode);
    }

    @GetMapping("/isAdmin")
    public Message isAdmin(){
        return ResponseUtil.responseBody(userService.isAdmin());
    }

    /**
     * 查询申请人(也就是税务专员)
     * @return
     */
    @GetMapping("/getTaxCommissioner")
    public Message getTaxCommissioner(){
        List<UserVo> list = new ArrayList<>();
        List<UserVo> allUserByRoleCode = userService.getAllUserByRoleCode("ROLE_TAX_COMMISSIONER");
        int count =allUserByRoleCode.size()>10?10:allUserByRoleCode.size();
        for (int i = 0; i < count; i++) {
            list.add(allUserByRoleCode.get(i));
        }
        return ResponseUtil.responseBody(list);
    }
}
