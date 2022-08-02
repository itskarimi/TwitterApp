package edu.sharif.twitter.entity;

import edu.sharif.twitter.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = ViewProfile.TABLE_NAME)
@Getter
@Setter
public class ViewProfile extends BaseEntity<Long> {
    public static final String TABLE_NAME = "view_profile";
    public static final String CREATE_DATE_TIME = "create_date_time";
    public static final String VIEWER = "viewer_id";
    public static final String VIEWED = "viewed_id";

    @Column(name = CREATE_DATE_TIME)
    private LocalDateTime createDateTime;

    @ManyToOne
    @JoinColumn(name = VIEWER)
    private User viewer;

    @ManyToOne
    @JoinColumn(name = VIEWED)
    private User viewed;
}
