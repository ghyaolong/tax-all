package com.chinasoft.tax.service.impl;

import com.chinasoft.tax.common.utils.DateUtil;
import com.chinasoft.tax.common.utils.DigestUtil;
import com.chinasoft.tax.common.utils.IDGeneratorUtils;
import com.chinasoft.tax.common.utils.MyBeanUtils;
import com.chinasoft.tax.constant.CommonConstant;
import com.chinasoft.tax.constant.CommonStatus;
import com.chinasoft.tax.dao.*;
import com.chinasoft.tax.enums.ExceptionCode;
import com.chinasoft.tax.po.*;
import com.chinasoft.tax.service.UserService;
import com.chinasoft.tax.vo.*;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description:用户管理实现类
 * @Author: yaochenglong
 * @Date: 17:49 2018/10/21
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private TUserMapper tUserMapper;

    @Autowired
    private TUserRoleMapper tUserRoleMapper;


    @Autowired
    private TUserDepartmentMapper tUserDepartmentMapper;


    @Autowired
    private TUserCompanyMapper tUserCompanyMapper;

    @Autowired
    private TCompanyMapper tCompanyMapper;

    @Autowired
    private TPermissionMapper tPermissionMapper;

    @Override
    public boolean login(UserVo vo) {
        if (vo == null) {
            throw new BizException(ExceptionCode.USER_INFO_IS_NOT_EXIST);
        }

        if (StringUtils.isEmpty(vo.getUsername())) {
            throw new BizException(ExceptionCode.REQUEST_PARAM_MISSING);
        }

        TUser tUser = null;
        try {
            Example example = new Example(TUser.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("username", vo.getUsername());
            tUser = tUserMapper.selectOneByExample(example);
            if (tUser == null) {
                return false;
            }
        } catch (Exception e) {
            throw new BizException(ExceptionCode.DATA_AREADY_EXIST);
        }
        String salt = tUser.getSalt();
        String oriPassword = tUser.getPassword();
        String password = DigestUtil.encryptMD5(vo.getPassword(), salt);
        if (oriPassword.equals(password)) {
            return true;
        } else {
            throw new BizException(ExceptionCode.PASSORD_ERROR);
        }
    }

    @Override
    public List<UserVo> getAllUser() {
        List<TUser> tUsers = tUserMapper.selectAll();
        List<UserVo> voList = MyBeanUtils.copyList(tUsers, UserVo.class);
        return voList;
    }

    @Override
    public UserVo getUser(UserVo vo) {
        TUser tuser = new TUser();
        BeanUtils.copyProperties(vo, tuser);
        try {
            TUser tUser = tUserMapper.selectOne(tuser);
            BeanUtils.copyProperties(tUser, vo);
        } catch (Exception e) {
            throw new BizException("用户数据不唯一");
        }
        return vo;
    }

    @Override
    public UserVo getUserByUserName(String username) {
        Example example = new Example(TUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
        try {
            TUser tUser = tUserMapper.selectOneByExample(example);
            if (tUser != null) {
                UserVo userVo = MyBeanUtils.copy(tUser, UserVo.class);
                //关联部门
                List<TDepartment> tDepartments = tUserDepartmentMapper.findByUserId(tUser.getId());
                List<DepartmentVo> departmentVos = MyBeanUtils.copyList(tDepartments, DepartmentVo.class);
                userVo.setDepartments(departmentVos);
                //关联角色
                List<TRole> tRoles = tUserRoleMapper.findByUserId(tUser.getId());
                List<RoleVo> roleVos = MyBeanUtils.copyList(tRoles, RoleVo.class);
                userVo.setRoles(roleVos);
                //关联权限菜单
                List<TPermission> tPermissions = tPermissionMapper.findByUserId(tUser.getId());
                List<PermissionVo> permissionVos = MyBeanUtils.copyList(tPermissions, PermissionVo.class);
                userVo.setPermissions(permissionVos);
                return userVo;
            }
            return null;

        } catch (Exception e) {
            log.error("查询用户" + username + "出错", e);
            throw new BizException(ExceptionCode.DATA_AREADY_EXIST);
        }
    }

    @Override
    public UserVo getUserById(String id) {
        TUser tUser = tUserMapper.selectByPrimaryKey(id);
        if (tUser == null) {
            throw new BizException(ExceptionCode.USER_INFO_IS_NOT_EXIST);
        }
        UserVo vo = new UserVo();
        BeanUtils.copyProperties(tUser, vo, "password2");
        return vo;
    }

    @Override
    public MyPageInfo<UserVo> getUserByPage(int pageNow, int pageSize) {
        if (pageNow < 0 || pageSize <= 0) {
            throw new BizException("分页参数不正确");
        }
        PageHelper.startPage(pageNow, pageSize, true);
        Example example = new Example(TUser.class);
        example.createCriteria().andEqualTo("is_del", CommonStatus.NORMAL_FLAG);
        List<TUser> tUsers = tUserMapper.selectByExample(example);
        int count = tUserMapper.selectCountByExample(example);
        List<UserVo> userVoList = MyBeanUtils.copyList(tUsers, UserVo.class);
        MyPageInfo<UserVo> page = new MyPageInfo<>(userVoList);
        page.setTotalElements(count);
        page.setPageNum(pageNow);
        return page;
    }

    @Override
    public MyPageInfo<UserVo> getUserByPage(int pageNow, int pageSize, UserVo vo) {
        TUser tuser = new TUser();
        BeanUtils.copyProperties(vo, tuser);
        PageHelper.startPage(pageNow, pageSize, true);
        tuser.setStatus(CommonStatus.NORMAL_FLAG);
        List<TUser> tUsers = tUserMapper.select(tuser);
        int count = tUserMapper.selectCount(tuser);
        List<UserVo> userVoList = MyBeanUtils.copyList(tUsers, UserVo.class);
        MyPageInfo<UserVo> page = new MyPageInfo<>(userVoList);
        page.setTotalElements(count);
        page.setPageNum(pageNow);
        return page;
    }

    @Transactional
    @Override
    public void addUser(UserVo vo) {
        log.info("添加用户开始,入参为[" + vo.toString() + "]");
        TUser tuser = new TUser();
        boolean bool = isExistByUserName(vo.getUsername());
        if (bool) {
            throw new BizException(ExceptionCode.USER_NAME_AREADY_EXIST);
        }
        bool = isExistByWorkNumber(vo.getWorkNumber());
        if (bool) {
            throw new BizException(ExceptionCode.WORK_NUMBER_AREADY_EXIST);
        }
        log.info("验证用户唯一性通过，");
        //这里将密码加密成暗文
        BeanUtils.copyProperties(vo, tuser);
        tuser.setId(IDGeneratorUtils.getUUID32());
//        String salt = RandomUtils.randomSalt();
//        String password = DigestUtil.encryptMD5(tuser.getPassword(), salt);
        String encryptPass = new BCryptPasswordEncoder().encode(vo.getPassword());
        tuser.setCreateTime(new Date());
        tuser.setDepartid(vo.getDepartmentIds());
        tuser.setPassword(encryptPass);
        tuser.setStatus(0);
        log.info("添加用户中....,参数为[" + tuser.toString() + "]");
        tUserMapper.insertSelective(tuser);


        //保存用户-角色关系
        String[] roleIds = vo.getRoleIds().split(",");
        for (String roleId : roleIds) {
            TUserRole tUserRole = new TUserRole();
            tUserRole.setId(IDGeneratorUtils.getUUID32());
            tUserRole.setUserId(tuser.getId());
            tUserRole.setRoleId(roleId);
            tUserRoleMapper.insert(tUserRole);
            log.debug("保存用户-角色成功，结果:" + tUserRole);
        }
        //保存用户-部门关系
        String[] departmentIds = vo.getDepartmentIds().split(",");
        for (String departmentId : departmentIds) {
            TUserDepartment tUserDepartment = new TUserDepartment();
            tUserDepartment.setId(IDGeneratorUtils.getUUID32());
            tUserDepartment.setUserId(tuser.getId());
            tUserDepartment.setDepartmentId(departmentId);
            tUserDepartmentMapper.insert(tUserDepartment);
            log.debug("保存用户-部门成功，结果:" + tUserDepartment);
        }
        log.info("添加用户成功");
    }

    /**
     * 判断用户是否存在，
     *
     * @return
     * @Param username 用户名
     */
    private boolean isExistByUserName(String username) {
        if (StringUtils.isEmpty(username)) {
            throw new BizException(ExceptionCode.REQUEST_PARAM_MISSING);
        }
        Example example = new Example(TUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.orEqualTo("username", username);
        int i = tUserMapper.selectCountByExample(example);
        return i > 0;
    }

    /**
     * 判断用户是否存在，
     *
     * @return
     * @Param workNumber
     */
    private boolean isExistByWorkNumber(String workNumber) {
        if (StringUtils.isEmpty(workNumber)) {
            throw new BizException(ExceptionCode.REQUEST_PARAM_MISSING);
        }
        Example example = new Example(TUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.orEqualTo("workNumber", workNumber);
        int i = tUserMapper.selectCountByExample(example);
        return i > 0;
    }


    @Override
    public void delUser(String id) {
        log.info("删除用户，输入参数[id=" + id + "]");
//        TUser tUser = tUserMapper.selectByPrimaryKey(id);
//        tUser.setStatus(CommonStatus.DEL_FLAG);
//        //tUserMapper.deleteByPrimaryKey(id);
//        tUserMapper.updateByPrimaryKeySelective(tUser);
        tUserMapper.deleteByPrimaryKey(id);
        Example example = new Example(TUserRole.class);
        example.createCriteria().andEqualTo("userId", id);
        tUserRoleMapper.deleteByExample(example);

        example = new Example(TUserDepartment.class);
        example.createCriteria().andEqualTo("userId", id);
        tUserDepartmentMapper.deleteByExample(example);

        example = new Example(TUserCompany.class);
        example.createCriteria().andEqualTo("userId", id);
        tUserCompanyMapper.deleteByExample(example);
        //删除该用户之后，将和该用户关联的公司状态修改未未分配
        List<TCompany> tCompanies = tUserCompanyMapper.findByUserId(id);
        for (TCompany tCompany : tCompanies) {
            tCompany.setIsAssign(CommonConstant.COMPANY_UNASSGINED);
            tCompanyMapper.updateByPrimaryKeySelective(tCompany);
        }
        log.info("删除用户成功");
    }

    @Override
    public void delUserByWorkNum(String workNumber) {
        log.info("通过工号删除用户，输入参数[workNumber=" + workNumber + "]");
        Example example = new Example(TUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("workNumber", workNumber);
        TUser tUser = tUserMapper.selectOneByExample(example);
        tUser.setStatus(CommonStatus.DEL_FLAG);
        tUserMapper.updateByPrimaryKeySelective(tUser);
        log.info("删除用户成功，返回对象[" + tUser.toString() + "]");
    }

    @Override
    public void editUser(UserVo vo) {
        //TUser tuser = new TUser();

        Example queryExample = new Example(TUser.class);
        if(!StringUtils.isEmpty(vo.getUsername())){
            TUser tUser = tUserMapper.selectByPrimaryKey(vo.getId());
            if(!tUser.getUsername().equals(vo.getUsername())){
                if(!tUser.getUsername().equals(vo.getUsername())){
                    queryExample.createCriteria().andEqualTo("username",vo.getUsername());
                    int i = tUserMapper.selectCountByExample(queryExample);
                    if(i>0){
                        throw new BizException(ExceptionCode.DATA_AREADY_EXIST.getCode(),"用户名已存在");
                    }
                }
            }else if(!tUser.getWorkNumber().equals(vo.getWorkNumber())){
                queryExample.createCriteria().andEqualTo("workNumber",vo.getUsername());
                int i = tUserMapper.selectCountByExample(queryExample);
                if(i>0){
                    throw new BizException(ExceptionCode.DATA_AREADY_EXIST.getCode(),"工号已存在");
                }
            }
        }
        TUser tuser = tUserMapper.selectByPrimaryKey(vo.getId());
        BeanUtils.copyProperties(vo, tuser, "password");
        String roleIds = vo.getRoleIds();
        String departmentIds = vo.getDepartmentIds();
        String companyIds = vo.getCompanyIds();

        if (!StringUtils.isEmpty(roleIds)) {
            //清除用户先前的角色，重新添加角色
            Example userRoleExample = new Example(TUserRole.class);
            userRoleExample.createCriteria().andEqualTo("userId", vo.getId());
            tUserRoleMapper.deleteByExample(userRoleExample);
            log.debug("修改用户中，清除用户原有的角色成功");
            String[] split = roleIds.split(",");
            for (String s : split) {
                TUserRole tUserRole = new TUserRole();
                tUserRole.setId(IDGeneratorUtils.getUUID32());
                tUserRole.setUserId(vo.getId());
                tUserRole.setRoleId(s);
                tUserRoleMapper.insertSelective(tUserRole);
            }
            log.debug("修改用户.....重新给用户添加角色成功");
        }

        if (!StringUtils.isEmpty(departmentIds)) {
            Example userDepartmentExample = new Example(TUserDepartment.class);
            userDepartmentExample.createCriteria().andEqualTo("userId", vo.getId());
            tUserDepartmentMapper.deleteByExample(userDepartmentExample);
            log.info("修改用户......删除用户原有的部门信息成功");
            String[] split = departmentIds.split(",");
            for (String s : split) {
                TUserDepartment tUserDepartment = new TUserDepartment();
                tUserDepartment.setId(IDGeneratorUtils.getUUID32());
                tUserDepartment.setUserId(vo.getId());
                tUserDepartment.setDepartmentId(s);
                tUserDepartment.setCreateTime(new Date());
                tUserDepartmentMapper.insertSelective(tUserDepartment);
            }
            log.debug("修改用户......重新给用户添加部门成功");
        }

        //重新分配公司
        //先查询该用户分配了的公司
        Example tuserCompanyExample = new Example(TUserCompany.class);
        tuserCompanyExample.createCriteria().andEqualTo("userId", vo.getId());
        List<TUserCompany> selectedCompanies = tUserCompanyMapper.selectByExample(tuserCompanyExample);
        for (TUserCompany selectedCompany : selectedCompanies) {
            TCompany tCompany = new TCompany();
            tCompany.setId(selectedCompany.getCompanyId());
            tCompany.setIsAssign(CommonConstant.COMPANY_UNASSGINED);
            tCompanyMapper.updateByPrimaryKeySelective(tCompany);
        }

        tUserCompanyMapper.deleteByExample(tuserCompanyExample);
        log.info("修改用户......删除用户原来的公司信息");
        String[] ids = companyIds.split(",");
        for (String id : ids) {
            TUserCompany tUserCompany = new TUserCompany();
            tUserCompany.setId(IDGeneratorUtils.getUUID32());
            tUserCompany.setUserId(vo.getId());
            tUserCompany.setCompanyId(id);
            tUserCompany.setCreateTime(new Date());
            tUserCompanyMapper.insertSelective(tUserCompany);

            //设置该公司已被分配
            TCompany tCompany = new TCompany();
            tCompany.setId(id);
            tCompany.setIsAssign(CommonConstant.COMPANY_ASSIGNED);
            tCompanyMapper.updateByPrimaryKeySelective(tCompany);
        }
        tuser.setDepartid(vo.getDepartmentIds());
        tUserMapper.updateByPrimaryKeySelective(tuser);
    }

    @Override
    public void updatePassord(UserVo userVo) {
        log.info("开始修改用户密码，输入参数:" + userVo);
        TUser tuser = new TUser();
        BeanUtils.copyProperties(userVo, tuser);
        tUserMapper.updateByPrimaryKeySelective(tuser);
        log.info("修改密码成功");
    }

    /**
     * 分页查询
     *
     * @param pageVo
     * @param searchVo
     * @param userVo
     * @return
     */
    @Override
    public MyPageInfo<UserVo> findByCondition(PageVo pageVo, SearchVo searchVo, UserVo userVo){
        String username = userVo.getUsername();
        String email = userVo.getEmail();
        Integer sex = userVo.getSex();
        Integer status = userVo.getStatus();
        //Date createTime = userVo.getCreateTime();
        Example example = new Example(TUser.class);
        Example.Criteria criteria = example.createCriteria();

        //
        //userVo.setRoleIds("496138616573952");
        //

        if (!StringUtils.isEmpty(username)) {
            criteria.andLike("username", "%" + username + "%");
        }
        if (!StringUtils.isEmpty(email)) {
            criteria.andLike("email", "%" + email + "%");
        }
        if (sex != null) {
            criteria.andEqualTo("sex", sex);
        }
        if (status != null) {
            criteria.andEqualTo("status", status);
        }
        if(!StringUtils.isEmpty(userVo.getTel())){
            criteria.andLike("tel",""+userVo.getTel()+"%");
        }


        if (searchVo != null) {
            if (!StringUtils.isEmpty(searchVo.getStartDate()) && !StringUtils.isEmpty(searchVo.getEndDate())) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    criteria.andBetween("createTime", sdf.parse(searchVo.getStartDate()), sdf.parse(searchVo.getEndDate()));
                    //criteria.andGreaterThanOrEqualTo("createTime",sdf.parse(searchVo.getStartDate()));
                    //criteria.andLessThanOrEqualTo("createTime",sdf.parse(searchVo.getEndDate()));

                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }
        if(!StringUtils.isEmpty(userVo.getDepartmentIds())){
            criteria.andEqualTo("departid", userVo.getDepartmentIds());
        }

        PageHelper.startPage(pageVo.getPageNumber(), pageVo.getPageSize(), true);
        List<TUser> tUsers = tUserMapper.selectByExample(example);
        int count = tUserMapper.selectCountByExample(example);
        List<UserVo> userVoList = MyBeanUtils.copyList(tUsers, UserVo.class, "password", "password2");
        Iterator<UserVo> iterator = userVoList.iterator();
        while (iterator.hasNext()){
            UserVo vo = iterator.next();
            List<TRole> tRoleList = tUserRoleMapper.findByUserId(vo.getId());
            if (!CollectionUtils.isEmpty(tRoleList)) {
                List<RoleVo> roleVos = MyBeanUtils.copyList(tRoleList, RoleVo.class);
                vo.setRoles(roleVos);
            }

            if(!StringUtils.isEmpty(userVo.getRoleIds())){
                for (TRole tRole : tRoleList) {
                    if(!tRole.getId().equals(userVo.getRoleIds())){
                        try {
                            iterator.remove();
                        }catch (Exception e){

                        }
                    }
                }
            }

            List<TDepartment> tDepartmentList = tUserDepartmentMapper.findByUserId(vo.getId());
            if (!CollectionUtils.isEmpty(tDepartmentList)) {
                List<DepartmentVo> departmentVos = MyBeanUtils.copyList(tDepartmentList, DepartmentVo.class);
                vo.setDepartments(departmentVos);
            }
            List<TCompany> tCompanyList = tUserCompanyMapper.findByUserId(vo.getId());
            if (!CollectionUtils.isEmpty(tCompanyList)) {
                List<CompanyVo> companyVoList = MyBeanUtils.copyList(tCompanyList, CompanyVo.class);
                vo.setCompanys(companyVoList);
            }
        }
//        for (UserVo vo : userVoList) {
//
//
//        }
        MyPageInfo info = new MyPageInfo(userVoList);
        if(!CollectionUtils.isEmpty(userVoList)){
            info.setTotalElements(count);
            info.setPageNum(pageVo.getPageNumber());
        }
        return info;

    }

    @Override
    public List<TaskTipPo> getTaskTip(Integer status) {
        List<TaskTipPo> taskTip = tCompanyMapper.getTaskTip(status);
        return taskTip;
    }

    @Override
    public List<UserVo> getPrevReview(String userId) {
        Map<String, String> map = new HashMap();
        map.put("name", "审核人");
        List<TUser> tUsers = tUserMapper.getPrevReview(map);
        //todo 或者匹配code
        List<UserVo> userVos = MyBeanUtils.copyList(tUsers, UserVo.class);
        return userVos;
    }

    @Override
    public UserVo getUserInfoByUserIdAndKey(String userId, String key) {
        TUser tUser = tUserMapper.selectByPrimaryKey(userId);
        UserVo userVo = new UserVo();
        if (tUser != null) {
            userVo = MyBeanUtils.copy(tUser, UserVo.class);
        }
        TRole tRoles = tUserRoleMapper.findByUserIdAndKey(userId, key);
        RoleVo copy = new RoleVo();
        if (tRoles != null) {
            copy = MyBeanUtils.copy(tRoles, RoleVo.class);
        }
        List<RoleVo> list = new ArrayList<>();
        list.add(copy);
        userVo.setRoles(list);
        return userVo;
    }

    @Override
    public List<UserVo> getUserInfoByKey(String key) {
        List<TUser> tUsers = tUserMapper.findUserByKey(key);
        List<UserVo> userVos = MyBeanUtils.copyList(tUsers, UserVo.class);
        return userVos;
    }
}
