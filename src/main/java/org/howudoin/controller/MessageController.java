package org.howudoin.controller;


import org.howudoin.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/messages/send")
    @CrossOrigin(origins = "http://localhost:8081")
    public String sendMessage(@RequestParam String senderEmail, @RequestParam String receiverEmail, @RequestParam String message){
        return messageService.sendMessage(senderEmail,receiverEmail,message);
    }

    @GetMapping("/messages")
    @CrossOrigin(origins = "http://localhost:8081")
    public List<String> showMessageHistory(@RequestParam String senderEmail, @RequestParam String receiverEmail){
        return messageService.showMessageHistory(senderEmail,receiverEmail);
    }

}
