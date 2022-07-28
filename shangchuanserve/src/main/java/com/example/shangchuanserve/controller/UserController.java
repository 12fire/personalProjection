package com.example.shangchuanserve.controller;

import com.example.shangchuanserve.bean.User;
import com.example.shangchuanserve.common.Result;
import com.example.shangchuanserve.common.ResultCode;
import com.example.shangchuanserve.common.util.JwtUtil;
import com.example.shangchuanserve.common.util.MyThreadLocalUtil;
import com.example.shangchuanserve.config.annation.UserLoginToken;
import com.example.shangchuanserve.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;




    //通过token获取学生信息
    @PostMapping(value = "userMessage")
    @UserLoginToken
    public Result userMessage(@RequestHeader(value = "token") String token){
       Result result = new Result();
        User user  = MyThreadLocalUtil.get();
        if(user != null){
            user.setPassWord(null);
            user.setSalt(null);
            result.setResult(ResultCode.SUCCESS);
        }else{
            result.setResult(ResultCode.ERROR);
        }
        result.setData(user);
        MyThreadLocalUtil.remove();
        return result;
    }
}
