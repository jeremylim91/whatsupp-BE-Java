package com.chatApp.demo.controller.interceptor;

import com.chatApp.demo.utils.HandleCookies;
import org.springframework.asm.Handle;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.CookieHandler;

public class AuthInterceptor implements HandlerInterceptor {
    private static final String loggedInUsername= "loggedInUsername";
    private static final String loggedInUserId= "loggedInUserId";
    private static final String loggedInHash= "loggedInHash";

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception{
        System.out.println("==========================================");
        System.out.println("Inside the Auth interceptor");
        System.out.println("==========================================");


        boolean isAuthenticated= new AuthLogic().checkUserAuthentication(req, res);
//    If client proves to be inauthentic, clear all client's cookies
        if (!isAuthenticated){
            System.out.println("not authenticated");
            HandleCookies.deleteCookies(res, loggedInHash);
            HandleCookies.deleteCookies(res, loggedInUserId);
            HandleCookies.deleteCookies(res, loggedInUsername);



//            new HandleCookies(loggedInHash, res).deleteCookies();
////            Remove the username cookie
//            Cookie removeUsername= new Cookie(loggedInUsername,null );
//            removeUsername.setMaxAge(0);
////            Indicates to the browswer tt the cookie shld only be sent using a secure protocol (e.g. HTTPS, SSL)
//            removeUsername.setSecure(true);
////          Using the HttpOnly flag when generating a cookie helps mitigate the risk of client side script accessing the protected cookie (if the browser supports it)
//            removeUsername.setHttpOnly(true);
//            removeUsername.setPath("/");
//            res.addCookie(removeUsername);
//
////        Remove the userId cookie
//            Cookie removeUserId= new Cookie(loggedInUserId,null);
//            removeUserId.setMaxAge(0);
////          Indicates to the browser tt the cookie shld only be sent using a secure protocol (e.g. HTTPS, SSL)
//            removeUserId.setSecure(true);
////            . Using the HttpOnly flag when generating a cookie helps mitigate the risk of client side script accessing the protected cookie (if the browser supports it)
//            removeUserId.setHttpOnly(true);
//            removeUserId.setPath("/");
//            res.addCookie(removeUserId);
//
////        Remove the loggedInHash cookie
//            Cookie removeHash= new Cookie(loggedInHash,null );
//            removeHash.setMaxAge(0);
////            Indicates to the browser tt the cookie shld only be sent using a secure protocol (e.g. HTTPS, SSL)
//            removeHash.setSecure(true);
////          Using the HttpOnly flag when generating a cookie helps mitigate the risk of client side script accessing the protected cookie (if the browser supports it)
//            removeHash.setHttpOnly(true);
//            removeHash.setPath("/");
//            res.addCookie(removeHash);
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
