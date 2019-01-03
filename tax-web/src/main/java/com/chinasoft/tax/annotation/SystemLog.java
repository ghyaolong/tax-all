package com.chinasoft.tax.annotation;

import java.lang.annotation.*;

/**
 * @Description: 系统日志自定义注解
 * @Date: Created in 9:07 2018/11/7
 * @Author: yaochenglong
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})//作用于参数或方法上
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
    String description() default "";
}