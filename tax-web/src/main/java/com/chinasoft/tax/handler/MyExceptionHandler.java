package com.chinasoft.tax.handler;
import com.chinasoft.tax.common.utils.ResponseUtil;
import com.chinasoft.tax.enums.ExceptionCode;
import com.chinasoft.tax.vo.BizException;
import com.chinasoft.tax.vo.Message;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;

@Slf4j
@ControllerAdvice
public class MyExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(MyExceptionHandler.class);


    //@SecurityParameter(outEncode = true,inDecode = false)
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Message handler(Exception ex){
        StringBuilder builder = new StringBuilder();
        BizException bizException = null;

        if(ex instanceof MultipartException){
            return ResponseUtil.responseBody("A-000046", "上传文件不能超过4MB",null);
        }
        if( ex instanceof BizException){
            builder.append("\n【业务异常】"+ex);
            logger.warn("【业务异常】"+ex);
        }else {
            builder.append("\n【系统异常】"+ex);
            logger.warn("【系统异常】"+ex);
        }
        for(int i=0;i<5;i++){
            StackTraceElement e = ex.getStackTrace()[i];
            String str = e.getFileName()+":"+e.getClassName()+":"+e.getMethodName()+":"+e.getLineNumber();
            builder.append("\n"+str);
            logger.warn(str);
        }
        if(ex instanceof  BizException){
            bizException = (BizException)ex;
        }else{
            bizException = new BizException(ExceptionCode.UNKNOWN_ERROR.getCode(),builder.toString());
        }
        log.info("异常处理类中：返回的加密前的字符：[错误码"+bizException.getErrCode()+";错误信息"+bizException.getErrMsg()+";"+bizException.getData()+"]");
        return ResponseUtil.responseBody(bizException.getErrCode(), bizException.getErrMsg(),bizException.getData());
    }

}

