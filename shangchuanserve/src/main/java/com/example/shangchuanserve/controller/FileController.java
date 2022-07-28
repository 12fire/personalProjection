package com.example.shangchuanserve.controller;

import com.example.shangchuanserve.bean.HomeWork;
import com.example.shangchuanserve.bean.StuHomework;
import com.example.shangchuanserve.bean.User;
import com.example.shangchuanserve.common.Result;
import com.example.shangchuanserve.common.ResultCode;
import com.example.shangchuanserve.common.util.JwtUtil;
import com.example.shangchuanserve.common.util.MyThreadLocalUtil;
import com.example.shangchuanserve.config.annation.Auth;
import com.example.shangchuanserve.config.annation.UserLoginToken;
import com.example.shangchuanserve.exception.FileException;
import com.example.shangchuanserve.service.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class FileController {

    @Autowired
    private FileService fileService;
    @Autowired
    private UserService userService;

    @Autowired
    private stuhomeworkService stuHService;

    @Autowired
    private homeworkService homeworkservice;

    @PostMapping(value = "downloadOne")
    @UserLoginToken
    @Auth
    public void downloadOne(@RequestBody StuHomework stuHomework, HttpServletResponse response) throws Exception {
        stuHomework = stuHService.findbyHomeworkIdAndCourseId(stuHomework);
        if(stuHomework != null) {
            fileService.FileDownload(stuHomework.getFileURL(), response);
        }else{
            new FileException("用户未提交文件");
        }

    }
    //打包下载
    @GetMapping(value = "downloadAll")
    @UserLoginToken
    @Auth
    public void downloadZip(@RequestParam("homeworkId") int homeworkId, HttpServletResponse response) throws Exception {
        HomeWork homework = homeworkservice.getHoneworkByHomeworkId(homeworkId);
        fileService.ZipFile(homework,response);

    }

    @PostMapping(value = "/upload")
    @UserLoginToken
    public Result uploadFile(@RequestParam MultipartFile File,@RequestHeader String token,
                           @RequestHeader String homeworkId) throws Exception {
//        String userId = JwtUtil.getMemberIdByJwtToken(token);
        System.out.println("upload------------");
        User user = MyThreadLocalUtil.get();
        System.out.println(homeworkId);
        fileService.FileUpload(File, user.getUserId(),Integer.parseInt(homeworkId));
        Result result = new Result();
        result.setResult(ResultCode.SUCCESS);
        return result;
    }


}
