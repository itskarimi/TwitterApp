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


    protected String username;
    protected String password;

    @OneToOne(mappedBy = "user" ,cascade = CascadeType.ALL)
    protected UserProfile userProfile = new UserProfile();

    @OneToMany(mappedBy = "user"  ,cascade = CascadeType.ALL)
    protected List<Tweet> tweets = new ArrayList<>();

    @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL)
    protected List<Comment> comments = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "follow_pattern",
            joinColumns = @JoinColumn(name = "follower"),
            inverseJoinColumns = @JoinColumn(name = "following"))
    protected List<User> followings = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "follow_pattern",
            joinColumns = @JoinColumn(name = "following"),
            inverseJoinColumns =@JoinColumn(name = "follower"))
    protected List<User> followers = new ArrayList<>();

    @OneToMany(mappedBy = "viewer", cascade = CascadeType.ALL)
    protected List<ViewProfile> profileActivities = new ArrayList<>();

    @OneToMany(mappedBy = "viewed", cascade = CascadeType.ALL)
    protected List<ViewProfile> profileViews = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    protected List<Message> messages = new ArrayList<>();

    @ManyToMany(mappedBy = "members")
    protected List<Chat> chats = new ArrayList<>();

    @ManyToMany(mappedBy = "admins")
    protected List<Group> adminChats = new ArrayList<>();

    @Override
    public String toString() {
        return userProfile.getFirstName() + " " + userProfile.getLastName() + "\n"
                + username + "\n" + userProfile.getBio();
    }
}
