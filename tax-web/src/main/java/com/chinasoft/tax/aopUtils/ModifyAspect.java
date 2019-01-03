package com.chinasoft.tax.aopUtils;

import com.chinasoft.tax.aop.EnableGameleyLog;
import com.chinasoft.tax.beanUtils.IpInfoUtil;
import com.chinasoft.tax.common.utils.IDGeneratorUtils;
import com.chinasoft.tax.config.security.CurrentUserUtils;
import com.chinasoft.tax.constant.CommonConstant;
import com.chinasoft.tax.constant.CommonStatus;
import com.chinasoft.tax.service.LogInfoService;
import com.chinasoft.tax.vo.LogInfoVo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:拦截@EnableGameleyLog注解的方法,
 * 将具体修改存储到数据库中
 * @Date: Created in 17:24 2018/11/7
 * @Author: yaochenglong
 */

@Aspect
@Component
@ConditionalOnBean(LogInfoService.class)
public class ModifyAspect {

    private final static Logger logger = LoggerFactory.getLogger(ModifyAspect.class);

    private LogInfoVo operateLog=new LogInfoVo();

    private Object oldObject;

    private Object newObject;

    private Map<String,Object> feildValues;

    /*@Autowired(required = false)
    private HttpServletRequest request;*/

    @Autowired
    private LogInfoService logInfoService;

    @Autowired
    private IpInfoUtil ipInfoUtil;

    @Autowired
    private CurrentUserUtils currentUserUtils;

    @Before("@annotation(enableGameleyLog)")
    public void doBefore(JoinPoint joinPoint, EnableGameleyLog enableGameleyLog){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Object info=joinPoint.getArgs()[0];
        String[] feilds=enableGameleyLog.feildName();
        operateLog.setUsername(currentUserUtils.getUserName());
        operateLog.setIp(ipInfoUtil.getIpAddr(request));
        operateLog.setOperationTime(new Date());
        operateLog.setCreateTime(new Date());
        operateLog.setMethod(joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        if(ModifyName.UPDATE.equals(enableGameleyLog.name())){
            for(String feild:feilds){
                feildValues=new HashMap<>();
                Object result= ReflectionUtils.getFieldValue(info,feild);
                feildValues.put(feild,result);
            }
            try {
                ContentParser contentParser= (ContentParser) enableGameleyLog.parseclass().newInstance();
                oldObject=contentParser.getResult(feildValues,enableGameleyLog);
            } catch (Exception e) {
                logger.error("service加载失败:",e);
            }
        }else{
            if(ModifyName.UPDATE.equals(enableGameleyLog.name())){
                logger.error("id查询失败，无法记录日志");
            }
        }

    }
    @AfterReturning(pointcut = "@annotation(enableGameleyLog)",returning = "object")
    public void doAfterReturing(Object object, EnableGameleyLog enableGameleyLog){
        if(ModifyName.UPDATE.equals(enableGameleyLog.name())){
            ContentParser contentParser= null;
            try {
                contentParser = (ContentParser) enableGameleyLog.parseclass().newInstance();
                newObject=contentParser.getResult(feildValues,enableGameleyLog);
            } catch (Exception e) {
                logger.error("service加载失败:",e);
            }
            try {
                List<Map<String ,Object>> changelist= ReflectionUtils.compareTwoClass(oldObject,newObject);
                StringBuilder str=new StringBuilder();
                for(Map<String,Object> map:changelist){
                    str.append("【"+map.get("name")+"】从【"+map.get("old")+"】改为了【"+map.get("new")+"】;\n");
                }
                operateLog.setDataAfter(str.toString());

            } catch (Exception e) {
                logger.error("比较异常",e);
            }
        }
        operateLog.setId(IDGeneratorUtils.getUUID32());
        operateLog.setStatus(CommonConstant.EXECUTE_SUCCESS);
        logInfoService.add(operateLog);
    }

    @AfterThrowing(pointcut = "@annotation(enableGameleyLog)",throwing = "e")
    public void afterThrowing(JoinPoint point, Throwable e, EnableGameleyLog enableGameleyLog){
        if(ModifyName.UPDATE.equals(enableGameleyLog.name())){
            operateLog.setDataAfter("调用失败，异常代码:"+e.getMessage());
            operateLog.setStatus(CommonConstant.EXECUTE_FAILD);
            logInfoService.add(operateLog);
        }
    }
}