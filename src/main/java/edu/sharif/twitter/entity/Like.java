package edu.sharif.twitter.entity;

import edu.sharif.twitter.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = Like.TABLE_NAME)
public class Like extends BaseEntity<Long> {
    public static final String TABLE_NAME = "like_table";
}
