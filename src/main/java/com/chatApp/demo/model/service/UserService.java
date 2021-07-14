package com.chatApp.demo.model.service;

import com.chatApp.demo.model.User;
import com.chatApp.demo.repository.UserRepository;
import com.chatApp.demo.utils.Hasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Optional<User> signIn(String username, String password){
//        Get a hashed version of the password the user used to sign in
        String hashedPassword= new Hasher().getHashedString(password).toString();
//        Get a user instance
     User userInstance= userRepository.findUserInstanceByUsernameAndPassword(username,hashedPassword);
//    If user instance does not exist, return null
     if (userInstance==null){
    return null;
    }
//     Else user instance exists.
//
    return null;

    }

}


