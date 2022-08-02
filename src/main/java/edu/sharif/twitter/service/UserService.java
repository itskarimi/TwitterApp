package edu.sharif.twitter.service;

import edu.sharif.twitter.base.service.BaseEntityService;
import edu.sharif.twitter.entity.PublicMessage;
import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.entity.dto.SearchUserDto;

import java.util.List;

public interface UserService extends BaseEntityService<User, Long> {
    User login();

    void signUp();

    User editUsername(User user);

    List<User> showTweetAllOfUsers();

    User findByUsername(SearchUserDto searchUserDto);

    void follow(User user, User following);

    String showProfile(User user);

    List<Tweet> showFollowingsTweets(User user);

    void showStats(User user);
}
