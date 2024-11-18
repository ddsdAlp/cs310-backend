package org.howudoin.model;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class Message {

    @Id
    private String Id;
    private String senderEmail;
    private String receiverEmail;
    private List<String> messageHistory = new ArrayList<>();

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public List<String> getMessageHistory() {
        return messageHistory;
    }

    public void setMessageHistory(String message) {
        messageHistory.add(message);
    }
}
