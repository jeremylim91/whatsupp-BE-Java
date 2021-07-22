package com.chatApp.demo;

import com.chatApp.demo.controller.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Autowired AuthInterceptor authInterceptor;

    private static final String[] allRoutesToAuthenticate= new String []{
            "/users/signOut",
            "users/allUsers",
            "/users/setUserCredentialsInStore",
            "/messages/**",
            "/rooms/**"
    };
    private List<String> routesToAuthenticate = new ArrayList<String>();
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns(allRoutesToAuthenticate);
    }


    @Override
    public void addCorsMappings (CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080")
                .allowCredentials(true).maxAge(3600);
    }
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**");
//            }
//        };
//    }
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("*");
//    }
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//        return source;
//    }




}
