package com.websocket.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.websocket.chat.dto.ChatMessage;

@Repository
public interface MessageRepository extends JpaRepository<ChatMessage, Long> {

	@Query(value = "select * from message where sender = ?1 or recipient = ?1",nativeQuery = true)
	List<ChatMessage> findMessages(String user);
}
