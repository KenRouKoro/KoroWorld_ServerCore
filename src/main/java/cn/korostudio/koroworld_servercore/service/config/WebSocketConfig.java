package cn.korostudio.koroworld_servercore.service.config;

import cn.korostudio.koroworld_servercore.service.ChatAndBroadCastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private ChatAndBroadCastService chatAndBroadCastService;
    @Autowired
    private ChatInterceptor chatInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry
                .addHandler(chatAndBroadCastService, "/message/ws")
                .addInterceptors(chatInterceptor)
                .setAllowedOrigins("*");
    }
}
