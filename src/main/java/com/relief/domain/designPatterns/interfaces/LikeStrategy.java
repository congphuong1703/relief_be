package com.relief.domain.designPatterns;

import java.util.UUID;

public interface LikeStrategy {

    void like(UUID userId, UUID objectId);
    int countLikes(UUID userId, UUID objectId);
}
