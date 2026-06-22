package com.database.homework.pangdonglaishop.config;


import com.database.homework.pangdonglaishop.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(loginInterceptor)
                .excludePathPatterns(
                        "/user/login",

                        // ✅ Knife4j / Swagger 全部放行
                        "/doc.html",
                        "/webjars/**",
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/v3/api-docs/**"
                );




    }
}
