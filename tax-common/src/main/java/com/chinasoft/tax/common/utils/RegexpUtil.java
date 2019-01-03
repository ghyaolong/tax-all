package com.chinasoft.tax.common.utils;

import java.util.regex.Pattern;

/**
 * @Author: wumengxiang@iyungu.com
 * @Description: 正则匹配工具类
 * @Date: Created in 17:33 2017/12/5
 * @Modified By:
 */
public class RegexpUtil {

    //验证手机号码
    public static final String MOBILE_REGEXP = "^1[34578]\\d{9}$";

    //验证数字
    public static final String NUMBER_REGEXP = "^[1-9][0-9]*$";

    //验证日期（yyyy-MM-dd HH:mm:ss）
    public static final String

            DATATIME_REGEXP =  "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$";


    //验证日期（yyyy-MM-dd）
    public static final String DATA_YMD_REGEXP = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";

    //验证是非
    public static final String YES_OR_NO_REGEXP = "^[Y,N]$";

    //验证金额（非负数，最多保留小数点后两位）
    public static final String MONEY_RULE_REGEXP = "^(([1-9]+)|([0-9]+\\.[0-9]{1,2}))$";

    //验证0或1
    public static final String ZERO_OR_ONE_REGEXP = "^[1,0]$";

    //验证1或2
    public static final String ONE_OR_TWO_REGEXP = "^[1,2]$";

    //验证男或女
    public static final String MALE_OR_FEMALE_REGEXP = "^[男,女]$";

    //手术级别
    public static final String SURGERY_LEVEL="^[1-4]$";


    /**
     * @Author: wumengxiang@iyungu.com
     * @Description:
     * @Params:
     *   @param: source 匹配的源字符串
     *   @param: regexp 匹配的正则表达式
     * @Return: boolean
     * @Throws:
     * @Date: Created in 18:01 2017/12/5
     */
    public static boolean isValidate(String source, String regexp) {

        return Pattern.matches(regexp,source);
    }
}

