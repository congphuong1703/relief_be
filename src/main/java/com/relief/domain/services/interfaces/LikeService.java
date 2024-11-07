package com.relief.domain.services.interfaces;

import com.relief.domain.designPatterns.interfaces.LikeStrategy;
import com.relief.domain.designPatterns.interfaces.NotificationActionStrategy;
import com.relief.domain.models.Users;
import com.relief.domain.models.newsFeed.Like;

public interface LikeService extends BaseService<Like, Long> {

    void setLikeStrategy(LikeStrategy likeStrategy);

    void setNotificationStrategy(NotificationActionStrategy notificationStrategy);

    void like(Object object);
    void unlike(Users users, Object object);
    int countLikes(Object object);
}
