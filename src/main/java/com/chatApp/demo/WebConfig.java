package com.chatApp.demo;

import com.chatApp.demo.controller.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final String[] allRoutesToAuthenticate= new String []{"/users/signOut","/users/allUsers",
            "/messages/**", "/rooms/**"
    };
    private List<String> routesToAuthenticate = new ArrayList<String>();


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns(allRoutesToAuthenticate);
    }
}
