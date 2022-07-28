package com.example.shangchuanserve.interceptor;


import com.example.shangchuanserve.bean.User;
import com.example.shangchuanserve.common.util.JwtUtil;
import com.example.shangchuanserve.common.util.MyThreadLocalUtil;
import com.example.shangchuanserve.config.annation.PassToken;
import com.example.shangchuanserve.config.annation.UserLoginToken;

import com.example.shangchuanserve.exception.TokenOutTimeException;
import com.example.shangchuanserve.exception.UserNotLoginException;
import com.example.shangchuanserve.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle (HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse, Object object) throws Exception {

        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            System.out.println(userLoginToken);
            if (userLoginToken.required()) {
                // 执行认证

                if (StringUtils.isEmpty(token)) {
                    throw new UserNotLoginException("无token，请重新登录");
                }
                // 获取 token 中的 user id
                if(JwtUtil.checkToken(token)){
                    String userId = JwtUtil.getMemberIdByJwtToken(token);
                    User user = userService.getUserByUserId(userId);
                    if(user != null ){
                        MyThreadLocalUtil.put(user);
                        return true;
                    }else{
                        throw new TokenOutTimeException("认证不正确");
                    }

                }else{
                    throw new TokenOutTimeException("登录超时，请重新登录");
                }

            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
