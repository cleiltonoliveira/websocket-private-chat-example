package com.websocket.chat.util;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class UserInterceptor implements ChannelInterceptor {

	@Override
	public Message<?> preSend(final Message<?> message, final MessageChannel channel) {

		final StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
		if (StompCommand.CONNECT.equals(accessor.getCommand())) {

			System.out.println(accessor);
			Object raw = message
					.getHeaders()
					.get(SimpMessageHeaderAccessor.NATIVE_HEADERS);

			if (raw instanceof Map) {
				Object name = ((Map) raw).get("username");

				if (name instanceof LinkedList) {

					accessor.setUser(new User(((LinkedList) name).get(0).toString()));
				}
			}

//			String username = accessor.getFirstNativeHeader("login");
//			String password = accessor.getFirstNativeHeader("password");
//
//			System.out.println("On userInterc: " + password + username);
//			
//
//            final UsernamePasswordAuthenticationToken user = 
//            		
//            		 new UsernamePasswordAuthenticationToken(
//            	                username,
//            	                null,
//            	                Collections.singleton((GrantedAuthority) () -> "USER") // MUST provide at least one role
//            	        );
//            
//            accessor.setUser(user);
//            
//            accessor.setLogin(username);
//            accessor.setPasscode(password);
//            

		}

		return message;
	}

}