package com.chatApp.demo.socketController;

import com.chatApp.demo.model.Message;
import com.chatApp.demo.service.MessageService;
import com.chatApp.demo.service.RoomService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
//@Component
public class SocketController {

    @Autowired
    MessageService messageService;

    @Autowired
    RoomService roomService;

    @MessageMapping("/addMsgToDb")
    @SendTo("/wsFromServer/addMsgToDb")
    public String addMsgToDb (@Payload String incomingMsg) throws JsonProcessingException {
        System.out.println("================================");
        System.out.println("this is the chattttt");
        System.out.println("================================");

        System.out.println(incomingMsg);
        Message newMsg= messageService.create(incomingMsg);
        if (newMsg.equals(null)) return null;
        ObjectMapper objectMapper= new ObjectMapper();
        String jsonNewMsg= objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(newMsg);
        return jsonNewMsg;
    }
    @MessageMapping("/createRoom")
    @SendTo("/wsFromServer/createRoom")
    public String createRoom (@Payload String roomDetails) throws JsonProcessingException {
        System.out.println("=======================");
        System.out.println("Inside createRoom in BE");
        System.out.println("=======================");

        System.out.println(roomDetails);
        roomService.create(roomDetails);
//        if (newMsg.equals(null)) return null;
//        ObjectMapper objectMapper= new ObjectMapper();
//        String jsonNewMsg= objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(newMsg);
//        return jsonNewMsg;
        System.out.println("about to return null");
        return "null";

    }

}
