package edu.sharif.twitter.entity;

import edu.sharif.twitter.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = ViewProfile.TABLE_NAME)
public class ViewProfile extends BaseEntity<Long> {
    public static final String TABLE_NAME = "view_profile";
    public static final String VIEW_TABLE = "view_date";
    public static final String VIEWER = "viewer_id";
    public static final String VIEWED = "viewed_id";

    @Column(name = VIEW_TABLE)
    private LocalDate localDate;

    @ManyToOne
    @JoinColumn(name = VIEWER)
    private User viewer;

    @ManyToOne
    @JoinColumn(name = VIEWED)
    private User viewed;
}
