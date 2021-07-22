package com.chatApp.demo.model;

import com.chatApp.demo.seeders.AvatarCreator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection="rooms")
public class Room {
    @Id
    private String id;
    private String name;
    private String image;
    private String createdBy;
    private ArrayList associated_messages;


    public Room (){
        this ("", "", "", new ArrayList());
    }
    public Room (String name, String createdBy){
        this(name, new AvatarCreator().getGridyImg(name), createdBy, new ArrayList());
    }
    public Room(String name, String image, String createdBy, ArrayList associated_messages) {
        this.name = name;
        this.image = image;
        this.createdBy = createdBy;
        this.associated_messages = associated_messages;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", associated_messages=" + associated_messages +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ArrayList getAssociated_messages() {
        return associated_messages;
    }

    public void setAssociated_messages(ArrayList associated_messages) {
        this.associated_messages = associated_messages;
    }
}
