package com.chatApp.demo.service;

import com.chatApp.demo.controller.RoomSummaryMap;
import com.chatApp.demo.model.Message;
import com.chatApp.demo.model.Room;
import com.chatApp.demo.repository.RoomRepository;
import com.chatApp.demo.utils.HandleJson;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    MessageService messageService;

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
        System.out.println("roomId in roomService:");
        System.out.println(new ObjectId(roomId));
        System.out.println(roomRepository.findAll().toString());
        Optional<Room> roomOptional= roomRepository.findById(new ObjectId(roomId));
        System.out.println("RoomOptional to string:");
        System.out.println(roomOptional.toString());
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


    public ArrayList findAllRoomsWSummary(){
//       Retrieve all the rooms
        List <Room> allRooms= roomRepository.findAll();
        System.out.println("All rooms is:");
        System.out.println(allRooms.toString());
//Set an array list that contains all the data to return to the client
        ArrayList<RoomSummaryMap> dataToReturnToClient = new ArrayList<>();

        for (Room room : allRooms) {
//            If there is no associated msg, assign an empty array to the lastMsg.
            if(room.getAssociated_messages().size()<1){
                System.out.println("size is less than 1");
                RoomSummaryMap mapObject= new RoomSummaryMap(room.getId(),room.getName(),
                        room.getImage(),
                        room.getCreatedBy(),
                        room.getAssociated_messages(), "");
                System.out.println("map object is:");
                System.out.println(mapObject);

                dataToReturnToClient.add(mapObject);

                continue;
            }
            System.out.println("Size is more than 1");
//            Else, add the last msg
            String lasMsgObjId=
                    room.getAssociated_messages().get(room.getAssociated_messages().size()-1).toString();
//Query the db for this message
            Message messageInstance= messageService.findbyId(lasMsgObjId);
            dataToReturnToClient.add(new RoomSummaryMap(room.getId(),room.getName(),
                    room.getImage(),
                    room.getCreatedBy(),
                    room.getAssociated_messages(), messageInstance.getMessage()));
        }

        System.out.println("data to return to client inside room service");
        System.out.println(dataToReturnToClient);
    return dataToReturnToClient;
    }
}
