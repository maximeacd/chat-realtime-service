package com.chatapp.realtime_chat_service.repository;

import com.chatapp.realtime_chat_service.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    Page<Message> findTop50ByReceiverIdOrderByTimestampDesc(Long receiverId, Pageable pageable);

    Page<Message> findBySenderIdAndReceiverIdOrReceiverIdAndSenderIdOrderByTimestampDesc(Long senderId, Long receiverId, Long receiverId2, Long senderId2, Pageable pageable);

    Page<Message> findByReceiverIdAndContentContainingIgnoreCaseOrderByTimestampDesc(Long receiverId, String keyword, Pageable pageable);
}
