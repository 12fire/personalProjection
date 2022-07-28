package com.example.shangchuanserve.service.Imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.shangchuanserve.bean.StuHomework;
import com.example.shangchuanserve.bean.User;
import com.example.shangchuanserve.bean.resultBeam.stuhomeworkVo;
import com.example.shangchuanserve.mapper.stuhomeworkMapper;
import com.example.shangchuanserve.service.UserService;
import com.example.shangchuanserve.service.stuhomeworkService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class stuhomeworkImp implements stuhomeworkService {

    @Autowired
    private stuhomeworkMapper stuHMapper;

    @Autowired
    private UserService userService;
    @Override
    public List<stuhomeworkVo> findbyHomeworkId(int homeworkId) {
        QueryWrapper<StuHomework> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("homeworkId",homeworkId);
        List<StuHomework> stuHomeworks = stuHMapper.selectList(queryWrapper);
        List<stuhomeworkVo> list_stuhomeworkVo = new ArrayList<>();
        for(int i = 0; i < stuHomeworks.size(); i++){
            User user = userService.getUserByUserId(stuHomeworks.get(i).getUserId());
            stuhomeworkVo shVo = new stuhomeworkVo();
            shVo.setHomeworkId(stuHomeworks.get(i).getHomeworkId());
            shVo.setTime(stuHomeworks.get(i).getTime());
            shVo.setUserId(stuHomeworks.get(i).getUserId());
            shVo.setUserName(user.getUserName());
            list_stuhomeworkVo.add(i,shVo);
        }
        return list_stuhomeworkVo;
    }

    @Override
    public boolean insertStuHomework(StuHomework stuHomework) {
        int insert = 0;
        try{
            insert = stuHMapper.insert(stuHomework);
        }catch (Exception e){
            QueryWrapper<StuHomework> queryWrapper = new QueryWrapper();
            queryWrapper.eq("homeworkId",stuHomework.getHomeworkId())
                    .eq("userId",stuHomework.getUserId());
            insert = stuHMapper.update(stuHomework,queryWrapper);
        }


        return insert > 0? true:false;
    }

    @Override
    public StuHomework findbyHomeworkIdAndCourseId(StuHomework stuHomework) {
        QueryWrapper<StuHomework> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("homeworkId",stuHomework.getHomeworkId())
                .eq("userId",stuHomework.getUserId());
        stuHomework = stuHMapper.selectOne(queryWrapper);
        return stuHomework;
    }
}
