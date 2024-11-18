package org.howudoin.service;

import org.howudoin.model.User;
import org.howudoin.model.FriendRequest;
import org.howudoin.repository.FriendRequestRepository;
import org.howudoin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FriendRequestService {

    @Autowired
    private FriendRequestRepository friendRequestRepository;

    @Autowired
    private UserRepository userRepository;

    public String sendFriendRequest(String senderEmail, String receiverEmail){

        // Find sender and receiver by email, does not check if email exists or not
        User sender = userRepository.findByEmail(senderEmail);
        User receiver = userRepository.findByEmail(receiverEmail);

        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setSenderEmail(senderEmail);
        friendRequest.setReceiverEmail(receiverEmail);
        friendRequest.setStatus("PENDING");

        friendRequestRepository.save(friendRequest);

        return "Friend request sent!";
    }

    public String respondToFriendRequest(String requestId, boolean accepted){
        Optional<FriendRequest> friendRequest = friendRequestRepository.findById(requestId);

        if (friendRequest.isEmpty()) {
            return "Friend request not found!";
        }

        FriendRequest request = friendRequest.get();

        if(accepted){
            request.setStatus("ACCEPTED");

            // Updating users friend lists
            User sender = userRepository.findByEmail(request.getSenderEmail());
            User receiver = userRepository.findByEmail(request.getReceiverEmail());

            sender.getFriends().add(receiver.getEmail());
            receiver.getFriends().add(sender.getEmail());

            userRepository.save(sender);
            userRepository.save(receiver);
        }
        else{
            request.setStatus("DECLINED");
        }

        friendRequestRepository.save(request);

        if(accepted){
            return "Friend request accepted!";
        }
        else{
            return "Friend request declined!";
        }
    }
}
