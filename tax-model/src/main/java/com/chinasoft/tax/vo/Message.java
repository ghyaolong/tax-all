package com.chinasoft.tax.vo;
import com.alibaba.fastjson.annotation.JSONField;
import com.chinasoft.tax.constant.MsgStatus;

public class Message {
    @JSONField(ordinal = 1)
    private String status= MsgStatus.SUCCESS;//1:失败，0:成功，失败返回errCode和errMsg
    @JSONField(ordinal = 2)
    private Object data;//成功返回的数据
    @JSONField(ordinal = 3)
    private String errCode="";//错误代码
    @JSONField(ordinal = 4)
    private String errMsg="";//错误内容
    @JSONField(ordinal = 5)
    private String token="";

    public Message(){

    }

    public Message(String status, Object data,String token){
        this.status = status;
        setData(data);
    }

    public Message(String status, String errCode, String errMsg,String token){
        this.status = status;
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public Message(String status, String errCode, String errMsg, Object data,String token){
        this.status = status;
        this.errCode = errCode;
        this.errMsg = errMsg;
        setData(data);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Message [status=" + status + ", data=" + data + ", errCode=" + errCode + ", errMsg=" + errMsg + ",token="+token+"]";
    }
}
