package com.example.shangchuanserve.controller;

import com.example.shangchuanserve.bean.User;
import com.example.shangchuanserve.common.Result;
import com.example.shangchuanserve.common.ResultCode;
import com.example.shangchuanserve.common.util.JwtUtil;
import com.example.shangchuanserve.config.annation.PassToken;
import com.example.shangchuanserve.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

public class oauthController {

    @Autowired
    private UserService userService;

    //登录
    @PostMapping(value = "/login")
    @PassToken
    public Result login(@RequestParam("userId") String userId, @RequestParam("passWord") String passWord){
        User user = new User();
        user.setUserId(userId);
        user.setPassWord(passWord);
        System.out.println(userId+passWord);
        Result result = new Result();
        user = userService.getUserByUser(user);

        if(user != null){
            user.setPassWord(null);
            user.setSalt(null);
            String jwtToken = JwtUtil.getJwtToken(user.getUserId());
            result.setResult(ResultCode.SUCCESS);
            result.setData(user);
            result.setToken(jwtToken);
        }
        return result;
    }
}
