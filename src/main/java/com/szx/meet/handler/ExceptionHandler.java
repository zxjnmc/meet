package com.szx.meet.handler;

import com.szx.meet.constants.BizErrorCode;
import com.szx.meet.constants.IErrorCode;
import com.szx.meet.exception.BizException;
import com.szx.meet.exception.ParaException;
import com.szx.meet.response.ApiResult;
import com.szx.meet.util.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.BindException;

/**
 * @Author szx
 * @Date 2021/7/9 00:28
 * @Description
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandler {

    public ExceptionHandler() {
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ApiResult<Object> handel(Exception e) {
        // Result errorMessage
        String errorMessage = "server error";

        IErrorCode errorCode = null;

        if (e instanceof ParaException) {
            ParaException targetEx = (ParaException) e;
            errorMessage = targetEx.getMessage();
        } else if (e instanceof BizException) {
            BizException targetEx = (BizException) e;
            errorCode = targetEx.getErrorCode();
            errorMessage = targetEx.getMessage();
        } else if (e instanceof DuplicateKeyException) {
            errorCode = BizErrorCode.DUPLICATE_KEY_ERROR;
            errorMessage = errorCode.getMessage();
        } else if (e instanceof IllegalArgumentException) {
            IllegalArgumentException targetEx = (IllegalArgumentException) e;
            errorMessage = targetEx.getMessage();
        } else if (e instanceof HttpMessageNotReadableException || e instanceof BindException) {
            errorMessage = "参数错误";
        }
        String exceptionMsg = ExceptionUtils.getExceptionMsg(e);
        log.error("{}", exceptionMsg);
        if (errorCode != null) {
            return ApiResult.fail(errorCode, errorMessage);
        } else {
            return ApiResult.fail(errorMessage);
        }
    }
}
