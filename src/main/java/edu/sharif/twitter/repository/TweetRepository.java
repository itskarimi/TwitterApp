package edu.sharif.twitter.repository;

import edu.sharif.twitter.base.repository.BaseEntityRepository;
import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;

public interface TweetRepository extends BaseEntityRepository<Tweet, Long> {
    void showTweets(User user);

    void deleteById(Long id);
}
