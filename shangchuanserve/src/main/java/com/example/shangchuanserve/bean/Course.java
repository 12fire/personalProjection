package com.example.shangchuanserve.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sc_course")
public class Course {

    @TableField("userId")
    private String userId;

    @TableField("courseId")
    @TableId(type = IdType.AUTO)
    private String courseId;
    @TableField("courseName")
    private String courseName;

}
