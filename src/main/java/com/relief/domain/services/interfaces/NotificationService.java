package com.relief.domain.services.interfaces;

import com.relief.application.dtos.NotificationDTO;
import com.relief.application.requests.NotificationRequest;
import com.relief.domain.models.Notification;
import com.relief.domain.models.Users;
import com.relief.domain.models.newsFeed.Comment;
import com.relief.domain.models.newsFeed.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface NotificationService extends BaseService<Notification, Long> {

    void create(NotificationRequest request);

    void update(Long id, NotificationRequest request);

    List<NotificationDTO> findAll();

    Page<NotificationDTO> getPage(Pageable pageable);

    void like(UUID userId, boolean isSendEvent);

    void like(Users currentUser, Users userSubject, Post post, Comment comment, boolean isSendEvent);
}
