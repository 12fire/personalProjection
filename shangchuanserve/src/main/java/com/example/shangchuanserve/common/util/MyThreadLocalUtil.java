package com.example.shangchuanserve.common.util;

import com.example.shangchuanserve.bean.User;

public class MyThreadLocalUtil {

    private MyThreadLocalUtil(){}
    //线程变量隔离
    private static final ThreadLocal<User> LOCAL = new ThreadLocal<>();

    public static void put(User sysUser){
        LOCAL.set(sysUser);
    }
    public static User get(){
        return LOCAL.get();
    }
    public static void remove(){
        LOCAL.remove();
    }
}
