package com.example.shangchuanserve.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sc_stuhomework")
public class StuHomework {


    @TableField("userId")
    private String userId;

    @TableField("homeworkId")
    private int homeworkId;

    @TableField("time")
    private long time;

    @TableField("fileURL")
    private String fileURL;


}
