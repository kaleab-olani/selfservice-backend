package com.safari.selfservice.service;

import com.safari.selfservice.MessageState;
import com.safari.selfservice.UserState;
import com.safari.selfservice.models.Message;
import com.safari.selfservice.models.SystemUser;
import com.safari.selfservice.repository.MessageRepository;
import com.safari.selfservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MessageService {
    private MessageRepository messageRepository;
    private UserRepository userRepository;
    @Autowired
    public MessageService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public List<Message> getMessages(Integer user1Id, Integer user2Id) {
        List<Message> messageList = new ArrayList<>();
        messageList.addAll(messageRepository.findByReceiverUserIDAndSenderUserID(user1Id, user2Id));
        messageList.addAll(messageRepository.findByReceiverUserIDAndSenderUserID(user2Id, user1Id));

        messageList.sort(Comparator.comparing(Message::getDateSent));
        return messageList;
    }

    public Message getMessage(Integer messageId) {
        Optional<Message> message = messageRepository.findById(messageId);
        return message.orElse(null);
    }

    public Message saveMessage(Message message) {
        if (message!=null){
            String msgContent = message.getMessage();
            message.setMessage(msgContent == null ? "" : msgContent.substring(0, Math.min(msgContent.length(),180)));
            Integer receiverUserID = message.getReceiverUserID();
            Integer senderUserID = message.getSenderUserID();
            boolean receiverPresent = userRepository.findById(receiverUserID).isPresent();
            boolean senderPresent = userRepository.findById(senderUserID).isPresent();

            if (receiverPresent && senderPresent){
                SystemUser senderUser = userRepository.findById(senderUserID).get();
                if (senderUser.getUserState().equals(UserState.DEACTIVATED.name()) || senderUser.getNumOfMessagesLeft() <= 0 ){
                    senderUser.setUserState(UserState.DEACTIVATED.name());
                    userRepository.save(senderUser);
                } else {
                    senderUser.setNumOfMessagesLeft(senderUser.getNumOfMessagesLeft() -1);
                    message.setMessageState(MessageState.SENT.name());
                    Date currentDate = Calendar.getInstance(TimeZone.getDefault()).getTime();
                    message.setDateSent( currentDate);
                    return messageRepository.save(message);
                }
            }
        }
        return null;
    }
}
