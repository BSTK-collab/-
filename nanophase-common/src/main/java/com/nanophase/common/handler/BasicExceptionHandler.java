package com.nanophase.common.handler;

import com.nanophase.common.util.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhj
 * @date 2021-03-08
 * 统一异常处理
 */
@ControllerAdvice
@ResponseBody
public class BasicExceptionHandler {

    @ExceptionHandler(NanophaseException.class)
    public R handleException(NanophaseException e) {
        e.printStackTrace();
        return R.error(500, e.getMessage());
    }
}
