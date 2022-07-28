package com.example.shangchuanserve.common;

public enum ResultCode {

    /* 成功状态码 */
    SUCCESS(1, "成功"),
    ERROR(0, "失败"),




    /* 用户错误：20001-29999*/
    USER_NOT_LOGGED_IN(0, "用户未登录"),
    USER_LOGIN_ERROR(0, "账号或密码错误"),
    USER_ACCOUNT_FORBIDDEN(0, "账号已被禁用"),
    USER_NOT_EXIST(0, "用户不存在"),
    USER_HAS_EXISTED(0, "用户已存在"),
    USER_Register_ERROR(0, "用户注册错误"),







    /* 文件上传 */
    UPLOAD_SUCCESS(1,"上传成功"),
    UPLOAD_ERROR(0, "上传失败"),

    SESSION_TIME_OUT(0, "登录超时");



    private Integer code;

    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public static String getMessage(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.message;
            }
        }
        return name;
    }

    public static Integer getCode(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.code;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name();
    }

}
