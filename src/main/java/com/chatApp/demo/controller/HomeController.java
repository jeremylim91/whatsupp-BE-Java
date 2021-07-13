package com.chatApp.demo.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HomeController {
    @RequestMapping("home")
    @ResponseBody
    public String home (@RequestParam ("name") String myName ){
        System.out.println("This is the home end point");
//        String name= req.getParameter("name");

        return "hellooooooo " + myName;
    }
}
