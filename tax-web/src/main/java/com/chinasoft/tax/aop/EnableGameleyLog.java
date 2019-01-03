package com.chinasoft.tax.aop;

import com.chinasoft.tax.aopUtils.DefaultContentParse;

import java.lang.annotation.*;

/**
 * @Description:
 * @Date: Created in 17:15 2018/11/7
 * @Author: yaochenglong
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface EnableGameleyLog {
    /**
     * 操作的中文说明 可以直接调用ModifyName
     * @return
     */
    String name() default "";

    /**
     * 获取编辑信息的解析类，目前为使用id获取，复杂的解析需要自己实现，默认不填写
     * 则使用默认解析类
     * @return
     */
    Class parseclass() default DefaultContentParse.class;

    /**
     * 查询数据库所调用的class文件
     * @return
     */
    Class serviceclass();

    /**
     * 前台字段名称
     */
    String[] feildName() default {"id"};

}