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

@Entity(name = "tweet")
@DiscriminatorValue("tweet")
@Getter
@Setter
@AllArgsConstructor
public class Tweet extends PublicMessage {

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
