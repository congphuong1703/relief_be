package com.relief.domain.models.newsFeed;

import com.relief.domain.models.BaseModel;
import com.relief.domain.models.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLRestriction("is_deleted = 0")
public class Post extends BaseModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column
    @UuidGenerator
    private UUID Id;

    @Column
    private String caption;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Users author;

    @OneToMany(mappedBy = "post")
    private Set<PostDetach> postDetaches;

}
