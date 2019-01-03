package com.chinasoft.tax.common.utils;

import cn.hutool.core.util.RandomUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * ID生成器
 */
public class IDGeneratorUtils {

    /**
     * 生产32位的UUID
     * @return
     */
    public static String getUUID32(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-","");
    }

    /**
     * 获取流水号
     * 流水号规则：时间戳+6位随机数
     * @return
     */
    public static String getFlowNum(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmsss");
        String format = sdf.format(new Date());
        int ranInt = (int)((Math.random()*9+1)*100000);
        return format+ranInt;
    }


}
