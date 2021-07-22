package com.chatApp.demo.controller;

import com.chatApp.demo.model.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomSummaryMap {
    private String id;
    private String name;
    private String image;
    private String createdBy;
    ArrayList associated_messages;
    private String lastMsg;

}
