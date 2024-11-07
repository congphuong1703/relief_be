package com.relief.domain.services.interfaces;

import com.relief.domain.models.newsFeed.Comment;

import java.util.UUID;

public interface CommentService extends BaseService<Comment, UUID> {
    void like(String id);
}
