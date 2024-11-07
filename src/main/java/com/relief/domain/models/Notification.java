package com.relief.domain.models;

import com.relief.domain.enums.ActionType;
import com.relief.domain.enums.ChanelType;
import com.relief.domain.enums.NotificationStatus;
import com.relief.domain.models.newsFeed.Comment;
import com.relief.domain.models.newsFeed.Post;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "notification")
public class Notification extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "body")
    private String body;

    @Column(name = "title")
    private String title;

    @Column(name = "brief")
    private String brief;

    @Column(name = "chanel")
    @Enumerated(EnumType.STRING)
    private ChanelType chanel;
    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private Users sender;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "comment_id", referencedColumnName = "id")
    private Comment comment;

    @Column
    private String url;

    @Basic(optional = false)
    @Column(name = "action_type")
    @Enumerated(EnumType.STRING)
    private ActionType actionType;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private NotificationStatus status = NotificationStatus.PENDING;

    @ManyToOne
    @JoinColumn(name = "recipient_id", referencedColumnName = "id")
    private Users recipient;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "notification_subjects", joinColumns = @JoinColumn(name = "notification_id"), inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private Set<Users> subjects;
}
