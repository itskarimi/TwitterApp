package edu.sharif.twitter.service.impl;

import edu.sharif.twitter.base.service.impl.BaseEntityServiceImpl;
import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.repository.TweetRepository;
import edu.sharif.twitter.service.TweetService;
import edu.sharif.twitter.utils.input.Input;

import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;

public class TweetServiceImpl extends BaseEntityServiceImpl<Tweet, Long, TweetRepository>
        implements TweetService {

    public TweetServiceImpl(TweetRepository repository) {
        super(repository);
    }

    private final EntityTransaction transaction = repository.getEntityManger().getTransaction();

    @Override
    public void addTweet(User user) {
        Tweet tweet = new Tweet();

        tweet.setText(new Input(
                "Enter your text :",
                "Your text must be a maximum of 280 characters",
                "", null).getInputTextString());

        tweet.setCreateDateTime(LocalDateTime.now());
        tweet.setLastUpdateDateTime(LocalDateTime.now());
        tweet.setUser(user);
        user.getTweets().add(tweet);
        transaction.begin();
        repository.save(tweet);
        transaction.commit();

    }

    @Override
    public void editTweet(Tweet tweet) {
        tweet.setText(new Input(
                "Enter your text :",
                "Your text must be a maximum of 280 characters",
                "", null).getInputTextString());
        tweet.setLastUpdateDateTime(LocalDateTime.now());
        transaction.begin();
        repository.save(tweet);
        transaction.commit();
    }

    @Override
    public void showTweets(User user) {
        repository.showTweets(user);
    }

    @Override
    public void deleteById(Long Id) {
        repository.deleteById(Id);
    }


}
