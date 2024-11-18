package org.howudoin.service;

import org.howudoin.model.Message;
import org.howudoin.model.User;
import org.howudoin.repository.MessageRepository;
import org.howudoin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    //send message
    public String sendMessage(String senderEmail, String receiverEmail, String message){

        if (userRepository.findByEmail(senderEmail) == null) {
            throw new RuntimeException("Can't find email");
        }
        if (userRepository.findByEmail(receiverEmail) == null) {
            throw new RuntimeException("Can't find email");
        }

        User sender = userRepository.findByEmail(senderEmail);
        User receiver = userRepository.findByEmail(receiverEmail);

        if(receiver.getFriends().contains(senderEmail)){
            List<String> emails = new ArrayList<>();
            emails.add(senderEmail);
            emails.add(receiverEmail);

            Message newMessage = messageRepository.findBySenderEmailInAndReceiverEmailIn(emails, emails);

            if(newMessage == null){
                newMessage = new Message();

                newMessage.setSenderEmail(senderEmail);
                newMessage.setReceiverEmail(receiverEmail);
                newMessage.setMessageHistory(message);
            }
            else{
                newMessage.setSenderEmail(senderEmail);
                newMessage.setReceiverEmail(receiverEmail);
                newMessage.setMessageHistory(message);
            }

            messageRepository.save(newMessage);
            return "Message sent.";
        }

        return "You are not friends with that user.";
    }

    //show all messages
    public List<String> showMessageHistory(String senderEmail, String receiverEmail){
        List<String> emails = new ArrayList<>();
        emails.add(senderEmail);
        emails.add(receiverEmail);
        Message message = messageRepository.findBySenderEmailInAndReceiverEmailIn(emails, emails);


        return message.getMessageHistory();

    }
}
