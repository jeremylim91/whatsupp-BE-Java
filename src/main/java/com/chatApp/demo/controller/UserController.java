package com.chatApp.demo.controller;

import com.chatApp.demo.model.User;
import com.chatApp.demo.model.service.UserService;
import com.chatApp.demo.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import java.lang.reflect.Array;
import java.util.Objects;

//Let Spring container know tha this class is a controller
@RestController
public class UserController {
//    private UserRepository repository;
//
//
////   Constructor
//    public UserController(UserRepository repository) {
//        this.repository = repository;
//    }
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Bind the route to a URI path; optional: specify req type (post, put, etc)
    @GetMapping("users/signOut")
//    Specify that this is a REST service that does not render a view (passes the info to FE instead)
    @ResponseBody
    public void signOut (Model model){

        return;
    }

    @PostMapping("users/signIn")
    @ResponseBody
    public ResponseEntity signIn (@RequestBody String username, String password){
        userService.signIn(username,password);
        return ResponseEntity.status(200).build();
    }

    @PostMapping("users/setUserCredentialsInStore")
    @ResponseBody
    public void setUserCredentialsInStore (){

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
