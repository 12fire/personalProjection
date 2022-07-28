package com.example.shangchuanserve.service;


import com.example.shangchuanserve.bean.UserCourse;

import java.util.List;

public interface UserCourseService {

    public List<UserCourse> findUserByCouserId(int courseId);

    public boolean insertUserCourse(UserCourse userCourse);

    public boolean delUserCourse(UserCourse userCourse);

    public List findCourseByUserId(String UserId);
}
