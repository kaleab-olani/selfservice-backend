package com.safari.selfservice.controller;

import com.safari.selfservice.MessageState;
import com.safari.selfservice.models.Message;
import com.safari.selfservice.models.SystemUser;
import com.safari.selfservice.service.MessageService;
import com.safari.selfservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/")
public class MessageController {

    MessageService messageService;
    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/user-messages/{user1_id}/{user_2_id}")
    public ResponseEntity<List<Message>> getAllMessages(@PathVariable("user1_id") Integer user1Id, @PathVariable("user_2_id") Integer user2Id) {
        return new ResponseEntity<>(messageService.getMessages(user1Id, user2Id), HttpStatus.OK);
    }
    @GetMapping("/message/{message_id}")
    public ResponseEntity<Message> getMessage(@PathVariable("message_id") Integer messageId) {
        return new ResponseEntity<>(messageService.getMessage(messageId), HttpStatus.OK);
    }
    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        try {
            if (message.getDateSent() == null){
                message.setDateSent(new Date());
            }
            Message newMessage = messageService.saveMessage(message);
            if (newMessage == null){
                return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
            } else {
                return new ResponseEntity<>(newMessage, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
