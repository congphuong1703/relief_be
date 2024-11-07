package com.relief.domain.designPatterns;

import com.relief.domain.designPatterns.interfaces.NotificationActionStrategy;
import org.springframework.stereotype.Service;

@Service
public class NotificationLikeStrategy implements NotificationActionStrategy {
    @Override
    public void like(Object object) {

    }

    @Override
    public void comment(Object object) {

    }
}
