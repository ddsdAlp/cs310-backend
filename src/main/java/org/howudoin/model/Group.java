package org.howudoin.model;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Group {

    @Id
    private String groupName;
    private List<String> members = new ArrayList<>();           //List of all member emails
    private List<String> messageHistory = new ArrayList<>();    //List of all messages sent to the group
    private LocalDateTime creationDate;

    public List<String> getMembers(){
        return members;
    }

    public List<String> getMessageHistory(){
        return messageHistory;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
