package org.howudoin.model;

import org.springframework.data.annotation.Id;

import java.util.List;

public class User {

    private String name;
    private String lastName;
    private String email;
    private String password;

    private List<String> friends; // List of user Emails

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    public List<String> getFriends(){
        return friends;
    }
}
