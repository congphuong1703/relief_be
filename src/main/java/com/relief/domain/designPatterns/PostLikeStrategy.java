package com.relief.domain.designPatterns;

import com.relief.domain.designPatterns.interfaces.LikeStrategy;
import com.relief.domain.models.Users;
import com.relief.domain.models.newsFeed.Like;
import com.relief.domain.models.newsFeed.Post;
import com.relief.infrastructure.repositories.LikeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostLikeStrategy implements LikeStrategy {

    private final LikeRepository likeRepository;

    public PostLikeStrategy(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    public void like(Users users, Object object) {
        Post post = (Post) object;
        Optional<Like> like = this.likeRepository.findByPostAndUsers(post.getId(), users.getId());
        if (like.isEmpty()) {
            this.likeRepository.save(Like.builder()
                    .users(users)
                    .post(post)
                    .build());
        }
    }

    @Override
    public void unlike(Users users, Object object) {
        Post post = (Post) object;
        Optional<Like> like = this.likeRepository.findByPostAndUsers(post.getId(), users.getId());
        like.ifPresent(this.likeRepository::delete);
    }

    @Override
    public int countLikes(Object object) {
        Post post = (Post) object;
        return this.likeRepository.countByPost(post.getId());
    }
}
