package com.example.shangchuanserve.controller;

import com.example.shangchuanserve.bean.HomeWork;
import com.example.shangchuanserve.bean.User;
import com.example.shangchuanserve.bean.UserCourse;
import com.example.shangchuanserve.common.Result;
import com.example.shangchuanserve.common.ResultCode;
import com.example.shangchuanserve.common.util.JwtUtil;
import com.example.shangchuanserve.common.util.MyThreadLocalUtil;
import com.example.shangchuanserve.config.annation.Auth;
import com.example.shangchuanserve.config.annation.UserLoginToken;
import com.example.shangchuanserve.service.UserCourseService;
import com.example.shangchuanserve.service.homeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping
public class homeworkController {

    @Autowired
    private homeworkService heService;

    @Autowired
    private UserCourseService userCourseService;
    //添加课程作业
    @PostMapping(value = "addHomework")
    @UserLoginToken
    @Auth
    public Result addWork(@RequestBody HomeWork homework){
        homework.setHomeworkUrl("D://2021java//springboot_project/"+homework.getCourseId()+"/"+homework.getHomeworkName());
        Result result = new Result();
        boolean b = heService.insertHomework(homework);
        if(b){
            result.setResult(ResultCode.SUCCESS);
        }else {
            result.setResult(ResultCode.ERROR);
        }


        return result;
    }
//    通过课程号获取所有的作业
    @GetMapping(value = "gethomeworkMessageByCourseId")
    @UserLoginToken
    @Auth
    public Result gethomeworkMessageByCourseId(@RequestParam int courseId){
        List<HomeWork> list = heService.gethomeworkByCourseId(courseId);
        Result result = new Result();
        result.setData(list);
        return result;
    }
    //删除作业
    @GetMapping(value = "delhomework")
    @UserLoginToken
    @Auth
    public Result delhomework(@RequestParam int homeworkId){
        boolean b = heService.deletHomework(homeworkId);
        Result result = new Result();
        if(b){
            result.setResult(ResultCode.SUCCESS);

        }else{
            result.setResult(ResultCode.ERROR);
        }
        return result;

    }






    // 获取某学生的所有作业
    @GetMapping(value = "getHomework")
    @UserLoginToken
//    @Auth
    public Result getStudentWorkList(@RequestHeader String token){
//        String userId = JwtUtil.getMemberIdByJwtToken(token);
        System.out.println("----------");
        User user = MyThreadLocalUtil.get();
        List<UserCourse> userCourseList = userCourseService.findCourseByUserId(user.getUserId());
        List<HomeWork> list_homework = new ArrayList<>();
        Result result = new Result();
        for(int i = 0; i < userCourseList.size(); i++){
            List<HomeWork> list = heService.gethomeworkByCourseId(userCourseList.get(i).getCourseId());
            if(list != null ){
                for(int j = 0; j < list.size(); j++){
                    if(list.get(j).getDdl() > System.currentTimeMillis()){
                        list_homework.add(list.get(j));
                    }

                }
            }

        }


//        System.out.println("list"+list);
        result.setResult(ResultCode.SUCCESS);
        result.setData(list_homework);
        MyThreadLocalUtil.remove();

        return result;
    }

}
