package com.example.shangchuanserve.service;

import com.example.shangchuanserve.bean.Course;

import java.util.List;

public interface CourseService {

    public List<Course> findById(String id);

    public int insertCourse(Course course);
}
