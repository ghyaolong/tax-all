package com.chinasoft.tax.common.utils;

import com.chinasoft.tax.constant.MsgStatus;
import com.chinasoft.tax.vo.Message;

/**
 * @Author: zhangchu@iyungu.com
 * @Description:
 * @Date: 11:30 2018/2/1
 * @Modified By:
 */
public class ResponseUtil {

    public static Message responseBody(Object data) {
        Message message = new Message();
        message.setData(data);
        return message;
    }

    /**
     * 数据校验异常
     * @param data
     * @param msg
     * @return
     */
    public static Message responseBody(String code,String msg,Object data) {
        Message message = new Message();
        message.setData(data);
        message.setStatus(MsgStatus.FAILURE);
        message.setErrCode(code);
        message.setErrMsg(msg);
        return message;
    }

    /**
     * 放异常code码和错误消息
     * @param code
     * @param msg
     * @return
     */
    public static Message responseBody(String code,String msg) {
        Message message = new Message();
        message.setStatus(MsgStatus.FAILURE);
        message.setErrCode(code);
        message.setErrMsg(msg);
        return message;
    }

    /**
     * 只用于登录返回
     * @param code
     * @param msg
     * @param data
     * @param token
     * @return
     */
    public static Message responseBody(String code,String msg,Object data,String token) {
        Message message = new Message();
        message.setData(data);
        message.setStatus(MsgStatus.SUCCESS);
        message.setToken(token);
        return message;
    }

}
