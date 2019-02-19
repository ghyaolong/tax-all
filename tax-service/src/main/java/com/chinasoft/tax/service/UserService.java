package com.chinasoft.tax.service;

import com.chinasoft.tax.po.TaskTipPo;
import com.chinasoft.tax.vo.MyPageInfo;
import com.chinasoft.tax.vo.PageVo;
import com.chinasoft.tax.vo.SearchVo;
import com.chinasoft.tax.vo.UserVo;
import com.github.pagehelper.PageInfo;

import java.text.ParseException;
import java.util.List;

/**
 * @Description:用户管理
 * @Author: yaochenglong
 * @Date: 16:55 2018/10/21
 */
public interface UserService {

    /**
     * 用户登录,返回token
     * @param vo
     * @return
     */
    boolean login(UserVo vo);

    /**
     * 获取所有用户数据
     * @return
     */
    List<UserVo> getAllUser();


    /**
     * 通过roleCode获取拥有改roleCode的所有用户
     * @param roleCode
     * @return
     */
    List<UserVo> getAllUserByRoleCode(String roleCode);
    /**
     * @Description:查询某一个用户，如果有多个，则报错
     * @param vo
     * @return 返回唯一一个用户
     */
    UserVo getUser(UserVo vo);

    /**
     * 通过userName查找用户
     * @param username
     * @return
     */
    UserVo getUserByUserName(String username);

    /**
     * 通过主键id查询用户
     * @param id
     * @return
     */
    UserVo getUserById(String id);
    /**
     * @Description: 已分页方式获取用户
     * @param pageNow : 当前页
     * @param pageSize: 每页显示都条数
     * @Return: 返回带用户数据带分页对象
     * @Author: yaochenglong
     * @Date: Created in 16:57 2018/10/21
     */

    MyPageInfo<UserVo> getUserByPage(int pageNow, int pageSize);

    /**
     * @Description:
     * @param pageNow
     * @param pageSize
     * @param vo 额外都查询条件
     * @return
     */
    MyPageInfo<UserVo> getUserByPage(int pageNow, int pageSize, UserVo vo);

    /**
     * @Description:添加用户
     * @param vo
     */
    void addUser(UserVo vo);

    /**
     * 删除用户
     * @param id 用户唯一id
     */
    void delUser(String id);

    /**
     * 通过工号删除用户
     * @param workNum 工号
     */
    void delUserByWorkNum(String workNum);

    /**
     * 修改用户
     * @param vo
     */
    void editUser(UserVo vo);

    /**
     * 修改密码
     * @param userVo
     */
    void updatePassord(UserVo userVo);


    MyPageInfo<UserVo> findByCondition(PageVo pageVo, SearchVo searchVo, UserVo userVo) throws ParseException;

    /**
     * 获取用户需要处理的公司的统计
     * @param status
     * @return
     */
    List<TaskTipPo> getTaskTip(Integer status);

    /**
     * 获取当前登录用户的上级审核人
     * @param userId
     * @return
     */
    List<UserVo> getPrevReview(String userId);

    /**
     * 通过用户id和流程key返回用户和该用户的角色信息
     * @param userId
     * @param key
     * @return
     */
    UserVo getUserInfoByUserIdAndKey(String userId,String key);

    List<UserVo> getUserInfoByKey(String key);
}
