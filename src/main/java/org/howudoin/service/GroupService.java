package org.howudoin.service;

import org.howudoin.model.Group;
import org.howudoin.model.User;
import org.howudoin.repository.GroupRepository;
import org.howudoin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    //create group
    public String createGroup(String groupName, List<String> emailList){

        Group group = new Group();
        group.setGroupName(groupName);

        //Add emails to the group members list
        for(String email: emailList){
            if(userRepository.findByEmail(email) != null){
                if(!group.getMembers().contains(email)){
                    group.getMembers().add(email);;
                }
            }
        }

        groupRepository.save(group);

        return "Group Created";
    }

    //add member to group
    public String addMemberToGroup(String email, String groupName){
        if(userRepository.findByEmail(email) == null){
            return "User doesn't exist";
        }

        Group group = groupRepository.findByGroupName(groupName);

        if(group.getMembers().contains(email)){
            return "User already in group";
        }

        group.getMembers().add(email);

        groupRepository.save(group);

        return "User added to group";

    }

    //send message to group
    public String sendMessageToGroup(String senderEmail, String message, String groupName){
        User user = userRepository.findByEmail(senderEmail);
        Group group = groupRepository.findByGroupName(groupName);

        group.getMessageHistory().add(user.getName()+": "+message);

        groupRepository.save(group);

        return "Message sent to group";
    }

    //view all messages in the group
    public List<String> seeAllGroupMessages(String groupName){
        Group group = groupRepository.findByGroupName(groupName);

        return group.getMessageHistory();
    }

    //view all members in the group
    public List<String> seeAllGroupMembers(String groupName){
        Group group = groupRepository.findByGroupName(groupName);

        return group.getMembers();
    }

    //view all groups a member if part of
    public List<String> showGroups(String email){
        List<Group> groups = groupRepository.findAll();
        List<String> userGroups = new ArrayList<>();

        for(Group group: groups){
            if(group.getMembers().contains(email)){
                userGroups.add(group.getGroupName());
            }
        }

        return userGroups;
    }
}
