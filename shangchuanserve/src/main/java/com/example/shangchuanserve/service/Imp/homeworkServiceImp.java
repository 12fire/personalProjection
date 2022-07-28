package com.example.shangchuanserve.service.Imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.shangchuanserve.bean.Course;
import com.example.shangchuanserve.bean.HomeWork;
import com.example.shangchuanserve.bean.UserCourse;
import com.example.shangchuanserve.common.util.DateUtil;
import com.example.shangchuanserve.mapper.CourseMapper;
import com.example.shangchuanserve.mapper.homeworkMapper;
import com.example.shangchuanserve.service.CourseService;
import com.example.shangchuanserve.service.UserCourseService;
import com.example.shangchuanserve.service.homeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class homeworkServiceImp implements homeworkService {

    @Autowired
    private homeworkMapper homeworkmapper;

    @Autowired
    private UserCourseService userCourseService;
    @Override
    public boolean insertHomework(HomeWork homeWork) {

//        int homeworkDeadtime = Integer.parseInt(homeWork.getHomeworkDeadtime());
//        String datefromat = DateUtil.datefromat(homeworkDeadtime);
//        homeWork.setHomeworkDeadtime(datefromat);
        int insert = 0;
        insert = homeworkmapper.insert(homeWork);
        return insert > 0?true:false;
    }

    @Override
    public List<HomeWork> gethomeworkByCourseId(int courseId) {
        List<HomeWork> listHomework = new ArrayList<>();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("courseId",courseId);
//        queryWrapper.gt("ddl", System.currentTimeMillis());

        List list = homeworkmapper.selectList(queryWrapper);

        return list;
    }

    @Override
    public HomeWork getHoneworkByHomeworkId(int homeworkId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("homeworkId",homeworkId);
        HomeWork homeWork = homeworkmapper.selectOne(queryWrapper);
        return homeWork;
    }

    @Override
    public HomeWork findinfobyhomeworkId(String homeworkId) {
        QueryWrapper<HomeWork> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("homeworkId",homeworkId);
        HomeWork homeWork = homeworkmapper.selectOne(queryWrapper);
        return homeWork;
    }

    @Override
    public List<HomeWork> findAllhomework() {
        QueryWrapper<HomeWork> queryWrapper = new QueryWrapper<>();
        List<HomeWork> homeWorkList = homeworkmapper.selectList(queryWrapper);
        return homeWorkList;
    }

    @Override
    public boolean deletHomework(int homeworkId) {

        QueryWrapper<HomeWork> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("homeworkId",homeworkId);
        int delete = homeworkmapper.delete(queryWrapper);

        return delete > 0?true:false;
    }

    @Override
    public boolean updateHomework(HomeWork homeWork) {
        QueryWrapper<HomeWork> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("homeworkId",homeWork.getHomeworkId());
//        int homeworkDeadtime = Integer.parseInt(homeWork.getDdl());
//        String datefromat = DateUtil.datefromat(homeworkDeadtime);
//        homeWork.setDdl(datefromat);
        int update = homeworkmapper.update(homeWork, queryWrapper);
        return update > 0?true:false;
    }


}
