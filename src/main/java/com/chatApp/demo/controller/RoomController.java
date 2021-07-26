package com.chatApp.demo.controller;

import com.chatApp.demo.model.Room;
import com.chatApp.demo.service.RoomService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@RestController
public class RoomController {
    @Autowired
    RoomService roomService;


    @GetMapping("/rooms/index")
    public ResponseEntity index(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException {
        ArrayList dataToReturnClient = roomService.findAllRoomsWSummary();
        System.out.println("Data to return to client is:");
        System.out.println(dataToReturnClient);
        ObjectMapper mapper= new ObjectMapper();
        return ResponseEntity.status(200).body(mapper.writeValueAsString(dataToReturnClient));
    }

    @PostMapping("/rooms/create")
    public ResponseEntity create(HttpServletRequest req, HttpServletResponse res,
                    @RequestBody String roomDetails) throws JsonProcessingException {

        //        Send to service layer to create room
        roomService.create(roomDetails);
        return ResponseEntity.status(200).build();
    }

}
