package com.relief.domain.services;

import com.relief.domain.designPatterns.PostLikeStrategy;
import com.relief.domain.models.newsFeed.Post;
import com.relief.domain.services.interfaces.AbstractBaseService;
import com.relief.domain.services.interfaces.LikeService;
import com.relief.domain.services.interfaces.NotificationService;
import com.relief.domain.services.interfaces.PostService;
import com.relief.infrastructure.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PostServiceImpl extends AbstractBaseService<Post, UUID> implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    protected JpaRepository<Post, UUID> getRepository() {
        return this.postRepository;
    }
    @Autowired
    private PostLikeStrategy postLikeStrategy;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private LikeService likeService;
    @Override
    public void like(String id) {
        this.likeService.setLikeStrategy(postLikeStrategy);
        this.likeService.like(id);
    }
}
