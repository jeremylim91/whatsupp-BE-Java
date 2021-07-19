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
    public User findUserByUsernameAndPassword(String username, String password){
//        Get a hashed version of the password the user used to sign in
        String hashedPassword= Hasher.createHashedString(password);
//        Optional <User> optionalEntity = userRepository.findById("60efcd7bd8e8926e3c6fff8f");
//        Optional <User> optionalEntity= userRepository.findUserByUsernameAndPassword(username, hashedPassword);
        System.out.println("right before db query");
//       Search db to see if user-provided username and password are legit
        Optional userInstance= userRepository.findUserByUsernameAndPassword(username,
                hashedPassword);
//      Return the user instance if present (cast it first), else null
        return (User) userInstance.orElse(null);
    }

    public List<User> findAllUsers(){
        List<User> allUsers= userRepository.findAll();

        return allUsers;

    }

}

