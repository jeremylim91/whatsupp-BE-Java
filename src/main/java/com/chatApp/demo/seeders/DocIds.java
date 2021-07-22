package com.chatApp.demo.seeders;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Component
@Data
public class DocIds {
    @Id private ObjectId DEV_HANGOUT;
    @Id private ObjectId FOODIES_UNITE;
    @Id private ObjectId CYCLING_BUDDIES;

}
