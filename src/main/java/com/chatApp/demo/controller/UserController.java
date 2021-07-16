package com.chatApp.demo.controller;

import com.chatApp.demo.SignInResponse;
import com.chatApp.demo.model.User;
import com.chatApp.demo.service.UserService;
import com.chatApp.demo.utils.Hasher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.util.List;

//Let Spring container know tha this class is a controller
@RestController
public class UserController {
    private static final String loggedInUsername= "loggedInUsername";
    private static final String loggedInUserId= "loggedInUserId";
    private static final String loggedInHash= "loggedInHash";
    private String loggedInHashCookie;
    private String loggedInUserIdCookie;



    //    private UserRepository repository;
//
//
////   Constructor
//    public UserController(UserRepository repository) {
//        this.repository = repository;
//    }
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Bind the route to a URI path; optional: specify req type (post, put, etc)
    @GetMapping("users/signOut")
    public ResponseEntity <String> signOut (HttpServletResponse res){
//        Remove the username cookie
        Cookie removeUsername= new Cookie(loggedInUsername,"" );
        removeUsername.setMaxAge(0);
        res.addCookie(removeUsername);

//        Remove the userId cookie
        Cookie removeUserId= new Cookie(loggedInUserId,"");
        removeUserId.setMaxAge(0);
        res.addCookie(removeUserId);


//        Remove the loggedInHash cookie
        Cookie removeHash= new Cookie(loggedInHash,"" );
        removeHash.setMaxAge(0);
        res.addCookie(removeHash);
//        Respond to client that everything is ok.
        return ResponseEntity.status(200).build();
    }

    @PostMapping("users/signIn")
    public ResponseEntity <String> signIn (HttpServletResponse res, @RequestBody UserDetails userDetails) throws JsonProcessingException {
//        @RequestBody String username, String password
        String username= userDetails.getUsername();
        String password= userDetails.getPassword();

//      Check if the user instance exists
        User userInstance= userService.findUserByUsernameAndPassword(username,password);
//      If userInstance is null, the login credentials are not invalid
        if (userInstance==null){
//            Inform FE accordingly
            return ResponseEntity.status(403).body("Sorry, your credentials could not be authenticated. Please try again.");
        }

        String hashedId= Hasher.createHashedString(userInstance.getId().toString());
        System.out.println("hashedId is:");
        System.out.println(hashedId);
//      Else the credentials are valid
//      Set cookies in FE
        res.addCookie(new Cookie(loggedInUsername, userInstance.getUsername()));
        res.addCookie(new Cookie(loggedInUserId, userInstance.getId().toString()));
        res.addCookie(new Cookie(loggedInHash, hashedId));

//      send the response

        SignInResponse signInResponse = new SignInResponse(true, userInstance);

        ObjectMapper objectMapper= new ObjectMapper();


//        return ResponseEntity.status(200).body({auth: true, user: userInstance});
        return ResponseEntity.status(200).body(objectMapper.writeValueAsString(signInResponse));
    }

    @PostMapping("users/setUserCredentialsInStore")
    @ResponseBody
    public void setUserCredentialsInStore() {

    }


    @GetMapping("/users/checkUserAuthentication")
    @ResponseBody
    public String checkUserAuthentication(HttpServletRequest req, HttpServletResponse res){
        final String isUserLoggedIn= "isUserLoggedIn";
        req.setAttribute(isUserLoggedIn,new RequestObject(false));

        System.out.println( req.getAttribute(isUserLoggedIn));

        Cookie[] allCookies= req.getCookies();

        for (Cookie cookie:allCookies){
            if(cookie.getName().equals(loggedInHash)){
                this.loggedInHashCookie= cookie.getValue();
            }
            if (cookie.getName().equals(loggedInUserId)){
                this.loggedInUserIdCookie=cookie.getValue();
            }
        }
//        System.out.println(allCookies[0].getValue());
        System.out.println(loggedInHashCookie);
        System.out.println(loggedInUserIdCookie);

    return "Hiiii";
    }
    // Bind the getAllUsers method to a URI path; optional: specify the request type (e.g. get, put)
//    @RequestMapping("/user/allUsers")
////    Specify that this is a REST service that does not render a view (passes the info to FE instead)
//    @ResponseBody
//        public java.util.List getAllUsers (Model model){
////        find all entries in the repo
//        List allUsers= repository.findAll();
//        return allUsers;
//    }
//
//    // Bind the getAllUsers method to a URI path; optional: specify the request type (e.g. get, put)
//    @RequestMapping("/user/addUser")
////    Specify that this is a REST service that does not render a view (passes the info to FE instead)
//    @ResponseBody
//    public String addUser (@RequestParam String username){
//
//        //    Add a user to the repo
//        User newUser= new User(username);
//        repository.save(newUser);
//        String myOutputValue= newUser.getId().toString()+ "   xxxxxxxxxxxxxx          ";
//
//      return myOutputValue;
//    }
}
class UserDetails {
    private String username;
    private String password;

    public UserDetails(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

@Data class RequestObject{
    private boolean isUserLoggedIn;

    public RequestObject(){
        this(false);
    }
    public RequestObject(boolean isUserLoggedIn) {
        this.isUserLoggedIn = isUserLoggedIn;
    }
}