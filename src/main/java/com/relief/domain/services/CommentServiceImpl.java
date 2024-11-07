package com.relief.domain.services;

import com.relief.domain.designPatterns.CommentLikeStrategy;
import com.relief.domain.models.newsFeed.Comment;
import com.relief.domain.services.interfaces.AbstractBaseService;
import com.relief.domain.services.interfaces.CommentService;
import com.relief.domain.services.interfaces.LikeService;
import com.relief.infrastructure.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommentServiceImpl extends AbstractBaseService<Comment, UUID> implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    protected JpaRepository<Comment, UUID> getRepository() {
        return this.commentRepository;
    }

    @Autowired
    private CommentLikeStrategy commentLikeStrategy;

    @Autowired
    private LikeService likeService;
    @Override
    public void like(String id) {
        this.likeService.setLikeStrategy(commentLikeStrategy);
        this.likeService.like(id);
    }
}
