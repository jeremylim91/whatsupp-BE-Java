package com.chatApp.demo.controller;

import com.chatApp.demo.model.Room;
import com.chatApp.demo.service.RoomService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class RoomController {
    @Autowired
    RoomService roomService;


    @GetMapping("/rooms/index")
    public void index(HttpServletRequest req, HttpServletResponse res){


    }

    @PostMapping("/rooms/create")
    public void create(HttpServletRequest req, HttpServletResponse res, @RequestBody String roomDetails) throws JsonProcessingException {


        //        Send to service layer to create room
        roomService.create(roomDetails);
    }

    @MessageMapping("/createRoom")
    @SendTo("updateRoomList")
    public String createMessage (String incomingMsg) throws Exception{
        roomService.create(incomingMsg);
        return (null);
    }
}
