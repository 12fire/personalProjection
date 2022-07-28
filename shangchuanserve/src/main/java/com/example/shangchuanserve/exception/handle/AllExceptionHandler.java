package com.example.shangchuanserve.exception.handle;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.example.shangchuanserve.common.Result;
import com.example.shangchuanserve.common.ResultCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class AllExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result doException(Exception e){
//        e.printStackTrace();
        Result result = new Result();
        result.setResult(ResultCode.ERROR);
        result.setData(null);
        return result;
    }
}
