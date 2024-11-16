package org.howudoin.service;

import org.howudoin.model.User;
import org.howudoin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(User user){
        userRepository.save(user);
        System.out.println("User Saved");
    }

    public User loginUser(String email, String password){
        User user = userRepository.findByEmail(email);
        if(!password.matches(user.getPassword())){
            throw new RuntimeException("Invalid email or password");
        }

        return user;
    }
}
