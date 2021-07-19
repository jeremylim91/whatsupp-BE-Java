package com.chatApp.demo;

import com.chatApp.demo.controller.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration public class WebConfig implements WebMvcConfigurer {

    @Autowired AuthInterceptor authInterceptor;

    private static final String[] allRoutesToAuthenticate= new String []{"/users/signOut","/users" +
            "/allUsers","/users/setUserCredentialsInStore",
            "/messages/**", "/rooms/**"
    };
    private List<String> routesToAuthenticate = new ArrayList<String>();


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns(allRoutesToAuthenticate);
    }
}
