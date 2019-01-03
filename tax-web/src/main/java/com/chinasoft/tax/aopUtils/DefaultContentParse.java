package com.chinasoft.tax.aopUtils;

import com.chinasoft.tax.aop.EnableGameleyLog;
import com.chinasoft.tax.utils.MyMapper;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * 基础解析类
 * 单表编辑时可以直接使用id来查询
 * 如果为多表复杂逻辑，请自行编写具体实现类
 * @author lw
 * @date 2018-03-02
 */
public class DefaultContentParse implements ContentParser {
    @Override
    public Object getResult(Map<String,Object> feildValues, EnableGameleyLog enableGameleyLog) {
        Assert.isTrue(feildValues.containsKey("id"),"未解析到id值，请检查前台传递参数是否正确");
        Object result= feildValues.get("id");
        String id = null;
        if(result instanceof String){
            id= (String) result;
        }
        MyMapper service= null;
        Class cls=enableGameleyLog.serviceclass();
        service = (MyMapper) SpringContainer.getBean(cls);
        Object o = service.selectByPrimaryKey(id);
        System.out.println(o.toString());
        return  service.selectByPrimaryKey(id);
    }


}
