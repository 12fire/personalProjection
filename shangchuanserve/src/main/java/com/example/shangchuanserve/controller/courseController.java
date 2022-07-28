package com.example.shangchuanserve.controller;

import com.example.shangchuanserve.bean.Course;
import com.example.shangchuanserve.bean.User;
import com.example.shangchuanserve.common.Result;
import com.example.shangchuanserve.common.ResultCode;
import com.example.shangchuanserve.common.util.JwtUtil;
import com.example.shangchuanserve.common.util.MyThreadLocalUtil;
import com.example.shangchuanserve.config.annation.Auth;
import com.example.shangchuanserve.config.annation.UserLoginToken;
import com.example.shangchuanserve.service.CourseService;
import com.example.shangchuanserve.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class courseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("getCourse")
    @UserLoginToken
    @Auth
    public Result getCourse() {
//        String userId = JwtUtil.getMemberIdByJwtToken(token);
        User user = MyThreadLocalUtil.get();
        List<Course> courseList = courseService.findById(user.getUserId());
        Result result = new Result();
        result.setResult(ResultCode.SUCCESS);
        result.setData(courseList);
        MyThreadLocalUtil.remove();
        return result;
    }

    @PostMapping("addCouses")
    @UserLoginToken
    @Auth
    public Result addCourse(@RequestBody Course course){
        System.out.println(course.toString());
//        String userId = JwtUtil.getMemberIdByJwtToken(token);
        User user = MyThreadLocalUtil.get();
        course.setUserId(user.getUserId());
        int i = courseService.insertCourse(course);
        Result result = new Result();
        if(i > 0){
            result.setResult(ResultCode.SUCCESS);
        }else{
            result.setResult(ResultCode.ERROR);
        }
        MyThreadLocalUtil.remove();
        return result;
    }


}
