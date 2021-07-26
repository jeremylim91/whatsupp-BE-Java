package com.chatApp.demo.controller.interceptor;

import com.chatApp.demo.utils.HandleCookies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    private static final String loggedInUsername= "loggedInUsername";
    private static final String loggedInUserId= "loggedInUserId";
    private static final String loggedInHash= "loggedInHash";

    @Autowired
    AuthLogic authLogic;

//    public AuthInterceptor(AuthLogic authLogic) {
//        this.authLogic = authLogic;
//    }
//
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception{
        System.out.println("==========================================");
        System.out.println("Inside the Auth interceptor");
        System.out.println("==========================================");


        boolean isAuthenticated= authLogic.checkUserAuthentication(req, res);
//    If client proves to be inauthentic, clear all client's cookies
        if (!isAuthenticated){
            System.out.println("Is not authenticated");
//          Clear all the client's cookies
            HandleCookies.deleteCookies(res, loggedInHash);
            HandleCookies.deleteCookies(res, loggedInUserId);
            HandleCookies.deleteCookies(res, loggedInUsername);

//          Create msg to client
            res.setStatus(500);
            ResponseEntity.status(500).body("unauthenticated");

        }else {
            System.out.println("Interceptor status: User is authentic");

        }
        return isAuthenticated;
};
    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler, ModelAndView modelAndView) throws Exception {
        // your code

    }
    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler, Exception ex) {
        // your code
    }
}
