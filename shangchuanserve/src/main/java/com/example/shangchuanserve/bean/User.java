package com.example.shangchuanserve.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sc_user")
public class User implements Serializable {

    @TableField("userId")
    private String userId;

    @TableField("passWord")
    private String passWord;

    @TableField("userName")
    private String userName;

    @TableField("salt")
    private String salt;

    @TableField("userType")
    private char userType;

}
