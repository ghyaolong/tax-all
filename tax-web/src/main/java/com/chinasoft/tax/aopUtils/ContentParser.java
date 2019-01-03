package com.chinasoft.tax.aopUtils;

import com.chinasoft.tax.aop.EnableGameleyLog;

import java.util.Map;

public interface ContentParser {
    /**
     * 获取信息返回查询出的对象
     * @param feildValues 查询条件的参数值
     * @param enableGameleyLog 注解
     * @return
     */
    Object getResult(Map<String, Object> feildValues, EnableGameleyLog enableGameleyLog);
}
