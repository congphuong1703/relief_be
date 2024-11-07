package com.relief.domain.models.newsFeed;

import com.relief.domain.enums.DetachType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "post_detaches")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDetach implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "commnet_id", referencedColumnName = "id")
    private Comment comment;

    @Column
    private String path;

    @Column
    @Enumerated(EnumType.STRING)
    private DetachType detachType;
}
