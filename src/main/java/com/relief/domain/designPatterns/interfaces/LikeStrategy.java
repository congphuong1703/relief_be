package com.relief.domain.designPatterns.interfaces;

import com.relief.domain.models.Users;

public interface LikeStrategy{

    void like(Users users, Object object);

    void unlike(Users users, Object object);

    int countLikes(Object object);
}
