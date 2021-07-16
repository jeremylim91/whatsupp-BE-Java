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

//       Search db to see if user-provided username and password are legit
        Optional userInstance= userRepository.findUserByUsernameAndPassword(username,
                hashedPassword);

//        If no userInstance found, then user cannot be authenticated
                if (!userInstance.isPresent()){
                    return null;
                }
//     Else if user instance is found, send back the user Instance
    return (User) userInstance.get();
    }



}


//============================================
//============================================
// const signOut = async (req, res) => {
//         // remove all the cookies relevant to user auth
//         res.clearCookie('loggedInHash', {secure: true, sameSite: 'None'});
//         res.clearCookie('loggedInUserId', {secure: true, sameSite: 'None'});
//         res.clearCookie('loggedInUsername', {secure: true, sameSite: 'None'});
//         res.status(200).send('cookies cleared');
//         };
//         return {
//         signIn,
//         setUserCredentialsInStore,
//         signOut,
//         };