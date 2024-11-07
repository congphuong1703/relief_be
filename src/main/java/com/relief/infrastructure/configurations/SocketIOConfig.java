package com.relief.infrastructure.configurations;

import com.corundumstudio.socketio.SocketIOServer;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocketIOConfig {

    @Value("${socket-server.host}")
    private String host;

    @Value("${socket-server.port}")
    private Integer port;


    @Bean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setHostname("localhost");
        config.setPort(port);
        return new SocketIOServer(config);
    }

    @Bean
    public ApplicationRunner runner(SocketIOServer socketIOServer) {
        return args -> socketIOServer.start();
    }

    @PreDestroy
    public void preDestroy(SocketIOServer socketIOServer) {
        socketIOServer.stop();
    }
}