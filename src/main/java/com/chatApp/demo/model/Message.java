package com.chatApp.demo.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection="messages")
public class Message {
    @Id
    private ObjectId id;
    private String message;
    private String username;
    private LocalDateTime timeStamp;
    private String room_id;

    public Message(String message, String username, LocalDateTime timeStamp, String room_id) {
        this.message = message;
        this.username = username;
        this.timeStamp = timeStamp;
        this.room_id = room_id;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                ", username='" + username + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", room_id='" + room_id + '\'' +
                '}';
    }

    public ObjectId getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }
}
