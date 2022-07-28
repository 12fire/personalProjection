package com.example.shangchuanserve.service;

import com.example.shangchuanserve.bean.Course;
import com.example.shangchuanserve.bean.HomeWork;

import java.util.List;

public interface homeworkService {

    public boolean insertHomework(HomeWork homeWork);

    public List<HomeWork> gethomeworkByCourseId(int courseId);

    public HomeWork getHoneworkByHomeworkId(int homeworkId);

    public HomeWork findinfobyhomeworkId(String homeworkId);

    public List<HomeWork> findAllhomework();

    public boolean deletHomework(int homeworkId);

    public boolean updateHomework(HomeWork homeWork);

}
