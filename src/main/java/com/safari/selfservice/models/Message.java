package com.safari.selfservice.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table
public class Message {
    @Id
    @SequenceGenerator(
            name = "message_id",
            sequenceName = "message_id",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "message_id"
    )
    private Integer id;
    private Integer senderUserID;
    private Integer receiverUserID;
    private String message;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date dateSent;
    private String messageState; // possible values (new, failed, sent, delivered, seen)

    public Message() {
    }

    public Message(Integer id, Integer senderUserID, Integer receiverUserID, String message, Date dateSent, String messageState) {
        this.id = id;
        this.senderUserID = senderUserID;
        this.receiverUserID = receiverUserID;
        this.message = message;
        this.dateSent = dateSent;
        this.messageState = messageState;
    }

    public Message(Integer senderUserID, Integer receiverUserID, String message, Date dateSent, String messageState) {
        this.senderUserID = senderUserID;
        this.receiverUserID = receiverUserID;
        this.message = message;
        this.dateSent = dateSent;
        this.messageState = messageState;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSenderUserID() {
        return senderUserID;
    }

    public void setSenderUserID(Integer senderUserID) {
        this.senderUserID = senderUserID;
    }

    public Integer getReceiverUserID() {
        return receiverUserID;
    }

    public void setReceiverUserID(Integer receiverUserID) {
        this.receiverUserID = receiverUserID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    public String getMessageState() {
        return messageState;
    }

    public void setMessageState(String messageState) {
        this.messageState = messageState;
    }
}
