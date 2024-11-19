package org.howudoin.model;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class Group {

    @Id
    private String groupName;
    private List<String> members = new ArrayList<>();           //List of all member emails
    private List<String> messageHistory = new ArrayList<>();    //List of all messages sent to the group

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
}
