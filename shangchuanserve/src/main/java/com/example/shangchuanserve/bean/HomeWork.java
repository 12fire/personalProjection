package com.example.shangchuanserve.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sc_homework")
public class HomeWork {

    @TableId(type = IdType.AUTO)
    @TableField("homeworkId")
    private int homeworkId;

    @TableField("courseId")
    private String courseId;

    @TableField("homeworkName")
    private String homeworkName;

    //作业格式
    @TableField("format")
    private String format;

    @TableField("ddl")
    private long ddl;

    @TableField("homeworkUrl")
    private String homeworkUrl;






}
