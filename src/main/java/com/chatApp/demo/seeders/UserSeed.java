package com.chatApp.demo.seeders;

import com.chatApp.demo.model.User;
import com.chatApp.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UserSeed{
    @Autowired
    private UserRepository userRepository;

    public long count(){
        return userRepository.count();
    }

//    public void run (String...args) throws Exception{
    public void seedUserData(){
        //  Check if there is alr data; if so, abort the seeding process
//        this.userRepository.deleteAll();

        //       Create users based on User model
        User user1= new User("Jeremy", "jeremylim_91", "05AA9E7784DF47D71B7C2374FD86DBDF0E07FC80BC1DDA0E30714D339E95FD5472C1D0E929E01E8A03F2EA77A929EEBE7C0BA0544761C9CB302036890FAEE9D5");
        User user2= new User("Joshua", "joshualim_91", "05AA9E7784DF47D71B7C2374FD86DBDF0E07FC80BC1DDA0E30714D339E95FD5472C1D0E929E01E8A03F2EA77A929EEBE7C0BA0544761C9CB302036890FAEE9D5");
        User user3= new User("Joseph", "josephpok_91", "05AA9E7784DF47D71B7C2374FD86DBDF0E07FC80BC1DDA0E30714D339E95FD5472C1D0E929E01E8A03F2EA77A929EEBE7C0BA0544761C9CB302036890FAEE9D5");

        //        Put all users into a list
        List<User> allUsers= Arrays.asList(user1,user2, user3);

        //        Bulk insert the list of users
        this.userRepository.insert(allUsers);
    }
}
