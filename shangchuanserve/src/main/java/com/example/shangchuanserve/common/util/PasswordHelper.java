package com.example.shangchuanserve.common.util;

import com.example.shangchuanserve.bean.User;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 密码加密
 */

public class PasswordHelper {
    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    private static String algorithmName = "md5";
    private static final int hashIterations = 2;

    public static User encryptPassword(User user) {

        if(user.getSalt() == null) {
            user.setSalt(randomNumberGenerator.nextBytes().toHex());
        }

        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassWord(),
                ByteSource.Util.bytes(user.getSalt()),
                hashIterations).toHex();

        user.setPassWord(newPassword);
        return user;
    }

    public RandomNumberGenerator getrandomNumberGenerator(){
        return randomNumberGenerator;
    }
}
