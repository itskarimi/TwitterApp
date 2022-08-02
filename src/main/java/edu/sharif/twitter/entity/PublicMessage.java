package edu.sharif.twitter.entity;

import edu.sharif.twitter.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "public_message")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "public_message_type")
@Getter
@Setter
public abstract class PublicMessage extends BaseEntity<Long> {

    public static final String CREATE_DATE_TIME = "create_date_time";
    public static final String LAST_UPDATE_DATE_TIME = "last_update_date_time";

    @Column
    protected String text;

    @Column(name = CREATE_DATE_TIME, nullable = false)
    protected LocalDateTime createDateTime;

    @Column(name = LAST_UPDATE_DATE_TIME, nullable = false)
    protected LocalDateTime lastUpdateDateTime;

    @OneToMany(mappedBy = "repliedTo", cascade = CascadeType.ALL)
    protected List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "publicMessage", cascade = CascadeType.ALL)
    protected List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "viewed", cascade = CascadeType.ALL)
    protected List<View> views = new ArrayList<>();
}
