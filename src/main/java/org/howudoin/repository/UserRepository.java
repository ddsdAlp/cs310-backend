package org.howudoin.repository;

import org.howudoin.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String>{
    User findByEmail(String email);
    List<User> findAll();
}
