package com.example.shangchuanserve.service.Imp;


import com.example.shangchuanserve.bean.HomeWork;
import com.example.shangchuanserve.bean.StuHomework;
import com.example.shangchuanserve.bean.User;
import com.example.shangchuanserve.common.util.FileUtil;
import com.example.shangchuanserve.exception.FileException;
import com.example.shangchuanserve.service.FileService;
import com.example.shangchuanserve.service.UserService;
import com.example.shangchuanserve.service.homeworkService;
import com.example.shangchuanserve.service.stuhomeworkService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Service
public class FileServiceImp implements FileService {

    @Autowired
    private homeworkService homeworkservice;

    @Autowired
    private UserService userService;

    @Autowired
    private stuhomeworkService shomeworkService;

    @Override
    public void FileDownload(String name, HttpServletResponse response) throws Exception {

        File file = new File(name);

        if (!file.exists()) {
            throw new FileException(name + "文件不存在");
        }
        response.setContentType("application/force-download");
        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("content-type","text/html;charset = UTF-8");
        String newFileName = name.substring(name.lastIndexOf("/") + 1);
        response.addHeader("Content-Disposition", "attachment;fileName="
                + java.net.URLEncoder.encode(newFileName,"UTF-8"));


        byte[] buffer = new byte[1024];
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            OutputStream os = response.getOutputStream();

            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        }

    }

    /*
    上传文件
     */
    @Override
    public void FileUpload(MultipartFile file,String userId, int homeworkId) throws Exception {

//        Result result = new Result()
        HomeWork homeWork= homeworkservice.getHoneworkByHomeworkId(homeworkId);
        String last_FileName = file.getOriginalFilename().substring(
                file.getOriginalFilename().lastIndexOf("."));
        if (file == null || file.isEmpty()) {
            throw new FileException("未选择需上传的日志文件");
        }

        //删除原有的文件
        StuHomework temp = new StuHomework();
        temp.setHomeworkId(homeworkId);
        temp.setUserId(userId);
        temp = shomeworkService.findbyHomeworkIdAndCourseId(temp);
        if(temp != null){
            new File(temp.getFileURL()).delete();
        }


        User user = userService.getUserByUserId(userId);
        String filePath = new File(homeWork.getHomeworkUrl()).getAbsolutePath();
        File fileUpload = new File(filePath);
        if (!fileUpload.exists()) {
            fileUpload.mkdirs();
        }
        String filename = FileUtil.changeFileName(homeWork.getFormat(),user,homeWork.getHomeworkName())+last_FileName;




        StuHomework stuHomework = new StuHomework();
        stuHomework.setFileURL(homeWork.getHomeworkUrl()+"/"+filename);
        stuHomework.setHomeworkId(homeworkId);
        stuHomework.setUserId(userId);
        stuHomework.setTime(System.currentTimeMillis());
        shomeworkService.insertStuHomework(stuHomework);
        fileUpload = new File(filePath, filename);
        file.transferTo(fileUpload);

//        return "D://project//logs_app/";


    }



    @Override
    public void ZipFile(HomeWork homeWork,HttpServletResponse response) throws Exception {
        String name = "D:/2021java/springboot_project/zip/"+homeWork.getHomeworkName()+".zip";
        File file = new File(name);
        FileOutputStream fos1 = new FileOutputStream(file);
        FileUtil.toZip(homeWork.getHomeworkUrl(), fos1,true);



        if (!file.exists()) {
            throw new FileException(homeWork.getHomeworkName()+".zip"+ "文件不存在");
        }
        response.setContentType("application/force-download");
        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("content-type","text/html;charset = UTF-8");
        String newFileName = homeWork.getHomeworkName()+".zip";
        response.addHeader("Content-Disposition", "attachment;fileName="
                + java.net.URLEncoder.encode(newFileName,"UTF-8"));


        byte[] buffer = new byte[1024];
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        }
        FileUtil.del(name);
    }
}
