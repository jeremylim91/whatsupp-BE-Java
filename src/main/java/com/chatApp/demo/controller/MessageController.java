package com.chatApp.demo.controller;


import com.chatApp.demo.service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
public class MessageController {
    @Autowired
    MessageService messageService;


    @GetMapping("/messages/getAllMsgsInRoom/")
    public ResponseEntity getAllMsgsInRoom(HttpServletRequest req, HttpServletResponse res, @RequestParam String roomId ) throws JsonProcessingException {

        System.out.println("Inside the Room controller");
        System.out.println("Room id is:");
        System.out.println(roomId);
//       ========For testing=========
//        String roomIdTest= roomService.findOneRoom();

//==============================================================
        List  allMsgsInRoom = messageService.getAllMsgsInRoom(roomId);

        ObjectMapper objectMapper= new ObjectMapper();

        String responseBody= objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(allMsgsInRoom);
        return ResponseEntity.status(200).body(responseBody);
    }

    @GetMapping("/messages/allMsgsByRoom")
    public ResponseEntity getAllMsgsByRoom(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException {
        Map allMsgsByRoom= messageService.getAllMsgsByRoom();
        System.out.println("allMsgsByRoom isss:");
        ObjectMapper mapper= new ObjectMapper();
        String stringifiedAllMsgsByRoom= mapper.writerWithDefaultPrettyPrinter().writeValueAsString(allMsgsByRoom);
        System.out.println(stringifiedAllMsgsByRoom);

        return ResponseEntity.status(200).body(stringifiedAllMsgsByRoom);
    }



//    @MessageMapping("/addMsgToDb")
//    @SendTo("/wsFromServer/addMsgToDb")
//    public String addMsgToDb (@Payload String incomingMsg) throws JsonProcessingException {
//        System.out.println("================================");
//        System.out.println("this is the chattttt");
//        System.out.println("================================");
//
//        System.out.println(incomingMsg);
//        Message newMsg= messageService.create(incomingMsg);
//        if (newMsg.equals(null)) return null;
//        ObjectMapper objectMapper= new ObjectMapper();
//        String jsonNewMsg= objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(newMsg);
//        return jsonNewMsg;
//    }

}
