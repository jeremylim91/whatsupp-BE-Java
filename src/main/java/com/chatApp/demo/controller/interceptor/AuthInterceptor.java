package com.chatApp.demo.controller.interceptor;

import com.chatApp.demo.utils.HandleCookies;
import org.springframework.beans.factory.annotation.Autowired;
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
            HandleCookies.deleteCookies(res, loggedInHash);
            HandleCookies.deleteCookies(res, loggedInUserId);
            HandleCookies.deleteCookies(res, loggedInUsername);

        }else {
            System.out.println("Is Logged in");
            System.out.println(req.getAttribute("isLoggedIn"));;
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
