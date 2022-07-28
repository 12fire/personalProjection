package com.example.shangchuanserve.service;


import com.example.shangchuanserve.bean.HomeWork;
import com.example.shangchuanserve.bean.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public interface FileService {

    public void FileDownload(String name, HttpServletResponse response) throws Exception;

    public void FileUpload(MultipartFile file, String userId, int homeworkId) throws Exception;




    public void ZipFile(HomeWork homeWork,HttpServletResponse response) throws Exception;

}
