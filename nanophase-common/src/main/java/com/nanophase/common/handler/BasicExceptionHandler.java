package com.nanophase.common.handler;

import com.nanophase.common.util.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class BasicExceptionHandler {

    @ExceptionHandler(Exception.class)
    public R handleException(Exception e){
        e.printStackTrace();
        return R.error("500",e.getMessage());
    }
}
