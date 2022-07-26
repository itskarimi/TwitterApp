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
@Table(name = "message_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message extends BaseEntity<Long> {

    public static final String CREATE_DATE_TIME = "create_date_time";
    public static final String LAST_UPDATE_DATE_TIME = "last_update_date_time";

    String text;

    @Column(name = CREATE_DATE_TIME, nullable = false)
    protected LocalDateTime createDateTime;

    @Column(name = LAST_UPDATE_DATE_TIME, nullable = false)
    protected LocalDateTime lastUpdateDateTime;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    protected Chat chat;

    @ManyToOne
    @JoinColumn(name = "user_id")
    protected User user;

    @Column(nullable = false)
    protected Boolean isReply;

    @ManyToOne
    @JoinColumn
    protected Message origin;

    @OneToMany(mappedBy = "origin", cascade = CascadeType.ALL)
    protected List<Message> replies = new ArrayList<>();

    @Column(nullable = false)
    protected Boolean isForward;

    @Override
    public String toString() {
        String reply = "";
        if (this.getIsReply()) {
            reply = getOrigin().getUser().getUsername() + ": ";
            String replyText = getOrigin().getText();
            if (replyText.length() > 8)
                replyText = replyText.substring(0, 8) + "...";
            reply = reply + ": " + replyText + "\n" + "---";
        }
        return reply + user.getUsername() + ": " + text;
    }

}
