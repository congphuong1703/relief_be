package com.relief.domain.services;

import com.relief.domain.designPatterns.interfaces.LikeStrategy;
import com.relief.domain.designPatterns.interfaces.NotificationActionStrategy;
import com.relief.domain.models.Users;
import com.relief.domain.models.newsFeed.Like;
import com.relief.domain.services.interfaces.AbstractBaseService;
import com.relief.domain.services.interfaces.LikeService;
import com.relief.domain.services.interfaces.UserService;
import com.relief.infrastructure.repositories.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class LikeServiceImpl extends AbstractBaseService<Like, Long> implements LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserService userService;

    @Override
    protected JpaRepository<Like, Long> getRepository() {
        return this.likeRepository;
    }


    private LikeStrategy likeStrategy;
    private NotificationActionStrategy notificationActionStrategy;

    @Override
    public void setLikeStrategy(LikeStrategy likeStrategy) {
        this.likeStrategy = likeStrategy;
    }

    @Override
    public void setNotificationStrategy(NotificationActionStrategy notificationStrategy) {
        this.notificationActionStrategy = notificationActionStrategy;
    }


    @Override
    public void like(Object object) {
        Users users = this.userService.getCurrentUser();
        this.likeStrategy.like(users, object);
        this.notificationActionStrategy.like(object);
    }

    @Override
    public void unlike(Users users, Object object) {
        this.notificationActionStrategy.like(object);
    }

    @Override
    public int countLikes(Object object) {
        return likeStrategy.countLikes(object);
    }
}
