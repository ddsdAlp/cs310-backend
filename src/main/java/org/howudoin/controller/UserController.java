package org.howudoin.controller;

import org.howudoin.model.User;
import org.howudoin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user){
        userService.registerUser(user);
        return "POST Request - Register";
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody User loginRequest){
        return userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
    }

    @PostMapping("/friends/add")
    public String sendFriendRequest(@RequestParam String senderEmail, @RequestParam String receiverEmail){
        return userService.sendRequest(senderEmail,receiverEmail);
    }

    @PostMapping("/friends/accept")
    public String respondToFriendRequest(@RequestParam String senderEmail, @RequestParam String receiverEmail){
        return  userService.acceptRequest(senderEmail, receiverEmail);
    }

    @GetMapping("/friends")
    public List<String> showFriends(@RequestParam String email){
        return userService.showFriends(email);
    }

    @GetMapping("/show")
    public List<User> showUsers(){
        return userService.showAllUsers();
    }

}
