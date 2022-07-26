package edu.sharif.twitter.entity;

import edu.sharif.twitter.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "group_profile")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupProfile extends BaseEntity<Long> {
    @Column(name = "name")
    private String name;

    @Column(name = "desrcription")
    private String description;

    @OneToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;
}
