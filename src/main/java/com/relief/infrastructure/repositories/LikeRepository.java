package com.relief.infrastructure.repositories;

import com.relief.domain.models.newsFeed.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {


    Optional<Like> findByCommentAndUsers(UUID commentId, UUID userId);

    Optional<Like> findByPostAndUsers(UUID postId, UUID userId);

    int countByComment(UUID commentId);
    int countByPost(UUID postId);
}
