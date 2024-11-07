package com.relief.domain.designPatterns;

import com.relief.domain.designPatterns.interfaces.LikeStrategy;
import com.relief.domain.models.Users;
import com.relief.domain.models.newsFeed.Comment;
import com.relief.domain.models.newsFeed.Like;
import com.relief.infrastructure.repositories.CommentRepository;
import com.relief.infrastructure.repositories.LikeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentLikeStrategy implements LikeStrategy {

    private final LikeRepository likeRepository;

    private final CommentRepository commentRepository;

    public CommentLikeStrategy(LikeRepository likeRepository, CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
    }

    @Override
    public void like(Users users, Object object) {
        Comment comment = (Comment) object;
        this.likeRepository.save(Like.builder()
                .users(users)
                .comment(comment)
                .build());
    }

    @Override
    public void unlike(Users users, Object object) {
        Comment comment = (Comment) object;
        Optional<Like> like = this.likeRepository.findByCommentAndUsers(comment.getId(), users.getId());
        like.ifPresent(this.likeRepository::delete);
    }

    @Override
    public int countLikes(Object object) {
        Comment comment = (Comment) object;
        return this.likeRepository.countByComment(comment.getId());
    }
}
