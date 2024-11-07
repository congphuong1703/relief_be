package com.relief.domain.services.interfaces;

import com.relief.domain.models.newsFeed.Post;

import java.util.UUID;

public interface PostService extends BaseService<Post, UUID> {
    void like(String id);
}
