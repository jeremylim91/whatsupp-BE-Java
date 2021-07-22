package com.chatApp.demo.repository;

import com.chatApp.demo.model.Message;
import com.chatApp.demo.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends MongoRepository <Room, String> {
    Optional <Room> findById(String id);

    List<Room> findAll();

    Room insert(Room roomToInsert);
}
