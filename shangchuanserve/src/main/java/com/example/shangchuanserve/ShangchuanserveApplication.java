package com.example.shangchuanserve;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@MapperScan("com.example.shangchuanserve.mapper")
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
//@EnableCaching
public class ShangchuanserveApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShangchuanserveApplication.class, args);
    }

}
