package com.example.shangchuanserve.config;


import com.example.shangchuanserve.interceptor.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImageMvcConfig implements WebMvcConfigurer {

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//

//        registry.addResourceHandler("/images/**").addResourceLocations("file:D://project//picture/");

    }


    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(authenticationInterceptor);
        registration.addPathPatterns("/**");
        registration.excludePathPatterns("/static/**");
    }


}
