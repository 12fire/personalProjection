package com.example.shangchuanserve.controller;

import com.example.shangchuanserve.bean.User;
import com.example.shangchuanserve.bean.UserCourse;
import com.example.shangchuanserve.common.Result;
import com.example.shangchuanserve.common.ResultCode;
import com.example.shangchuanserve.config.annation.UserLoginToken;
import com.example.shangchuanserve.service.UserCourseService;
import com.example.shangchuanserve.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping
public class userCourseController {

    @Autowired
    private UserCourseService userCourseService;

    @Autowired
    private UserService userService;

    @GetMapping("getAllstudentCourse")
    @UserLoginToken
    public Result getCourse(@RequestParam int courseId) {
        List<UserCourse> list_userId = userCourseService.findUserByCouserId(courseId);
        List<User> list_user = new ArrayList<>();
        for (int i = 0; i < list_userId.size(); i++) {
            User user = userService.getUserByUserId(list_userId.get(i).getUserId());
            user.setSalt(null);
            user.setPassWord(null);
            list_user.add(user);

        }
        Result result = new Result();

        result.setResult(ResultCode.SUCCESS);
        result.setData(list_user);


        return result;
    }
}
