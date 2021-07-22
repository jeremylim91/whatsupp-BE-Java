package com.chatApp.demo.controller;

import com.chatApp.demo.SignInResponse;
import com.chatApp.demo.model.User;
import com.chatApp.demo.service.UserService;
import com.chatApp.demo.utils.HandleCookies;
import com.chatApp.demo.utils.Hasher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import netscape.javascript.JSObject;
import org.bson.json.JsonObject;
import org.springframework.asm.Handle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Key;
import java.sql.Array;
import java.util.*;

//Let Spring container know tha this class is a controller
@RestController
public class UserController {
//    private static final String loggedInUsername= "loggedInUsername";
//    private static final String loggedInUserId= "loggedInUserId";
//    private static final String loggedInHash= "loggedInHash";
    private  String loggedInHashCookie= null;
    private  String loggedInUserIdCookie= null;

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Bind the route to a URI path; optional: specify req type (post, put, etc)
    @GetMapping("/users/signOut")
    public ResponseEntity <String> signOut (HttpServletResponse res){
        //        Remove the username cookie
        System.out.println("about to delete username cookie");
        HandleCookies.deleteCookies(res, CookieNames.loggedInUsername);
        //        Remove the userId cookie
        HandleCookies.deleteCookies(res, CookieNames.loggedInUserId);
        //        Remove the loggedInHash cookie
        HandleCookies.deleteCookies(res, CookieNames.loggedInHash);

        //        Respond to client that everything is ok.
        return ResponseEntity.status(200).build();
    }

    @PostMapping("/users/signIn")
    public ResponseEntity <String> signIn (HttpServletResponse res, @RequestBody String userDetails) throws JsonProcessingException {
        System.out.println("Inside the user controller/signIn");
        UserDetails instance= new ObjectMapper().readValue(userDetails, UserDetails.class);
        String username= instance.getUsername();
        String password= instance.getPassword();
        //        @RequestBody String username, String password
//        String username= userDetails.getUsername();
//        String password= userDetails.getPassword();

//      Check if the user instance exists
        User userInstance= userService.findUserByUsernameAndPassword(username,password);
//      If userInstance is null, the login credentials are not invalid
        if (userInstance==null){
//            Inform FE accordingly
            return ResponseEntity.status(403).body("Sorry, your credentials could not be authenticated. Please try again.");
        }
//      Else the credentials are valid
//      Set cookies in FE
        System.out.println("about to set cookies");
        HandleCookies.addCookies(res,CookieNames.loggedInUsername, userInstance.getUsername());
        HandleCookies.addCookies(res, CookieNames.loggedInUserId, userInstance.getId().toString());
        String hashedId= Hasher.createHashedString(userInstance.getId().toString());
        HandleCookies.addCookies(res, CookieNames.loggedInHash, hashedId);


//      Create the response object
        SignInResponse signInResponse = new SignInResponse(true, userInstance);
        ObjectMapper objectMapper= new ObjectMapper();
        return ResponseEntity.status(200).body(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(signInResponse));
    }



//     Bind the getAllUsers method to a URI path; optional: specify the request type (e.g. get, put)
    @RequestMapping("/users/allUsers")
//    Specify that this is a REST service that does not render a view (passes the info to FE instead)
    @ResponseBody
        public java.util.List getAllUsers (Model model){
//        find all entries in the repo
        List allUsers= userService.findAllUsers();
        System.out.println("All users is:");
        System.out.println(allUsers);
        ObjectMapper objectMapper= new ObjectMapper();
        return allUsers;
    }

@PostMapping("/users/setUserCredentialsInStore")
    public ResponseEntity <String> setUserCredentialsInStore(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException {
//    System.out.println(req.getAttribute());

    ObjectMapper objectMapper= new ObjectMapper();
    String responseObject= objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(req.getAttribute("requestObject"));
    return ResponseEntity.status(200).body(responseObject);
    }

}


