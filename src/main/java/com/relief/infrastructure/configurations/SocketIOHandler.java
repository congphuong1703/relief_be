package com.relief.infrastructure.configurations;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.relief.application.requests.NotificationRequest;
import com.relief.domain.services.interfaces.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class SocketIOHandler {


    private final SocketIOServer socketServer;
    private final NotificationService notificationService;
    private ConcurrentHashMap<String, SocketIOClient> userSessions = new ConcurrentHashMap<>();

    @Autowired
    public SocketIOHandler(SocketIOServer socketServer, NotificationService notificationService) {
        this.socketServer = socketServer;
        this.notificationService = notificationService;
        socketServer.addConnectListener(onConnected());
        socketServer.addDisconnectListener(onDisconnected());
        socketServer.addEventListener("chat", NotificationRequest.class, onChatReceived());

//        this.namespace = socketServer.addNamespace("/chat");
//        this.namespace.addConnectListener(onConnected());
//        this.namespace.addDisconnectListener(onDisconnected());
//        this.namespace.addEventListener("chat", ChatMessage.class, onChatReceived());
    }

    private DataListener<NotificationRequest> onChatReceived() {
        return (client, data, ackSender) -> {
            this.notificationService.create(data);
            this.sendNotificationToUser(data.getUserId(), data.getBody());
//            this.socketServer.getBroadcastOperations().sendEvent("chat", this.notificationService.findAll());
        };
    }

    private ConnectListener onConnected() {
        return client -> {
            String userId = client.getHandshakeData().getSingleUrlParam("userId");
            userSessions.put(userId, client);
            log.info("Client[{}] - Connected to chat module through '{}'", client.getSessionId().toString());
        };
    }

    private DisconnectListener onDisconnected() {
        return client -> {
            String userId = client.getHandshakeData().getSingleUrlParam("userId");
            userSessions.remove(userId);
            log.debug("Client[{}] - Disconnected from chat module.", client.getSessionId().toString());
        };
    }

    public void sendNotificationToUser(String userId, String message) {
        SocketIOClient client = userSessions.get(userId);
        if (client != null) {
            client.sendEvent("chat", message);
        }
    }
//        public DataListener<User> onUserJoinChat = new DataListener<User>() {
//            @Override
//            public void onData(SocketIOClient client, User user, AckRequest ackSender) throws Exception {
//                namespace.getBroadcastOperations().sendEvent("newUser", user);
//                users.put(client, user.getUsername());
//                namespace.getBroadcastOperations().sendEvent("count", users.size());
//            }
//        };
//
//        public DataListener<Message> onUserSendMessage = new DataListener<Message>() {
//            @Override
//            public void onData(SocketIOClient client, Message message, AckRequest arg2) throws Exception {
//                namespace.getBroadcastOperations().sendEvent("newMessage", client, message);
//                messageService.add(message);
//            }
//        };
//
//        public DataListener<User> onUserTyping = new DataListener<User>() {
//            @Override
//            public void onData(SocketIOClient client, User user, AckRequest arg2) throws Exception {
//                namespace.getBroadcastOperations().sendEvent("userTyping", client, user);
//            }
//        };
//
//        public DataListener<User> onUserStopTyping = new DataListener<User>() {
//            @Override
//            public void onData(SocketIOClient client, User user, AckRequest arg2) throws Exception {
//                namespace.getBroadcastOperations().sendEvent("userStopTyping", client, user);
//            }
//        };

}
