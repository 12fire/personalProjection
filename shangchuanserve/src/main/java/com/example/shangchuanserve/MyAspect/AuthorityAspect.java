package com.example.shangchuanserve.MyAspect;

import com.example.shangchuanserve.common.Result;
import com.example.shangchuanserve.common.ResultCode;
import com.example.shangchuanserve.common.util.MyThreadLocalUtil;
import com.example.shangchuanserve.exception.AuthorityException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AuthorityAspect {
    @Pointcut("@annotation(com.example.shangchuanserve.config.annation.Auth)")
    private void mypt(){
        //无需代码
    }

    @Before(value = "mypt()")
    public void before(JoinPoint jp) throws Exception {
        Result result = new Result();
        if("1".equals(MyThreadLocalUtil.get().getUserType()+"")){
            MyThreadLocalUtil.remove();

            throw new AuthorityException("普通用户，权限不足");
        }

    }
}
