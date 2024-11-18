package org.howudoin.repository;

import org.howudoin.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message,String> {
    Message findBySenderEmailInAndReceiverEmailIn(List<String> userEmails1, List<String> userEmails2);
}
