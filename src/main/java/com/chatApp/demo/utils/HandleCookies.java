package com.chatApp.demo.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class HandleCookies {
//    private String cookieKey;
//    private String cookieVal;
//    private HttpServletResponse res;
//
//
//    public HandleCookies(String cookieKey, HttpServletResponse res) {
//        this.cookieKey = cookieKey;
//        this.res = res;
//    }
//
//    public HandleCookies(String cookieKey, String cookieVal, HttpServletResponse res) {
//        this.cookieKey = cookieKey;
//        this.cookieVal = cookieVal;
//        this.res = res;
//    }

    public  static void deleteCookies (HttpServletResponse res, String cookieKey){
        Cookie cookieToRemove = new Cookie(cookieKey,null );
//       Indicates that the cookie should expire immediately
        cookieToRemove.setMaxAge(0);
//            Indicates to the browswer tt the cookie shld only be sent using a secure protocol (e.g. HTTPS, SSL)
//        cookieToRemove.setSecure(true);
//          Using the HttpOnly flag when generating a cookie helps mitigate the risk of client side script accessing the protected cookie (if the browser supports it)
        cookieToRemove.setHttpOnly(true);
        cookieToRemove.setPath("/");
        res.addCookie(cookieToRemove);
    }
    public static void addCookies (HttpServletResponse res, String cookieKey, String cookieValue){
//        Define the cookie key and val
        Cookie cookieToAdd= new Cookie(cookieKey, cookieValue);
//        cookieToAdd.setSecure(true);
        cookieToAdd.setHttpOnly(true);
        cookieToAdd.setPath("/");
//        Set the cookie in the browser
        res.addCookie(cookieToAdd);
    }

}
