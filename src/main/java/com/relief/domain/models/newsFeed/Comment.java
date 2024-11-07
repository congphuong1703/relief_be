package com.relief.domain.models.newsFeed;

import com.relief.domain.enums.YesNo;
import com.relief.domain.models.BaseModel;
import com.relief.domain.models.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLRestriction("is_deleted = 0")
@SQLDelete(sql = "UPDATE comments SET deleted = 1 WHERE id=?")
public class Comment extends BaseModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column
    @UuidGenerator
    private UUID id;

    @Column
    private String message;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users users;

    @Column(name = "is_edited")
    @Enumerated(EnumType.ORDINAL)
    private YesNo isEdited = YesNo.NO;

    @Column(name = "is_deleted")
    @Enumerated(EnumType.ORDINAL)
    private YesNo isDeleted = YesNo.NO;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "parent_id")
    private List<Comment> replies;

}
