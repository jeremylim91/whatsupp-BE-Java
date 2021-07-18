package com.chatApp.demo.controller.interceptor;

import com.chatApp.demo.model.User;
import com.chatApp.demo.repository.UserRepository;
import com.chatApp.demo.utils.Hasher;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AuthLogic {
    @Autowired
    UserRepository userRepository;


    private static final String loggedInUsername= "loggedInUsername";
    private static final String loggedInUserId= "loggedInUserId";
    private static final String loggedInHash= "loggedInHash";
    private  String loggedInHashCookie= null;
    private  String loggedInUserIdCookie= null;


    public Boolean checkUserAuthentication(HttpServletRequest req, HttpServletResponse res){
        final String isUserLoggedIn= "isUserLoggedIn";
        req.setAttribute(isUserLoggedIn,new RequestObject(false));
        System.out.println("About to see the request object");
        System.out.println( req.getAttribute(isUserLoggedIn));

        Cookie[] allCookies = req.getCookies();
//       If user has no cookies, he's not authenticated. Return false
        if(allCookies==null)return false;

//If user has cookiess, extract save them into variables we can reference
        for (Cookie cookie:allCookies){
            if(cookie.getName().equals(loggedInHash)){
                this.loggedInHashCookie= cookie.getValue();
                System.out.println(this.loggedInHashCookie);
            }
            if (cookie.getName().equals(loggedInUserId)){
                this.loggedInUserIdCookie=cookie.getValue();
                System.out.println(this.loggedInUserIdCookie);
            }
        }
//        If user has cookies called loggedInUserId and loggedInHash,dig deeper to see if the values match what we have in the db
        if(loggedInHashCookie!=null && loggedInUserIdCookie!=null){
//            Lvl 1 check: Does loggedInUserIdCookie match loggedInHashCookie after running it thru my hashhing function (which has a salt)?
            String hashedLoggedInUserIdCookie= Hasher.createHashedString(loggedInUserIdCookie);
//          If loggedInUserIdCookie doesn't match loggedInHashCookie after hashing, authentication fails
            if (hashedLoggedInUserIdCookie!= loggedInHashCookie){
                return false;
            }
//           Else proceed to level 2 authentication: Check if the userId returns a user instance
            Optional userInstance= userRepository.findUserById(loggedInUserIdCookie);

//          If the user instance exists, user passes lvl 2 auth. Return true
            if (!userInstance.isEmpty()) return true;

//            Else the user fails level 2 auth.
        }
        return false;
    }

}

class RequestObject{
    private boolean isUserLoggedIn;

    public RequestObject(){
        this(false);
    }
    public RequestObject(boolean isUserLoggedIn) {
        this.isUserLoggedIn = isUserLoggedIn;
    }
}


