package com.example.shangchuanserve.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sc_stucourse")
public class UserCourse {

    @TableField("courseId")
    private int courseId;

    @TableField("userId")
    private String userId;
}
