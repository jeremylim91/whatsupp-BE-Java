package com.chatApp.demo.service;

import com.chatApp.demo.model.User;
import com.chatApp.demo.repository.UserRepository;
import com.chatApp.demo.utils.Hasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
//==============Constructor=======================
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
//=================================================
//==============Service methods====================
    public User getUserInstanceWithUsernameAndPassword(String username, String password){
        System.out.println("========================");
        System.out.println("username and password in service layer:");
        System.out.println(username);
        System.out.println(password);
        System.out.println("==========================");
//        Get a hashed version of the password the user used to sign in
        String hashedPassword= Hasher.createHashedString(password);
        System.out.println("hashed Password is:");
        System.out.println(hashedPassword);
        Optional <User> optionalEntity = userRepository.findById("60efcd7bd8e8926e3c6fff8f");
        User userInstance= optionalEntity.get();
//        Get a user instance
//        List dbResults= userRepository.findAll();
//        System.out.println(dbResults);
//     User userInstance= userRepository.findUserInstanceByUsernameAndPassword(username,hashedPassword);
        System.out.println("userInstance is:");
//        System.out.println(userInstance);
//        User userInstance= null;
//    If user instance does not exist, return null
     if (userInstance==null)
     {
    return null;
    }
//     Inputted credentials point toward a legitimate user. Return true
    return userInstance;
    }
}