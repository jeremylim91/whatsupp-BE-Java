package com.chatApp.demo.seeders;

import org.springframework.stereotype.Component;

@Component
public class AvatarCreator {
    private  String avatar= "avataaars";
    private  String gridy= "gridy";

    public String getAvatarImg(String roomName){
        return "https://avatars.dicebear.com/api/"+avatar+ "/"+ roomName+ ".svg";
    }
    public String getGridyImg(String roomName){
        return "https://avatars.dicebear.com/api/"+gridy+ "/"+ roomName+ ".svg";
    }
}
