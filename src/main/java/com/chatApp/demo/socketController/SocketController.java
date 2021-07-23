package com.chatApp.demo.socketController;

import com.chatApp.demo.model.Message;
import com.chatApp.demo.service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {
    @Autowired
    MessageService messageService;

//    @MessageMapping("/testingRoute")
//    @SendTo("/testingRoute")
//    public Message addMsgToDb (@Payload String incomingMsg) throws JsonProcessingException {
//        System.out.println("this is the chattttt");
//        System.out.println(incomingMsg);
//        Message newMsg= messageService.create(incomingMsg);
//        if (newMsg.equals(null)) return null;
//
//        return newMsg;
//    }

    @MessageMapping("/addMsgToDb")
    @SendTo("/wsFromServer/testingRoute")
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

}
