package com.chatApp.demo.repository;

import com.chatApp.demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//https://stackoverflow.com/questions/12730370/spring-data-mongodb-findby-method-for-nested-objects
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query(value = "{ 'username' : ?0, 'password' : ?1 }")
    User findUserInstanceByUsernameAndPassword(String username, String password);

}
