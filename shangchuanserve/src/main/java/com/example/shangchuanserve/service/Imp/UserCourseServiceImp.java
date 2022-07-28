package com.example.shangchuanserve.service.Imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.shangchuanserve.bean.UserCourse;
import com.example.shangchuanserve.mapper.UserCourseMapper;
import com.example.shangchuanserve.service.UserCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserCourseServiceImp implements UserCourseService {

    @Autowired
    private UserCourseMapper userCourseMapper;
    @Override
    public List<UserCourse> findUserByCouserId(int courseId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("courseId", courseId);
        List list = userCourseMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public boolean insertUserCourse(UserCourse userCourse) {
        int insert = 0;
        QueryWrapper<UserCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("courseId",userCourse.getCourseId()).eq("userName",userCourse.getUserId());
        UserCourse userCourse1 = userCourseMapper.selectOne(queryWrapper);
        if(userCourse1 == null){
            insert = userCourseMapper.insert(userCourse);
        }


        return insert > 0 ? true:false;
    }

    @Override
    public boolean delUserCourse(UserCourse userCourse) {
        QueryWrapper<UserCourse> queryWrapper = new QueryWrapper();
        queryWrapper.eq("courseId",userCourse.getCourseId()).eq("userName",userCourse.getUserId());
        int delete = 0;
        delete = userCourseMapper.delete(queryWrapper);
        return delete > 0 ? true:false;
    }

    @Override
    public List findCourseByUserId(String userId) {
        QueryWrapper<UserCourse> queryWrapper = new QueryWrapper();
        queryWrapper.eq("userId", userId);
        List<UserCourse> list = userCourseMapper.selectList(queryWrapper);
        return list;
    }
}
