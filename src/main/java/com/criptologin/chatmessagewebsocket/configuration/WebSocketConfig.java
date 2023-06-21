package com.criptologin.chatmessagewebsocket.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

	// configurazione del broker di messaggi:
	// viene abilitato un broker necessario per riportare i messaggi al client
	// definendo un prefisso utilizzato come mapping
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic");
		config.setApplicationDestinationPrefixes("/app");		
	}
	
	// viene abilitato l'opzione di fallback(ripiego) di SockJS, per trasporti alternativi 
	// nel caso in cui le webSocket non siano disponibili.
	// Ovvero SockJS tenterà di connettersi e utilizzare il miglior trasporto disponibile
	@Override
	  public void registerStompEndpoints(StompEndpointRegistry registry) {
	    registry.addEndpoint("/gs-guide-websocket").withSockJS();
	  }
}

