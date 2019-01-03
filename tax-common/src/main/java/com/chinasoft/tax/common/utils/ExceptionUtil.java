package com.chinasoft.tax.common.utils;

import com.chinasoft.tax.constant.SqlState;
import com.chinasoft.tax.enums.ExceptionCode;
import com.chinasoft.tax.vo.BizException ;
import org.springframework.dao.DuplicateKeyException;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @Author: yaochenglong
 * @Description:异常处理工具类
 */
public class ExceptionUtil {

    /**
     * @Author: zhangchu@iyungu.com
     * @Description:唯一约束冲突处理
     * @Params:
     *   @param: e
     * @Return: void
     * @Throws:
     * @Date: Created in 20:30 2018/5/23
     */
    public static void uniqueConflict(Exception e)
    {
        if(e instanceof DuplicateKeyException) {
            String sqlState = ((SQLIntegrityConstraintViolationException) ((DuplicateKeyException) e).getCause()).getSQLState();
            if (!sqlState.equals(SqlState.UNIQUE_CONFLICT)) {
                throw new BizException(ExceptionCode.SQL_EXECUTE_FAILURE.getCode(), ExceptionCode.SQL_EXECUTE_FAILURE.getMsg());
            }
        }
        else
        {
            throw new BizException(ExceptionCode.SQL_EXECUTE_FAILURE.getCode(), ExceptionCode.SQL_EXECUTE_FAILURE.getMsg());
        }
    }
}

