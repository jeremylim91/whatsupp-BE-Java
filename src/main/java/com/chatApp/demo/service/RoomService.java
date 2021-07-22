package com.chatApp.demo.service;

import com.chatApp.demo.model.Room;
import com.chatApp.demo.repository.RoomRepository;
import com.chatApp.demo.utils.HandleJson;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;

    public String findOneRoom(){
        System.out.println("Inside findOneRoom");
        List allRooms= roomRepository.findAll();
        if (allRooms.size()<1){
            System.out.println("No rooms found");
            return null;
        }
        Room roomInstance= (Room) allRooms.get(0);
        return roomInstance.getId().toString();
    }

    public Room findById(String roomId){
        Optional<Room> roomOptional= roomRepository.findById( roomId);
//        System.out.println(roomOptional.toString());
        if (roomOptional.isEmpty()) {
            System.out.println("room optional is empty");
//            List emptyList= new ArrayList();
//            System.out.println("Empty list is:");
//            System.out.println(emptyList.toString());
            return null;
        };
//        Return an instance of the room
        return roomOptional.get();
    }

    public void create(String roomDetails) throws JsonProcessingException {

//        ObjectMapper mapper = new ObjectMapper();
//        TypeReference<HashMap<String, String>> typeRef= new TypeReference<HashMap<String, String>>() {
//        };
//        Map<String, String> map= mapper.readValue(roomDetails, typeRef );
        String userId= HandleJson.deserializeToString(roomDetails, "userId");
        String roomName= HandleJson.deserializeToString(roomDetails, "roomName");
        Room newRoom= new Room(roomName, userId);
        roomRepository.insert(newRoom);
        System.out.println("Finished inserting room");


//        System.out.println("Map to string is:");
//        System.out.println(map.get("userId"));
//        roomRepository.insert(roomDetails);
    }

    public List<Room> findAll (){
        return roomRepository.findAll();
    }
}
