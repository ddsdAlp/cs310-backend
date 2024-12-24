package org.howudoin.controller;

import org.howudoin.JwtHelperUtils;
import org.howudoin.model.User;
import org.howudoin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtHelperUtils jwtHelperUtils;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user){
        userService.registerUser(user);
        return "POST Request - Register";
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User loginRequest){
        //return userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
        // Validate the user credentials (email & password)


        UserDetails userDetails = userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());

        /*if (userDetails != null) {
            // Generate the JWT token
            String jwtToken = jwtHelperUtils.generateToken(userDetails);
            return jwtToken; // Return the token
        } else {
            throw new RuntimeException("Invalid credentials");
        }*/

        String jwtToken = jwtHelperUtils.generateToken(userDetails);
        return jwtToken;
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
