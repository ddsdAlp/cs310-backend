package org.howudoin.repository;

import org.howudoin.model.FriendRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface FriendRequestRepository extends MongoRepository<FriendRequest, String> {
    List<FriendRequest> findByReceiverEmail(String receiverEmail);
    List<FriendRequest> findBySenderEmail(String senderEmail);

}
