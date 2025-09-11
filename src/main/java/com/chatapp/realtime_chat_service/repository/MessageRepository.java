package com.chatapp.realtime_chat_service.repository;

import com.chatapp.realtime_chat_service.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findTop50ByReceiverIdOrderByTimestampDesc(Long receiverId);
}
