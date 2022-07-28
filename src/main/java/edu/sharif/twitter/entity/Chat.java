package edu.sharif.twitter.entity;

import antlr.debug.MessageAdapter;
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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "chat_type")
@Getter
@Setter
public class Chat extends BaseEntity<Long> {

    public static final String CREATE_DATE_TIME = "create_date_time";
    public static final String LAST_UPDATE_DATE_TIME = "last_update_date_time";

    @Column(name = CREATE_DATE_TIME)
    protected LocalDateTime createDateTime;

    @Column(name = LAST_UPDATE_DATE_TIME)
    protected LocalDateTime lastUpdateDateTime;

    @ManyToMany
    @JoinTable(name = "user_chat")
    protected List<User> members = new ArrayList<>();

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    protected List<Message> messages = new ArrayList<>();

    @Override
    public String toString() {
        return "Chat{" +
                "createDateTime=" + createDateTime +
                ", lastUpdateDateTime=" + lastUpdateDateTime +
                ", members=" + members +
                ", messages=" + messages +
                '}';
    }
}
