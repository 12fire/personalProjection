package com.example.shangchuanserve.bean.resultBeam;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class stuhomeworkVo {

    private String userId;

    private int homeworkId;

    private long time;

    private String userName;


}
