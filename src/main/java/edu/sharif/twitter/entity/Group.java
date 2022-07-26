package edu.sharif.twitter.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("group")
@Getter
@Setter
public class Group extends Chat {

    @OneToOne(mappedBy = "group", cascade = CascadeType.ALL)
    private GroupProfile groupProfile = new GroupProfile();

    @ManyToMany
    @JoinTable(name = "group_admin")
    private List<User> admins = new ArrayList<>();

    @Override
    public String toString() {
        return getGroupProfile().getName();
    }
}
