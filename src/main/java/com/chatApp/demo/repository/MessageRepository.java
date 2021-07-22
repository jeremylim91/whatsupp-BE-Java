package com.chatApp.demo.repository;

import com.chatApp.demo.model.Message;
import com.chatApp.demo.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

    List <Message> findAll();
    Optional<List> findMessagesById(List associatedMessages);

    @Override
    Iterable<Message> findAllById(Iterable<String> iterable);

    Message insert(Message msgToInsert);

    @Override
    Optional<Message> findById(String s);
}
