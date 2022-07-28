package com.example.shangchuanserve.service;

import com.example.shangchuanserve.bean.StuHomework;
import com.example.shangchuanserve.bean.resultBeam.stuhomeworkVo;

import java.util.List;

public interface stuhomeworkService {

//    public StuHomework findonebyId(String id);

    public List<stuhomeworkVo> findbyHomeworkId(int homeworkId);

    public boolean insertStuHomework(StuHomework stuHomework);

    public StuHomework findbyHomeworkIdAndCourseId(StuHomework stuHomework);
}
