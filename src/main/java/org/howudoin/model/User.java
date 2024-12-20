package org.howudoin.model;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
@Document(collection = "users")
@Data
public class User {

    @Id
    private String Id;
    private String name = "name";
    private String lastName = "lastname";
    private String email = "email";
    private String password = "password";
    @CreatedDate
    private LocalDate createdDate;

    private List<String> friends = new ArrayList<>(); // List of user Emails who are friends of this user
    private List<String> friendRequests = new ArrayList<>();; //List of user Emails who have sent a friend request to user

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    public List<String> getFriends(){
        return friends;
    }

    public List<String> getFriendRequests(){
        return friendRequests;
    }

    public void setFriendRequests(String email){
        friendRequests.add(email);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
