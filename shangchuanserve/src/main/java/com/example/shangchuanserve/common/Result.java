package com.example.shangchuanserve.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private Integer flag;

    private String msg;

    private String token;

    private Object data;

    public Result(ResultCode code) {

        setResult(code);
    }

    public void setResult(ResultCode resultCode){
        this.flag = resultCode.code();
        this.msg = resultCode.message();
    }


}
