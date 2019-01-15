package com.chinasoft.tax.service.impl;

import com.chinasoft.tax.common.utils.IDGeneratorUtils;
import com.chinasoft.tax.common.utils.MyBeanUtils;
import com.chinasoft.tax.dao.TLogInfoMapper;
import com.chinasoft.tax.po.TLogInfo;
import com.chinasoft.tax.service.LogInfoService;
import com.chinasoft.tax.vo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class LogInfoServiceImpl implements LogInfoService {

    @Autowired
    private TLogInfoMapper tLogInfoMapper;

    @Override
    public MyPageInfo<LogInfoVo> getAllPage(PageVo pageVo, SearchVo searchVo, LogInfoVo logInfoVo) {
        PageHelper.startPage(pageVo.getPageNumber(),pageVo.getPageSize());
        Example example = new Example(TLogInfo.class);
        example.setOrderByClause("create_time desc");
        Example.Criteria criteria = example.createCriteria();
        if(logInfoVo!=null){
            if(!StringUtils.isEmpty(logInfoVo.getUsername())){
                criteria.andLike("username","%"+logInfoVo.getUsername()+"%");
            }
            if(!StringUtils.isEmpty(logInfoVo.getMethod())){
                criteria.andLike("method","%"+logInfoVo.getMethod().trim()+"%");
            }
            if(!StringUtils.isEmpty(logInfoVo.getMethodCode())){
                criteria.andLike("methodCode","%"+logInfoVo.getMethodCode()+"%");
            }
            if(!StringUtils.isEmpty(logInfoVo.getIp())){
                criteria.andLike("ip","%"+logInfoVo.getIp()+"%");
            }
            if(!StringUtils.isEmpty(logInfoVo.getCreator())){
                criteria.andLike("creator","%"+logInfoVo.getCreator()+"%");
            }
            if(logInfoVo.getStatus()!=null){
                criteria.andEqualTo("state","%"+logInfoVo.getStatus()+"%");
            }
        }
        if(searchVo!=null){
            if(!StringUtils.isEmpty(searchVo.getStartDate())&&!StringUtils.isEmpty(searchVo.getEndDate())){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                try {
                    criteria.andBetween("createTime",sdf.parse(searchVo.getStartDate()+"  00:00:00"),sdf.parse(searchVo.getEndDate()+" 23:59:59"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        List<TLogInfo> tLogInfoList = tLogInfoMapper.selectByExample(example);
        int count = tLogInfoMapper.selectCountByExample(example);
        List<LogInfoVo> logInfoVos = MyBeanUtils.copyList(tLogInfoList, LogInfoVo.class);

        MyPageInfo<LogInfoVo> page = new MyPageInfo<>(logInfoVos);
        page.setTotalElements(count);
        page.setPageNum(pageVo.getPageNumber());
        return page;
    }

    @Override
    public void add(LogInfoVo logInfoVo) {
        log.info("添加日志，输入参数："+logInfoVo.toString());
        TLogInfo tLogInfo = MyBeanUtils.copy(logInfoVo, TLogInfo.class);
        tLogInfo.setId(IDGeneratorUtils.getUUID32());
        tLogInfo.setCreateTime(new Date());
        tLogInfo.setUserName(logInfoVo.getUsername());
        tLogInfoMapper.insertSelective(tLogInfo);
        log.info("添加日志成功");
    }

    @Override
    public void delAll() {
        tLogInfoMapper.deleteByExample(null);
    }

    @Override
    public void delById(String s) {
        tLogInfoMapper.deleteByPrimaryKey(s);
    }
}
