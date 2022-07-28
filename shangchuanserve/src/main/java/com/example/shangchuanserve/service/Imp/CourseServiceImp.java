package com.example.shangchuanserve.service.Imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.shangchuanserve.bean.Course;
import com.example.shangchuanserve.mapper.CourseMapper;
import com.example.shangchuanserve.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImp implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findById(String id) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", id);
        List<Course> list = courseMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public int insertCourse(Course course) {
        int insert = 0;
        insert = courseMapper.insert(course);
        return insert;
    }
}
