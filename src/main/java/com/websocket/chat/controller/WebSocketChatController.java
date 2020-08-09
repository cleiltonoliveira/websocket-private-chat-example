package com.websocket.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.websocket.chat.dto.ChatMessage;
import com.websocket.chat.repository.MessageRepository;

@Controller
public class WebSocketChatController {

	@Autowired
	private SimpMessagingTemplate webSocket;

	@Autowired
	private MessageRepository repository;

	@GetMapping("/sockjs-message")
	public String getWebSocketWithSockJs() {
		return "sockjs-message";
	}

	@MessageMapping("/chat")
	public void send(SimpMessageHeaderAccessor sha, @Payload ChatMessage chatMessage) throws Exception {
		String sender = sha.getUser().getName();
		System.out.println(sha);
		ChatMessage message = new ChatMessage(chatMessage.getFrom(), chatMessage.getText(), chatMessage.getRecipient());

		message = repository.save(message);

		if (!sender.equals(chatMessage.getRecipient())) {
			webSocket.convertAndSendToUser(sender, "/queue/messages", message);
		}
		webSocket.convertAndSendToUser(chatMessage.getRecipient(), "/queue/messages", message);
	}
}
