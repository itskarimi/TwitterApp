package edu.sharif.twitter.entity;

import edu.sharif.twitter.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = Like.TABLE_NAME)
@Getter
@Setter
public class Like extends BaseEntity<Long> {
    public static final String TABLE_NAME = "like_table";
    public static final String CREATE_DATE_TIME = "create_date_time";

    @ManyToOne
    @JoinColumn(name = "public_message_id")
    private PublicMessage publicMessage;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = CREATE_DATE_TIME, nullable = false)
    protected LocalDateTime createDateTime;
}
