package com.chatApp.demo.seeders;

import com.chatApp.demo.model.Message;
import com.chatApp.demo.model.Room;
import com.chatApp.demo.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class RoomSeed{
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    AvatarCreator avatarCreator;
    @Autowired
    SeederStrings seederStrings;

    private ArrayList <Message> devHangoutAssociatedMsgs;
    private ArrayList <Message> cyclingBuddiesAssociatedMsgs;
    private ArrayList <Message> foodiesUnitAssociatedMsgs;
    private List <Room> allRooms= new ArrayList<Room>();



//
//    private final String devHangout= "Dev hangout";
//    private final String cyclingBuddies= "Cycling buddies";
//    private final String foodiesUnite = "Foodies unite!";


    //    public void run (String...args) throws Exception{

    public long count(){
        return roomRepository.count();
    }

    public List <Room> seedRoomData(){
        //        Empty DB of any existing data related to users
        roomRepository.deleteAll();
//        this.roomRepositoryy.deleteAll();

//        List <Room> allRooms= new ArrayList<Room>();
        //       Create rooms based on Room model; add them to the allRooms list
        allRooms.add(new Room(seederStrings.devHangout, avatarCreator.getGridyImg(seederStrings.devHangout), "jeremylim_91", new ArrayList()));
        allRooms.add(new Room(seederStrings.foodiesUnite, avatarCreator.getGridyImg(seederStrings.foodiesUnite), "jeremylim_91", new ArrayList()));
        allRooms.add(new Room(seederStrings.cyclingBuddies, avatarCreator.getGridyImg(seederStrings.cyclingBuddies), "josephpok_91", new ArrayList()));


        //        Bulk insert the list of users
        roomRepository.insert(allRooms);
        return allRooms;
    }

    public void assignAssociatedMessages(List allMessages){
        allRooms.forEach((room)->{


            allMessages.forEach((message)->{
                Message messageInstance= (Message) message;
                if (messageInstance.getRoom_id().equals(room.getId())) {
                    room.getAssociated_messages().add(messageInstance.getId());
                }
            });
            roomRepository.save(room);
        });
    }
}
