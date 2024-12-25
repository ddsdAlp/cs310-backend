package org.howudoin.controller;

import org.howudoin.model.User;
import org.howudoin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:8081")
    public String registerUser(@RequestBody User user){
        userService.registerUser(user);
        return "POST Request - Register";
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:8081")
    public User loginUser(@RequestBody User loginRequest){
        return userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
    }

    @PostMapping("/friends/add")
    @CrossOrigin(origins = "http://localhost:8081")
    public String sendFriendRequest(@RequestParam String senderEmail, @RequestParam String receiverEmail){
        return userService.sendRequest(senderEmail,receiverEmail);
    }

    @PostMapping("/friends/accept")
    @CrossOrigin(origins = "http://localhost:8081")
    public String respondToFriendRequest(@RequestParam String senderEmail, @RequestParam String receiverEmail){
        return  userService.acceptRequest(senderEmail, receiverEmail);
    }

    @GetMapping("/friends")
    @CrossOrigin(origins = "http://localhost:8081")
    public List<String> showFriends(@RequestParam String email){
        return userService.showFriends(email);
    }

    @GetMapping("/friends/requests")
    @CrossOrigin(origins = "http://localhost:8081")
    public List<String> showFriendRequests(@RequestParam String email){
        return userService.showFriendRequests(email);
    }

}
