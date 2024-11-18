package org.howudoin.controller;

import org.howudoin.service.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FriendRequestController {

    @Autowired
    private FriendRequestService friendRequestService;

    @PostMapping("/friends/add")
    public String sendFriendRequest(@RequestParam String senderEmail, @RequestParam String receiverEmail){
        return friendRequestService.sendFriendRequest(senderEmail,receiverEmail);
    }

    //need to change REQUEST ID of FRIEND REQUEST

    @PostMapping("/friends/accept")
    public String respondToFriendRequest(@RequestParam String requestId, @RequestParam boolean accepted){
        return  friendRequestService.respondToFriendRequest(requestId, accepted);
    }

    //@GetMapping("/friends")
    //need to implement User Service to get friend of user
}
