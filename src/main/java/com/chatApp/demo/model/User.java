package com.chatApp.demo.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection="users")
public class User {
    @Id
    private ObjectId id; //No need to add this field to constructor; the mondgodb driver adds creates a unique id and adds it in automatically
    private String name;
    private String username;
    private String password;
    private String imgUrl;

    public User(String name, String username, String password) {
        this(name, username, password, "No image provided");
    }
    public User(String name, String username, String password, String imgUrl) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.imgUrl = imgUrl;
    }
}
