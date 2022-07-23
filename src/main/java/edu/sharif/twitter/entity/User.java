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
@Table(name = User.TABLE_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity<Long> {
    public final static String TABLE_NAME = "user_table";


    private String username;
    private String password;

    @OneToOne(mappedBy = "user" ,cascade = CascadeType.ALL)
    private UserProfile userProfile = new UserProfile();

    @OneToMany(mappedBy = "user"  ,cascade = CascadeType.ALL)
    private List<Tweet> tweets = new ArrayList<>();

    @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "follow_pattern",
            joinColumns = @JoinColumn(name = "follower"),
            inverseJoinColumns =@JoinColumn(name = "following"))
    private List<User> followings = new ArrayList<>();

    @ManyToMany(mappedBy = "followings")
    private List<User> followers = new ArrayList<>();

    @Override
    public String toString() {
        return userProfile.getFirstName() + " " + userProfile.getLastName() + "\n"
                + username + "\n" + userProfile.getBio();
    }
}
