package com.chatApp.demo.socketController;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

//package com.chatApp.demo;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
//
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
@Override
    public void configureMessageBroker(MessageBrokerRegistry config){
//   We enable an in-memory message broker to carry the messages back to the client on
//   destinations prefixed with “/topic”
    config.enableSimpleBroker("/topic");
//    We complete our simple configuration by designating the “/app” prefix to filter destinations targeting application annotated methods (via @MessageMapping).
    config.setApplicationDestinationPrefixes("/app");
}
@Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
//    registry.addEndpoint("/chat");
    registry.addEndpoint("/chat").setAllowedOrigins("*");
    registry.addEndpoint("/chat").setAllowedOrigins("*").withSockJS();

}
}
