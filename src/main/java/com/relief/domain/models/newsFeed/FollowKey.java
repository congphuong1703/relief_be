package com.relief.domain.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowKey implements Serializable {

    @ManyToOne
    @JoinColumn(name = "follower_id", referencedColumnName = "id")
    private Users follower;
    @ManyToOne
    @JoinColumn(name = "followee_id", referencedColumnName = "id")
    private Users followee;

}
