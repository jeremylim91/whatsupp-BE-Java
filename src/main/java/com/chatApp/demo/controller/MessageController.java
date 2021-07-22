package com.chatApp.demo.controller;


import com.chatApp.demo.model.Message;
import com.chatApp.demo.service.MessageService;
import com.chatApp.demo.service.RoomService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
public class MessageController {
    @Autowired
    MessageService messageService;
    @Autowired
    RoomService roomService;

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
    public ResponseEntity getAllMsgsByRoom(HttpServletRequest req, HttpServletResponse res){
        Map allMsgsByRoom= messageService.getAllMsgsByRoom();

        return ResponseEntity.status(200).body(allMsgsByRoom);
    }





}
