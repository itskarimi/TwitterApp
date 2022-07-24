package edu.sharif.twitter.entity;

import edu.sharif.twitter.base.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = Like.TABLE_NAME)
public class Like extends BaseEntity<Long> {
    public static final String TABLE_NAME = "like_table";

    @ManyToOne
    @JoinColumn(name = "public_message_id")
    private PublicMessage publicMessage;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
