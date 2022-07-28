package com.example.shangchuanserve.controller;

import com.example.shangchuanserve.bean.UserCourse;
import com.example.shangchuanserve.common.Result;
import com.example.shangchuanserve.common.ResultCode;
import com.example.shangchuanserve.common.util.JwtUtil;
import com.example.shangchuanserve.config.annation.Auth;
import com.example.shangchuanserve.config.annation.UserLoginToken;
import com.example.shangchuanserve.service.CourseService;
import com.example.shangchuanserve.service.UserCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/server/subject")
public class SubjectController {

    @Autowired
    private UserCourseService userCourseService;

    @Autowired
    private CourseService courseService;

//    // 获取学生列表
//    @PostMapping(value = "/selectStuBySubjectId")
//    @UserLoginToken
//    @Auth
//    public Result getCourseStudents(@RequestParam(value = "subjectId") int subjectId){
//        Result result = new Result();
//        List<UserCourse> userByCouserId = userCourseService.findUserByCouserId(subjectId);
//        result.setResult(ResultCode.SUCCESS);
//        result.setData(userByCouserId);
//        return result;
//    }
//
//    //添加学生到课程
//    @PostMapping(value = "/addStuToSubject")
//    @UserLoginToken
//    @Auth
//    public Result addMenber(@RequestParam(value = "subjectId") int subjectId, @RequestParam(value = "userId") String userId){
//        Result result = new Result();
//        UserCourse userCourse = new UserCourse();
//        userCourse.setCourseId(subjectId);
//        userCourse.setUserId(userId);
//        boolean b = userCourseService.insertUserCourse(userCourse);
//        if(b){
//            result.setResult(ResultCode.SUCCESS);
//        }else {
//            result.setResult(ResultCode.ERROR);
//        }
//        return result;
//    }

    //从课程中删除学生
    @UserLoginToken
    @PostMapping(value = "/deleteStuBySubjectIdAndUserId")
    @Auth
    public Result removeMember(@RequestParam(value = "subjectId") int subjectId, @RequestParam(value = "userId") String userId){
        Result result = new Result();
        UserCourse userCourse = new UserCourse();
        userCourse.setCourseId(subjectId);
        userCourse.setUserId(userId);
        boolean b = userCourseService.delUserCourse(userCourse);
        if(b){
            result.setResult(ResultCode.SUCCESS);
        }else {
            result.setResult(ResultCode.ERROR);
        }

        return result;
    }




}
