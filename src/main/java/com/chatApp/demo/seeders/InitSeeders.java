//package com.chatApp.demo.seeders;
//
//import com.chatApp.demo.model.Message;
//import com.chatApp.demo.model.Room;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@SpringBootApplication
//public class InitSeeders {
//
//    @Autowired
//    UserSeed userSeed;
//    @Autowired MessageSeed messageSeed;
//    @Autowired RoomSeed roomSeed;
//
//    public static void main (String[] args) throws Exception{
////    public void run(String... args) throws Exception {
////Seed the user data
//        SpringApplication.run(Index.class, args);
//        System.out.println("========================");
//        System.out.println("Completed seeding the DB");
//        System.out.println("========================");
//
////        userSeed.seedUserData();
//////      Seed the Room data without associated messages
////        List<Room> allRooms= roomSeed.seedRoomData();
//////      Create the messsages
////        List<Message> allMessages= messageSeed.seedMessageData(allRooms);
//////       Go thru all messages and assign them as an associated message to the respective rooms
////        roomSeed.assignAssociatedMessages(allMessages);
//
//    }
//}