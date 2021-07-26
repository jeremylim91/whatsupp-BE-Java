package com.chatApp.demo.service;

import com.chatApp.demo.model.Message;
import com.chatApp.demo.model.Room;
import com.chatApp.demo.repository.MessageRepository;
import com.chatApp.demo.repository.RoomRepository;
import com.chatApp.demo.utils.HandleJson;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.tools.jconsole.JConsoleContext;
import org.bson.types.ObjectId;
import org.springframework.asm.Handle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;
//    @Autowired
//    RoomRepository roomRepository;
    @Autowired
    RoomService roomService;


    public Message findbyId(String messageId){
        Optional messageInstance =messageRepository.findById(messageId);
        if (!messageInstance.isPresent()) return null;
        return (Message) messageInstance.get();
    }

    public List getAllMsgsInRoom (String roomId){
//        Get hold of the room instance
        Room roomInstance= roomService.findById(roomId);
        if (roomInstance==null) return null;
//        Optional <Room> roomOptional= roomRepository.findById( roomId);
//        System.out.println(roomOptional.toString());
//        if (roomOptional.isEmpty()) {
//            System.out.println("room optional is empty");
////            List emptyList= new ArrayList();
////            System.out.println("Empty list is:");
////            System.out.println(emptyList.toString());
//            return null;
//        };
//        Room roomInstance= roomOptional.get();
        Iterable <Message> iterableMessages= messageRepository.findAllById(roomInstance.getAssociated_messages());
        List allAssociatedMsgs= (List) iterableMessages;
        if (allAssociatedMsgs.size()<1){
            System.out.println("No associated messages");
            return null;
        }
//       Else there are associated messages; return them
        return allAssociatedMsgs;
    }

    public Message create(String incomingMsg) throws JsonProcessingException {
        String roomId= HandleJson.deserializeToString(incomingMsg, "roomId");
        String msgContent= HandleJson.deserializeToString(incomingMsg, "msgContent");
        String username= HandleJson.deserializeToString(incomingMsg, "username");
        Message newMsg= new Message(new ObjectId().toString(),msgContent, username, LocalDateTime.now(), roomId);
        messageRepository.insert(newMsg);
//        Find the current room
        Room currRoom= roomService.findById(roomId);
        if (currRoom.equals(null)) return null;
        System.out.println("newMsg is:");
        System.out.println(newMsg);
        currRoom.getAssociated_messages().add(newMsg.getId());
        roomService.save(currRoom);
        System.out.println("finished updating currRoom to db");
        return newMsg;
    }

    public Map getAllMsgsByRoom() {
//      Get a list of all the rooms
        List <Room> allRooms= roomService.findAll();
        System.out.println(" allRooms Is;");
        System.out.println(allRooms);
        Map <String, List> responseObj= new HashMap<String, List>() {
        };
        allRooms.forEach((room)->{
            String roomId= room.getId();

            Iterable <Message> iterableMsgs= messageRepository.findAllById(room.getAssociated_messages());
            List <Message> allAssociatedMsgs= (List) iterableMsgs;
            System.out.println("allAssociatedMsgs");
            System.out.println(allAssociatedMsgs);
            if (allAssociatedMsgs.size()<1)return;
            System.out.println("roomId is:");
            System.out.println(roomId);
            responseObj.put(roomId, allAssociatedMsgs);

        });
        System.out.println("responseObj is: ");
        System.out.println(responseObj);
        return responseObj;
    }
}
