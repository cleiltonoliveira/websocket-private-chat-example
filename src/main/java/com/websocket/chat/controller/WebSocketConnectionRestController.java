package com.websocket.chat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.websocket.chat.dto.ChatMessage;
import com.websocket.chat.repository.MessageRepository;

@RestController
public class WebSocketConnectionRestController {
	@Autowired
	private MessageRepository repository;

	@PostMapping("/rest/user-connect")
	public List<ChatMessage> userConnect(HttpServletRequest request,
			@ModelAttribute("username") String userName, HttpServletResponse response) {

		return repository.findMessages(userName);
	}

	@PostMapping("/rest/user-disconnect")
	public String userDisconnect(@ModelAttribute("username") String userName) {
		return "disconnected";
	}
}
