package com.relief.domain.listener;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class RedisMessageSubscriber implements MessageListener {

    private final SocketIOServer socketIOServer;

    public RedisMessageSubscriber(SocketIOServer socketIOServer) {
        this.socketIOServer = socketIOServer;
    }


    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println("Received message: " + message.toString());
        socketIOServer.getBroadcastOperations().sendEvent("message", message);
    }
}
