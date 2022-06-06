package com.safari.selfservice.repository;

import com.safari.selfservice.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findBySenderUserID(String senderUserID);
    List<Message> findByReceiverUserID(String receiverUserID);
    List<Message> findByReceiverUserIDAndSenderUserID(Integer receiverUserID, Integer senderUserID);
}
