package cn.korostudio.koroworld_servercore.service;

import cn.hutool.core.thread.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;


@Component
public class ChatAndBroadCastService extends TextWebSocketHandler {
    protected static Logger logger = LoggerFactory.getLogger(ChatAndBroadCastService.class);

    protected static ConcurrentHashMap<String ,WebSocketSession> Servers=new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String serverName = (String)session.getAttributes().get("token");
        logger.info("Server "+serverName+" Connection.");
        Servers.put(serverName,session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String serverName = (String)session.getAttributes().get("token");
        logger.info("Server "+serverName+" Close Connection.");
        Servers.remove(serverName);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String serverName = (String)session.getAttributes().get("token");
        logger.info("Get Message From:"+serverName +" Data is:"+message.getPayload());
        ThreadUtil.execute(()->{
            for (String key:Servers.keySet()){
                if(key.equals(serverName)){
                    continue;
                }
                try {
                    Servers.get(key).sendMessage(message);
                } catch (IOException e) {
                    logger.error("Send Message Error,target is "+key);
                }
            }
        });
    }
}
