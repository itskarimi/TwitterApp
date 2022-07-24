package edu.sharif.twitter.entity;

import edu.sharif.twitter.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DM_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DM extends BaseEntity<Long> {

    @ManyToOne
    @JoinColumn(name = "user1")
    private User user1;

    @ManyToOne
    @JoinColumn(name = "user2")
    private User user2;

    @OneToMany(mappedBy = "dm")
    protected List<Message> messages = new ArrayList<>();
}
