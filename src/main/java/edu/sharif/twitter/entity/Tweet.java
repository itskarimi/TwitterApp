package edu.sharif.twitter.entity;

import edu.sharif.twitter.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = Tweet.TABLE_NAME)
@NamedEntityGraph(name = "twit_likes",
        attributeNodes = {
                @NamedAttributeNode("likes")
        }
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tweet extends BaseEntity<Long> {
    public static final String TABLE_NAME = "tweet_table";
    public static final String CREATE_DATE_TIME = "create_date_time";
    public static final String LAST_UPDATE_DATE_TIME = "last_update_date_time";

    private String text;

    @Column(name = CREATE_DATE_TIME, nullable = false)
    private LocalDateTime createDateTime;

    @Column(name = LAST_UPDATE_DATE_TIME, nullable = false)
    private LocalDateTime lastUpdateDateTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "tweet" , cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "twit_like")
    private List<User> likes = new ArrayList<>();

    @Override
    public String toString() {
        String dashLine = "----------";
        StringBuilder stringBuilder = new StringBuilder("@" + getUser().getUsername());
        stringBuilder.append(dashLine).append(dashLine).append(dashLine);

        stringBuilder.append(text).append("\n\n");
        stringBuilder.append(dashLine).append(dashLine).append(dashLine);
        stringBuilder.append("likes: ").append(likes.size()).append("\n");
        stringBuilder.append("comments: ").append(comments.size()).append("\n");
        stringBuilder.append(dashLine).append(dashLine).append(dashLine);
        for (Comment comment : comments) {
            stringBuilder.append(comment.toString());
            stringBuilder.append("\n\n");
            stringBuilder.append(dashLine).append(dashLine).append(dashLine);

        }
        stringBuilder.append("\n\n");
        return stringBuilder.toString();
    }
}
