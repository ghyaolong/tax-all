package com.chinasoft.tax.common.utils;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * @author yaochenglong
 * 日期工具类
 */
@Slf4j
public class DateUtil
{
    /**
     * 日期格式
     */
    public final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * 时间格式
     */
    private final static SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
    /**
     * 日期时间格式
     */
    private final static SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 天干（农历）
     */
    private final static String[] TIAN_GAN = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
    /**
     * 地支（农历）
     */
    private final static String[] DI_ZHI = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌 ", "亥"};
    /**
     * 中国星期
     */
    private final static String[] CHINESE_WEEK = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    /**
     * 中国数字
     */
    public final static String[] CHINESE_NUMBER = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖", "拾"};
    /**
     * 农历年信息
     */
    private final static long[] CHINESE_YEAR = new long[] { 0x04bd8, 0x04ae0,
            0x0a570, 0x054d5, 0x0d260, 0x0d950, 0x16554, 0x056a0, 0x09ad0,
            0x055d2, 0x04ae0, 0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540,
            0x0d6a0, 0x0ada2, 0x095b0, 0x14977, 0x04970, 0x0a4b0, 0x0b4b5,
            0x06a50, 0x06d40, 0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970,
            0x06566, 0x0d4a0, 0x0ea50, 0x06e95, 0x05ad0, 0x02b60, 0x186e3,
            0x092e0, 0x1c8d7, 0x0c950, 0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0,
            0x1a5b4, 0x025d0, 0x092d0, 0x0d2b2, 0x0a950, 0x0b557, 0x06ca0,
            0x0b550, 0x15355, 0x04da0, 0x0a5d0, 0x14573, 0x052d0, 0x0a9a8,
            0x0e950, 0x06aa0, 0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570,
            0x05260, 0x0f263, 0x0d950, 0x05b57, 0x056a0, 0x096d0, 0x04dd5,
            0x04ad0, 0x0a4d0, 0x0d4d4, 0x0d250, 0x0d558, 0x0b540, 0x0b5a0,
            0x195a6, 0x095b0, 0x049b0, 0x0a974, 0x0a4b0, 0x0b27a, 0x06a50,
            0x06d40, 0x0af46, 0x0ab60, 0x09570, 0x04af5, 0x04970, 0x064b0,
            0x074a3, 0x0ea50, 0x06b58, 0x055c0, 0x0ab60, 0x096d5, 0x092e0,
            0x0c960, 0x0d954, 0x0d4a0, 0x0da50, 0x07552, 0x056a0, 0x0abb7,
            0x025d0, 0x092d0, 0x0cab5, 0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50,
            0x055d9, 0x04ba0, 0x0a5b0, 0x15176, 0x052b0, 0x0a930, 0x07954,
            0x06aa0, 0x0ad50, 0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260,
            0x0ea65, 0x0d530, 0x05aa0, 0x076a3, 0x096d0, 0x04bd7, 0x04ad0,
            0x0a4d0, 0x1d0b6, 0x0d250, 0x0d520, 0x0dd45, 0x0b5a0, 0x056d0,
            0x055b2, 0x049b0, 0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20,
            0x0ada0 };

    private DateUtil(){}

    /**
     * 获取当前日期
     *
     * @return yyyy-MM-dd
     */
    public static String getCurrentDate()
    {
        return getDate(Calendar.getInstance().getTime());
    }

    /**
     * 获取当前时间
     *
     * @return HH:mm:ss
     */
    public static String getCurrentTime()
    {
        return getTime(Calendar.getInstance().getTime());
    }

    /**
     * 获取当前月1日日期
     *
     * @return yyyy-MM-dd
     */
    public static String getThisMonthDay()
    {
        String now = getCurrentDate();
        String[] nowArray = now.split("-");

        return nowArray[0] + "-" + nowArray[1] + "-01";
    }

    /**
     * 获取String格式的指定日期的时间信息
     *
     * @param date		日期
     * @return HH:mm:ss
     */
    public static String getTime(Date date)
    {
        if (null != date)
            return TIME_FORMAT.format(date);
        else
            return "";
    }

    /**
     * 获取Date格式的日期信息
     *
     * @param date	日期（yyyy-MM-dd）
     *
     * @return Date
     */
    public static Date getDate(String date)
    {
        if (date == null)
            return null;
        else
        {
            try
            {
                return DATE_FORMAT.parse(date);
            }
            catch (Exception e)
            {
                log.error("日期格式转换错误：", e);
                return null;
            }
        }
    }

    /**
     * 获取String格式的日期信息（yyyy-MM-dd）
     *
     * @param date
     * @return yyyy-MM-dd
     */
    public static String getDate(Date date)
    {
        if (date == null)
            return "";
        else
            return DATE_FORMAT.format(date);
    }

    /**
     * 获取Date格式的日期时间信息
     *
     * @param dateTime	日期时间（yyyy-MM-dd HH:mm:ss）
     *
     * @return Date
     */
    public static Date getDateTime(String dateTime)
    {
        if (dateTime == null)
            return null;
        else
        {
            try
            {
                return DATE_TIME_FORMAT.parse(dateTime);
            }
            catch (Exception e)
            {
                log.error("日期时间格式转换错误：", e);
                return null;
            }
        }
    }

    /**
     * 获取String格式的日期时间信息（yyyy-MM-dd HH:mm:ss）
     *
     * @param dateTime
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getDateTime(Date dateTime)
    {
        if (dateTime == null)
            return "";
        else
            return DATE_TIME_FORMAT.format(dateTime);
    }

    /**
     * 比较日期大小
     *
     * @param fromDate		开始日期（yyyy-MM-dd）
     * @param toDate		结束日期（yyyy-MM-dd）
     *
     * @return boolean      false：日期参数为null；日期参数非法；起始日期大于结束日期。
     * 						true：日期格式正确，结束日期大于起始日期。
     */
    public static boolean compareDate(String fromDate, String toDate)
    {
        if (fromDate == null || toDate == null)
            return false;

        try
        {
            //时间字符串符合yyyy-MM-dd模式;
            Date from = DATE_FORMAT.parse(fromDate);
            Date to = DATE_FORMAT.parse(toDate);

            if (from.getTime() > to.getTime())
                return false;
        }
        catch (Exception e)
        {
            log.error("日期转换错误:", e);
            return false;
        }

        return true;
    }

    /**
     * 比较2个日期时间的大小
     *
     * @param from	起始日期时间
     * @param to	结束日期时间
     *
     * @return boolean	true：日期时间格式有效 && 起始日期时间<=结束日期时间
     * 					false:日期时间格式无效 || 起始日期时间>结束日期时间
     */
    public static boolean compareDateTime(String from, String to)
    {
        try
        {
            Date fromDateTime = DATE_TIME_FORMAT.parse(from);
            Date toDateTime = DATE_TIME_FORMAT.parse(to);

            if (fromDateTime.getTime() > toDateTime.getTime())
                return false;

        }
        catch (Exception e)
        {
            log.error("日期时间格式错误：", e);
            return false;
        }

        return true;
    }


    /**
     * 比较2个日期时间的大小
     *
     * @param from	起始日期时间
     * @param to	结束日期时间
     *
     * @return boolean	true：日期时间格式有效 && 起始日期时间<=结束日期时间
     * 					false:日期时间格式无效 || 起始日期时间>结束日期时间
     */
    public static Integer compareDate(Date from, Date to)
    {
        try {
            if (from.getTime() > to.getTime()) {
                System.out.println("from 在 to前");
                return 1;
            } else if (from.getTime() < to.getTime()) {
                System.out.println("from 在 to后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 日期格式校验
     *
     * @param date		日期（yyyy-MM-dd）
     * @return boolean  校验成功返回true,否则返回false;
     */
    public static boolean isDate(String date)
    {
        if (date == null)
            return false;

        try
        {
            //时间字符串符合yyyy-MM-dd模式;
            DATE_FORMAT.parse(date);
            return true;
        }
        catch (Exception e)
        {
            log.error("日期格式校验错误：", e);
            return false;
        }
    }

    /**
     * 日期时间格式校验
     *
     * @param dateTime	日期（yyyy-MM-dd HH:mm:ss）
     * @return boolean  校验成功返回true,否则返回false;
     */
    public static boolean isDateTime(String dateTime)
    {
        if (dateTime == null)
            return false;

        try
        {
            DATE_TIME_FORMAT.parse(dateTime);

            return true;
        }
        catch (Exception e)
        {
            log.error("日期时间格式校验错误：", e);
            return false;
        }
    }

    /**
     * 得到某个日期所属月份的最大日期值；
     *
     * @param date String
     * @return int
     */
    public static int getMaxDayOfMonth(String date)
    {
        String[] mydate = date.split("-");

        GregorianCalendar gcal = new GregorianCalendar();
        gcal.set(Calendar.YEAR, Integer.parseInt(mydate[0]));
        gcal.set(Calendar.MONTH, Integer.parseInt(mydate[1]) - 1);
        gcal.set(Calendar.DAY_OF_MONTH, 1);
        //gcal.set(Calendar.DATE, Integer.parseInt(mydate[2]));
        //System.out.println(" Max : " +
        //                   gcal.getActualMaximum(Calendar.DAY_OF_MONTH));

        return gcal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 计算2个日期之间的相隔天数
     *
     * @param fromDate		起始日期
     * @param toDate			结束日期
     *
     * @return 间隔天数
     */
    public static int getDays(String fromDate, String toDate)
    {
        return getDays(getDate(fromDate), getDate(toDate));
    }

    /**
     * 计算2个日期之间的相隔天数
     *
     * @param fromDate		起始日期
     * @param toDate			结束日期
     *
     * @return 间隔天数
     */
    public static int getDays(Date fromDate, Date toDate)
    {
        long times = fromDate.getTime() - toDate.getTime();
        int days = (int)(times / (24 * 60 * 60 * 1000));

//    	log.debug(fromDate + "-" + toDate + "=" + days);
        return days;
    }

    /**
     * 获取指定日期的后N天日期信息
     *
     * @param date 		日期（yyyy-MM-dd）
     * @param days		天数
     *
     * @return String
     */
    public static String afterDays(String date, int days)
    {
        return addDays(date, Math.abs(days));
    }

    /**
     * 获取指定日期的前N天日期信息
     *
     * @param date 		日期（yyyy-MM-dd）
     * @param days		天数
     *
     * @return String
     */
    public static String beforDays(String date, int days)
    {
        return addDays(date, 0 - Math.abs(days));
    }

    /**
     * 获取指定日期的后N天日期信息
     *
     * @param date 		日期（yyyy-MM-dd）
     * @param days		天数（-1表示减去相应的天数）
     *
     * @return String
     */
    private static String addDays(String date, int days)
    {
        //log.debug("Enter addOneDayToCurrentDay()");
        String[] mydate = date.split("-");

        GregorianCalendar gcal = new GregorianCalendar();
        gcal.set(Calendar.YEAR, Integer.parseInt(mydate[0]));
        gcal.set(Calendar.MONTH, Integer.parseInt(mydate[1]) - 1);
        gcal.set(Calendar.DATE, Integer.parseInt(mydate[2]));
        gcal.add(Calendar.DATE, days);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String addDate = sdf.format(gcal.getTime());

        //log.debug("Exit addOneDayToCurrentDay()");
        return addDate;
    }

    /**
     * 获取指定日期时间增加N秒后的日期时间
     *
     * @param dateTime	日期时间（yyyy-MM-dd HH:mm:ss）
     * @param seconds	秒
     *
     * @return String
     */
    public static String addSeconds(String dateTime, int seconds)
    {
        if (isDateTime(dateTime))
        {
            GregorianCalendar gcal = new GregorianCalendar();
            gcal.setTime(getDateTime(dateTime));
            gcal.add(Calendar.SECOND, seconds);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(gcal.getTime());
        }
        else
            return null;
    }

    /**
     * 获取指定日期对应的星期信息
     *
     * @param date String
     *
     * @return int
     */
    public static int getWeek(String date)
    {
        //log.debug("Enter getWeekDayFromDate()");
        String[] mydate = date.split("-");

        GregorianCalendar gcal = new GregorianCalendar();
        gcal.set(Calendar.YEAR, Integer.parseInt(mydate[0]));
        gcal.set(Calendar.MONTH, Integer.parseInt(mydate[1]) - 1);
        gcal.set(Calendar.DATE, Integer.parseInt(mydate[2]));

        return gcal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取中文的星期信息
     *
     * @param date	日期（yyyy-MM-dd）
     *
     * @return 中文星期信息
     */
    public static String getChineseWeek(String date)
    {
        return CHINESE_WEEK[getWeek(date) - 1];
    }

    /**
     * 计算农历 y年的总天数
     *
     * @param year
     * @return 天数
     */
    private static int getYearDays(int year)
    {
        int i, sum = 348;
        for (i = 0x8000; i > 0x8; i >>= 1)
        {
            if (0 != (CHINESE_YEAR[year - 1900] & i))
                sum += 1;
        }

        return (sum + getLeapDays(year));
    }

    /**
     * 计算农历 y年闰月的天数
     *
     * @param
     * @return 天数
     */
    private static int getLeapDays(int year)
    {
        if (0 != getLeapMonths(year))
        {
            if (0 != (CHINESE_YEAR[year - 1900] & 0x10000))
                return 30;
            else
                return 29;
        }
        else
            return 0;
    }

    /**
     * 计算农历 y年闰哪个月 1-12 , 没闰传回 0
     *
     * @param
     * @return 月份
     */
    private static int getLeapMonths(int year)
    {
        return (int) (CHINESE_YEAR[year - 1900] & 0xf);
    }

    /**
     * 计算农历 y年m月的总天数
     *
     * @param
     * @param
     *
     * @return 天数
     */
    private static int getMonthDays(int year, int month)
    {
        if (0 == (CHINESE_YEAR[year - 1900] & (0x10000 >> month)))
            return 29;
        else
            return 30;
    }

    /**
     * 计算农历的月份、日信息
     *
     * @param year
     * @param month
     * @param day
     *
     * @return 农历信息
     */
    @SuppressWarnings("deprecation")
    private static String getChineseMonthAndDay(int year, int month, int day)
    {
        long[] nongDate = new long[7];
        int i = 0, temp = 0, leap = 0;
        Date baseDate = new Date(0, 0, 31);
        Date objDate = new Date(year - 1900, month - 1, day);
        long offset = (objDate.getTime() - baseDate.getTime()) / 86400000L;
        nongDate[5] = offset + 40;
        nongDate[4] = 14;

        for (i = 1900; i < 2050 && offset > 0; i++)
        {
            temp = getYearDays(i);
            offset -= temp;
            nongDate[4] += 12;
        }

        if (offset < 0)
        {
            offset += temp;
            i--;
            nongDate[4] -= 12;
        }

        nongDate[0] = i;
        nongDate[3] = i - 1864;
        leap = getLeapMonths(i); // 闰哪个月
        nongDate[6] = 0;

        for (i = 1; i < 13 && offset > 0; i++)
        {
            // 闰月
            if (leap > 0 && i == (leap + 1) && nongDate[6] == 0)
            {
                --i;
                nongDate[6] = 1;
                temp = getLeapDays((int) nongDate[0]);
            }
            else
                temp = getMonthDays((int) nongDate[0], i);

            // 解除闰月
            if (nongDate[6] == 1 && i == (leap + 1)) nongDate[6] = 0;
            offset -= temp;

            if (nongDate[6] == 0) nongDate[4]++;
        }

        if (offset == 0 && leap > 0 && i == leap + 1)
        {
            if (nongDate[6] == 1)
                nongDate[6] = 0;
            else
            {
                nongDate[6] = 1;
                --i;
                --nongDate[4];
            }
        }

        if (offset < 0)
        {
            offset += temp;
            --i;
            --nongDate[4];
        }

        //月份
        nongDate[1] = i;
        //日
        nongDate[2] = offset + 1;
        String result = "";

        if (1 == nongDate[1])
            result+= "正月";
        else
        if (nongDate[1] <= 10)
            result+= CHINESE_NUMBER[(int)nongDate[1]] + "月";
        else
            result+= "十" + CHINESE_NUMBER[(int)nongDate[1] % 10] + "月";

        if (nongDate[2] <= 10)
            result+= "初" + CHINESE_NUMBER[(int)nongDate[2]];
        else
        if (20 == nongDate[2])
            result+= "二十";
        else
        if (nongDate[2] > 10 && nongDate[2] < 20)
            result+= "十" + CHINESE_NUMBER[(int)nongDate[2] % 10];
        else
        if (nongDate[2] > 20 && nongDate[2] < 30)
            result+= "廿" + CHINESE_NUMBER[(int)nongDate[2] % 10];
        else
        if (30 == nongDate[2])
            result+= "三十";

        return result;
    }

    /**
     * 获取中国农历日期信息（比如：甲子年 五月初八）
     *
     * @param date	日期（yyyy-MM-dd）
     *
     * @return 甲子年 五月初八
     */
    public static String getChineseDate(String date)
    {
        log.debug("-------->getChineseDate()");
        log.debug("日期：" + date);

        String[] tmp = date.split("-");
        int year = Integer.parseInt(tmp[0]);
        int month = Integer.parseInt(tmp[1]);
        int day = Integer.parseInt(tmp[2]);
        int pos = 0;

        String chineseDate = "";
        //天干下标
        pos = (6 + (year - 1900)) % 10;
        log.debug("天干下标:" + pos);
        chineseDate+= TIAN_GAN[pos];
        //天干下标
        pos = (year - 1900) % 12;
        log.debug("地支下标:" + pos);
        chineseDate+= DI_ZHI[pos];

        chineseDate+= "年 ";

        chineseDate+= getChineseMonthAndDay(year, month, day);

        log.debug("<--------getChineseDate()");
        return chineseDate;
    }


    /**
     * @Author: zhangchu@iyungu.com
     * @Description:根据日期和格式返回对应格式的日期
     * @Params:
     *   @param: formatString
     *   @param: date
     * @Return: java.util.Date
     * @Throws:
     * @Date: Created in 9:46 2018/4/20
     */
    public static Date getDateByFormatString(SimpleDateFormat sdf,Date date)
    {
        Date returndate = null;
        try {
            returndate = sdf.parse(sdf.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  returndate;
    }

    /**
     * @Author: wangyarui@iyungu.com
     * @Description:生成时间戳
     * @Date: Created in 10:39 2018/5/4
     * @Param: []
     * @Return: int
     */
    public static Integer getDateMark(){
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMM");
        String strDateMark = sdf.format(new Date());
        Integer dateMark = Integer.parseInt(strDateMark);
        return dateMark;
    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime   当前时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     * @author jqlin
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        return date.after(begin) && date.before(end);
    }

    /**
     * 测试方法
     *
     * @param args
     */
    public static void main(String[] args)
    {
//		log.info(DateUtil.afterDays("2005-11-05", 10));
//		log.info(DateUtil.beforDays("2005-11-05", 10));
//		log.info(getChineseDate("2005-05-23") + " " + getChineseWeek("2005-05-23"));
//		log.info(getChineseDate("2006-02-03") + " " + getChineseWeek("2006-02-03"));
//		log.info(getChineseDate("1978-04-20") + " " + getChineseWeek("1978-04-20"));
//		log.info(getChineseDate("1978-10-05") + " " + getChineseWeek("1978-10-05"));
//		log.info(getDays(getCurrentDate(), "2006-08-01"));
//        int result = getDateMark();
//        log.info(result);
    }
}
