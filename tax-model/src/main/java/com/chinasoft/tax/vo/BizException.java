package com.chinasoft.tax.vo;

import com.chinasoft.tax.enums.ExceptionCode;

/**
 * @author yaochenglong
 */
public class BizException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String errCode;//异常编码
    private String errMsg;//异常描述
    private Object data;//异常时客户端需要的数据

    public BizException(String errCode){
        this.errCode = errCode;
    }

    public BizException(String errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public BizException(ExceptionCode en){
        super(en.getMsg());
        this.errCode = en.getCode();
        this.errMsg = en.getMsg();
    }

    public BizException(String errCode, String errMsg, Object data) {
        super(errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.data = data;
    }

    public BizException(String errCode, String errMsg, String data, Throwable cause) {
        super(errMsg,cause);
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.data = data;
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String errMsg, Throwable cause) {
        super(errMsg, cause);
        this.errMsg = errMsg;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BizException [errCode=" + errCode + ", errMsg=" + errMsg + ", data=" + data + "]";
    }
}
