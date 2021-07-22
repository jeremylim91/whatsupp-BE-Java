package com.chatApp.demo.seeders;

import com.chatApp.demo.model.Message;
import com.chatApp.demo.model.Room;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Index implements CommandLineRunner {

    @Autowired UserSeed userSeed;
    @Autowired MessageSeed messageSeed;
    @Autowired RoomSeed roomSeed;

    @Override
    public void run(String... args) throws Exception {
//        If databse has been seeded, abort this seeding process
        if (userSeed.count() >0|| roomSeed.count()>0|| messageSeed.count()>0){
            return;
        }

//Seed the user data
        userSeed.seedUserData();
//      Seed the Room data without associated messages
        List<Room> allRooms= roomSeed.seedRoomData();
//      Create the messsages
        List<Message> allMessages= messageSeed.seedMessageData(allRooms);
//       Go thru all messages and assign them as an associated message to the respective rooms
        roomSeed.assignAssociatedMessages(allMessages);

    }
}