package com.relief.domain.services;

import com.corundumstudio.socketio.SocketIOServer;
import com.relief.application.dtos.NotificationDTO;
import com.relief.application.requests.NotificationRequest;
import com.relief.domain.enums.ActionType;
import com.relief.domain.enums.ChanelType;
import com.relief.domain.listener.RedisMessagePublisher;
import com.relief.domain.models.Notification;
import com.relief.domain.models.Users;
import com.relief.domain.models.newsFeed.Comment;
import com.relief.domain.models.newsFeed.Post;
import com.relief.domain.services.interfaces.AbstractBaseService;
import com.relief.domain.services.interfaces.NotificationService;
import com.relief.infrastructure.mappers.NotificationMapper;
import com.relief.infrastructure.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service
public class NotificationServiceImpl extends AbstractBaseService<Notification, Long> implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private SocketIOServer socketServer;

    @Autowired
    private RedisMessagePublisher redisMessagePublisher;

    @Override
    protected JpaRepository<Notification, Long> getRepository() {
        return this.notificationRepository;
    }

    @Override
    public void create(NotificationRequest request) {
        Notification notification = this.notificationMapper.toDomain(request);
        this.create(notification);
    }

    @Override
    public void update(Long id, NotificationRequest request) {
        Notification notification = this.checkExists(id);
        this.notificationMapper.updateDomain(request, notification);
        this.create(notification);
    }

    @Override
    public List<NotificationDTO> findAll() {
        List<Notification> notifications = this.notificationRepository.findAll();
        return this.notificationMapper.toListDTO(notifications);

    }

    @Override
    public Page<NotificationDTO> getPage(Pageable pageable) {
        Page<Notification> notifications = this.notificationRepository.findAll(pageable);
        return notifications.map(e -> this.notificationMapper.toDTO(e));
    }

    @Override
    public void like(Users currentUser, Users userSubject, Post post, Comment comment, boolean isSendEvent) {
        Notification notification = Notification.builder()
                .post(post)
                .comment(comment)
                .chanel(ChanelType.WEB_APP)
                .actionType(ActionType.LIKE)
                .subjects(new HashSet<>() {{
                    add(userSubject);
                }})
                .sender(currentUser)
                .build();
        this.create(notification);
        if (isSendEvent) {
//            SocketIOClient client = socketServer.getClient(userId);
            redisMessagePublisher.publish("notification", ActionType.LIKE);
        }
    }
}
