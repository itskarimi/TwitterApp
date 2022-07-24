package edu.sharif.twitter.entity;

import edu.sharif.twitter.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "comment")
@DiscriminatorValue("comment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends PublicMessage {
    @ManyToOne
    @JoinColumn(name = "public_message_id")
    private PublicMessage repliedTo;

    @Override
    public String toString() {
        return "@" + user.getUsername() + ":\n" +
                text + "\n";
    }
}
