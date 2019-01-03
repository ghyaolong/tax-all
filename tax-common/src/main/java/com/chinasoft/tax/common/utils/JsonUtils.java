package com.chinasoft.tax.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.*;

public final class JsonUtils
{

    private JsonUtils()
    {

    }

    /**
     *
     * @Title: getJosnFromObject
     * @Description: 根据实体类对象生成对应的JSON字符串
     *
     * @param obj
     * @return
     * @return: String
     */
    public static String getJosnFromObject(Object obj)
    {
        String jsonResult = null;
        if (null != obj)
        {
            Gson gson = new Gson();
            jsonResult = gson.toJson(obj);
        }
        return jsonResult;
    }

    /**
     *
     * @Title: getObjectTFromJson
     * @Description: 根据json字符串和实体对象类Class生成对应的实体对象
     *
     * @param json
     * @param classT
     * @return
     * @return: T
     */
    @SuppressWarnings("unchecked")
    public static <T> T getObjectTFromJson(String json, Class<T> classT)
    {
        Object obj = null;
        if (null != json && !"".equals(json.trim()))
        {
            Gson gson = new Gson();
            obj = gson.fromJson(json, classT);
        }
        if (null != obj)
        {
            return (T) obj;
        }
        return null;
    }

    /**
     *
     * @Title: getObjectTFromJson
     * @Description: 根据json字符串和实体对象类型生成对应的实体对象
     * @param json
     * @param typeOfT
     * @return
     * @return: T
     */
    @SuppressWarnings("unchecked")
    public static <T> T getObjectTFromJson(String json, Type typeOfT)
    {
        Object target = null;
        if (null != json && !"".equals(json.trim()))
        {
            Gson gson = new Gson();
            target = gson.fromJson(json, typeOfT);
            return (T) target;
        }
        return null;
    }

    public static Map<String, Object> parseJSON2Map(String jsonStr)
    {
        //转义非法字符
        //jsonStr = filterIllealChar(jsonStr);

        Map<String, Object> map = new HashMap<String, Object>();
        //最外层解析
        JSONObject json = JSONObject.parseObject(jsonStr);
        for (Object k : json.keySet())
        {
            Object v = json.get(k);
            //如果内层还是数组的话，继续解析
            if (v instanceof JSONArray)
            {

                List<Map<String, Object>> list =
                        new ArrayList<Map<String, Object>>();
                List<Object> listBean = new ArrayList<Object>();

                Iterator<Object> it = ((JSONArray)v).iterator();
                boolean _b = false;
                while (it.hasNext())
                {
                    Object next = it.next();
                    if (next instanceof JSONObject)
                    {
                        JSONObject json2 = (JSONObject)next;
                        list.add(parseJSON2Map(json2.toString()));
                    }else{
                        _b = true;
                        listBean.add(next);
                    }

                }
                if(!_b){
                    map.put(k.toString(), list);
                }else{
                    map.put(k.toString(), listBean);
                }
            }
            else
            {
                map.put(k.toString(), v);
            }
        }
        return map;
    }

    /**
     * Description:对象转为json字符串
     * @param obj
     * @return
     * Date:        2016年4月12日 下午4:27:30
     * Author:      gaobing
     */
    public static String getJsonStrFromObj(Object obj)
    {
        if (null != obj)
        {
            String json_data = JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue, SerializerFeature.DisableCircularReferenceDetect);
            json_data = json_data.replace(":null", ":\"\"");
            return json_data;
        }
        return null;
    }
    /**
     * @param obj
     * @param filterNullFlag 是否过滤空值
     * @return
     */
    public static String getJsonStrFromObj(Object obj,boolean filterNullFlag)
    {
        if (null != obj)
        {
            if(filterNullFlag){
                return JSONObject.toJSONString(obj);
            }else{
                return JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue, SerializerFeature.DisableCircularReferenceDetect);
            }

        }
        return null;
    }


    /**
     * Description:过滤实体中的字段
     * @param src 需要过滤的对象，如 list,entity
     * @param clazz 实体的class
     * @param args 需要的字段,使用逗号分隔，如: time,desc
     * @return
     * Date:        2016年4月27日 下午5:42:21
     * Author:      gaobing
     */
    public static String filterFieldsJson(Object src, Class<?> clazz, String... args)
    {
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(clazz, args);
        return JSON.toJSONString(src, filter, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 过滤非法字符
     * @param s
     * @return
     */
//    private static String filterIllealChar(String s){
//        s = s.replace("\\", "\\\\");//斜杠
//        //s = s.replace("/", "\\/");//反斜杠,暂时注释，解决参数中有http://转换错误的问题
//        s = s.replace("\b", "\\b");//退格
//        s = s.replace("\f", "\\f");//走纸换页
//        s = s.replace("\n", "\\n");//换行符
//        s = s.replace("\r", "\\r");//回车
//        s = s.replace("\t", "\\t");//制表符
//        return s;
//    }

}

