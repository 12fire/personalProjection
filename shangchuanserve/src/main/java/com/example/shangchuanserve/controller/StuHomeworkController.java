package com.example.shangchuanserve.controller;

import com.example.shangchuanserve.bean.StuHomework;
import com.example.shangchuanserve.bean.resultBeam.stuhomeworkVo;
import com.example.shangchuanserve.common.Result;
import com.example.shangchuanserve.common.ResultCode;
import com.example.shangchuanserve.config.annation.Auth;
import com.example.shangchuanserve.config.annation.UserLoginToken;
import com.example.shangchuanserve.service.UserService;
import com.example.shangchuanserve.service.stuhomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class StuHomeworkController {

    @Autowired
    private stuhomeworkService  stuhomeworkservice;



    @GetMapping(value = "getFinishedStuHomework")
    @UserLoginToken
    @Auth
    public Result FinishedStuHomework(@RequestParam int homeworkId){

        List<stuhomeworkVo> list = stuhomeworkservice.findbyHomeworkId(homeworkId);
        Result result = new Result();
        result.setResult(ResultCode.SUCCESS);
        result.setData(list);
        return result;
    }
}

