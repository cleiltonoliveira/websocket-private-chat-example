package com.websocket.chat.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

//@Configuration
public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {

    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages
                .nullDestMatcher().authenticated() 
                .simpSubscribeDestMatchers("/user/queue/errors").permitAll() 
                .simpDestMatchers("/app/**").hasRole("USER") 
//                .simpSubscribeDestMatchers("/user/**").hasRole("USER") 
//                .anyMessage().denyAll()
                ; 
    }
    @Override
    protected boolean sameOriginDisabled() {
        return true;
    }
}