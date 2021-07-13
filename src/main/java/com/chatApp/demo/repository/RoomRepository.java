package com.chatApp.demo.repository;

import com.chatApp.demo.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends MongoRepository <Room, String> {
}
