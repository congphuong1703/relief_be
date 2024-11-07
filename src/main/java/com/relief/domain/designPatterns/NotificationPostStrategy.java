package com.relief.domain.designPatterns;

import com.relief.domain.designPatterns.interfaces.NotificationActionStrategy;
import com.relief.domain.models.newsFeed.Post;
import org.springframework.stereotype.Service;

@Service
public class NotificationPostStrategy implements NotificationActionStrategy {



    @Override
    public void like(Object object) {
        Post post = (Post) object;

    }

    @Override
    public void comment(Object object) {

    }
}
