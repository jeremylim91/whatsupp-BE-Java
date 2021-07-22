package com.chatApp.demo.controller.interceptor;

import com.chatApp.demo.controller.CookieNames;
import com.chatApp.demo.model.User;
import com.chatApp.demo.repository.UserRepository;
import com.chatApp.demo.service.UserService;
import com.chatApp.demo.utils.Hasher;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class AuthLogic {

    @Autowired
    UserService userService;

    private static final String requestObject= "requestObject";
    private  String loggedInHashCookie= null;
    private  String loggedInUserIdCookie= null;


    public Boolean checkUserAuthentication(HttpServletRequest req, HttpServletResponse res){
        final String isUserLoggedIn= "isUserLoggedIn";

        Cookie[] allCookies = req.getCookies();
        //If user has no cookies, he's not authenticated. Return false
        //Returning false indicates to Spring that we should not proceed to the next REST route
        if(allCookies==null)return false;

        //If user has cookies, extract save them into variables we can reference
        for (Cookie cookie:allCookies){
            if(cookie.getName().equals(CookieNames.loggedInHash)){
                this.loggedInHashCookie= cookie.getValue();
            }
            if (cookie.getName().equals(CookieNames.loggedInUserId)){
                this.loggedInUserIdCookie=cookie.getValue();
            }
        }
        //If user has cookies called loggedInUserId and loggedInHash,dig deeper to see if the values match what we have in the db
        if(loggedInHashCookie!=null && loggedInUserIdCookie!=null){
        //Level 1 authentication: Does loggedInUserIdCookie match loggedInHashCookie after
        // running it thru my hashhing function (which has a salt)?
            String hashedLoggedInUserIdCookie= Hasher.createHashedString(loggedInUserIdCookie);


            //If loggedInUserIdCookie doesn't match loggedInHashCookie after hashing, authentication fails
            if (!hashedLoggedInUserIdCookie.equals(loggedInHashCookie)){
                System.out.println("fail level 1 auth");
                return false;
            }
            //Else proceed to level 2 authentication: Check if the userId returns a user instance
            //Optional userInstance= .findUserById(loggedInUserIdCookie);
            User userInstance= userService.findUserById(loggedInUserIdCookie);

            //If the user instance exists, user passes lvl 2 auth. Return true
            if (userInstance!=null) {
                req.setAttribute(requestObject,new RequestObject( userInstance.getUsername(), loggedInUserIdCookie, loggedInHashCookie));
                System.out.println("About to see the request object");
                System.out.println( req.getAttribute(requestObject));
                return true;
            }

            //Else the user fails level 2 auth.
            System.out.println("Fail level 2 auth");
        }
        //If user instance does not exist, authentication fails. Return false to stop this api routing
        return false;
    }
}


