package com.example.shangchuanserve.service.Imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.shangchuanserve.bean.User;
import com.example.shangchuanserve.common.util.PasswordHelper;
import com.example.shangchuanserve.mapper.UserMapper;
import com.example.shangchuanserve.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean insertUser(User user) {
        int i = 0;
        User userByUsername = getUserByUsername(user.getUserName());
        if(userByUsername == null){
            User user1 = PasswordHelper.encryptPassword(user);
            i = userMapper.insert(user1);
        }
        return i > 0 ? true : false;
    }

    @Override
    public Boolean updataUser(User user, String newpassword) {
        int i = 0;
        User userByUsername = getUserByUser(user);
        if(userByUsername != null){
            user.setPassWord(newpassword);
            PasswordHelper.encryptPassword(user);
            UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("userId",user.getUserId());
            i = userMapper.update(user, updateWrapper);
        }
        return i > 0 ? true : false;
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    @Override
//    @Cacheable(value="redisCache", key="'redis_userId_'+#username")
        public User getUserByUserId(String userId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", userId);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    @Override
//    @Cacheable(value="redisCache", key="'redis_name_'+#name")
    public User getUserByName(String name) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userName", name);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    @Override
//    @Cacheable(value="redisCache", key="'redis_UserIdPassword_'+#user.getUsername()+#user.getPassword()")
    public User getUserByUser(User user) {
        User userByUsername = getUserByUserId(user.getUserId());
        user.setSalt(userByUsername.getSalt());
        User user1 = PasswordHelper.encryptPassword(user);
        System.out.println("user1"+user1);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId",user1.getUserId()).eq("passWord",user1.getPassWord());
        User u = userMapper.selectOne(queryWrapper);
        System.out.println("u"+u);
        return u;
    }
}
