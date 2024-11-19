package org.howudoin.controller;

import org.howudoin.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/groups/create")
    public String createGroup(@RequestParam String groupname, @RequestBody List<String> emailList){
        return groupService.createGroup(groupname, emailList);
    }

    @PostMapping("/groups/{groupId}/add-member")
    public String addMemberToGroup(@RequestParam String email, @PathVariable String groupId){
        return groupService.addMemberToGroup(email, groupId);
    }

    @PostMapping("/groups/{groupId}/send")
    public String sendMessageToGroup(@RequestParam String email, @RequestParam String message, @PathVariable String groupId){
        return groupService.sendMessageToGroup(email, message, groupId);
    }

    @GetMapping("/groups/{groupId}/messages")
    public List<String> showAllMessages(@PathVariable String groupId){
        return groupService.seeAllGroupMessages(groupId);
    }

    @GetMapping("/groups/{groupId}/members")
    public List<String> showAllMembers(@PathVariable String groupId){
        return groupService.seeAllGroupMembers(groupId);
    }

}
