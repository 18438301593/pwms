package com.weblogb.pwms.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
* @author: Jiajiajia
* @Date: 2021/10/28
* @Description: 登录拦截配置
*/
@Configuration
public class LoginConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册LoginInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());
        registration.addPathPatterns("/**");
        registration.excludePathPatterns(
                "/user/login" , "/user/dologin", "/user/register","/**/*.js","/**/*.ico", "/**/*.css", "/**/*.woff", "/**/*.ttf"
        );
    }
}