package com.example.shangchuanserve.service;

import com.example.shangchuanserve.bean.User;

public interface UserService {

    public Boolean insertUser(User user);

    public Boolean updataUser(User user, String newpassword);

    public User getUserByUsername(String username);

    public User getUserByUserId(String userId);

    public User getUserByUser(User user);

    public User getUserByName(String name);
}
