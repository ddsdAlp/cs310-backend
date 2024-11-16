package org.howudoin.controller;

import org.howudoin.model.User;
import org.howudoin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
}
