package org.howudoin.model;

import org.springframework.data.annotation.Id;

public class FriendRequest {

    @Id
    private String id;
    private String senderEmail;
    private String receiverEmail;
    private String status; // PENDING, ACCEPTED, DECLINED

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSenderEmail(){
        return senderEmail;
    }

    public String getReceiverEmail(){
        return receiverEmail;
    }

}
