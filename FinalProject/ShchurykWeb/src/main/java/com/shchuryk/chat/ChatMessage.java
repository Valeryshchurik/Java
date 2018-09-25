package com.shchuryk.chat;

import java.util.Date;

/**
 *
 * @author Egor Piankov
 */
public class ChatMessage {

    public ChatMessage(String message, String sender, Date received) {
        this.message = message;
        this.sender = sender;
        this.received = received;
    }

    public ChatMessage() {
    }

    private String message;
    private String sender;
    private Date received;
    private Boolean isAdmin;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Date getReceived() {
        return received;
    }

    public void setReceived(Date received) {
        this.received = received;
    }
    
    public Boolean getAdminRole() {
        return isAdmin;
    }

    public void setAdminRole(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return String.format("{0}: {1} ({2})", sender, message, received.toString());
    }
}
