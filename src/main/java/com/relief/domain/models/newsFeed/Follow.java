package com.relief.domain.models.newsFeed;

import com.relief.domain.models.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "follows")
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(FollowKey.class)
public class Follow implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @JoinColumn(name = "follower_id", referencedColumnName = "id")
    private Users follower;

    @Id
    @ManyToOne
    @JoinColumn(name = "follower_id", referencedColumnName = "id")
    private Users followee;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            setCreatedAt(LocalDateTime.now());
        }
    }
}
