package edu.sharif.twitter.service;

import edu.sharif.twitter.base.service.BaseEntityService;
import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;

public interface TweetService extends BaseEntityService<Tweet, Long> {

    void addTweet(User user);

    void editTweet(Tweet tweet);

    void showTweets(User user);

    void deleteById(Long Id);

}
