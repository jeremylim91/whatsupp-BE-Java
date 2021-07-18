package com.chatApp.demo.seeders;

import com.chatApp.demo.model.User;
import com.chatApp.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UserSeed implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run (String...args) throws Exception{
        //        Empty DB of any existing data related to users
        this.userRepository.deleteAll();

        //       Create users based on User model
        User user1= new User("Jeremy", "jeremylim_91", "password1");
        User user2= new User("Joshua", "joshualim_91", "password1");

        //        Put all users into a list
        List<User> allUsers= Arrays.asList(user1,user2);

        //        Bulk insert the list of users
        this.userRepository.insert(allUsers);
    }
}
