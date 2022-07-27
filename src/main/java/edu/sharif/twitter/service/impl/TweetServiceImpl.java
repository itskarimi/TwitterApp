package edu.sharif.twitter.service.impl;

import edu.sharif.twitter.entity.PublicMessage;
import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.repository.TweetRepository;
import edu.sharif.twitter.service.TweetService;
import edu.sharif.twitter.utils.input.Input;

import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;

public class TweetServiceImpl extends PublicMessageServiceImpl<Tweet>
        implements TweetService {

    public TweetServiceImpl(TweetRepository repository) {
        super(repository);
    }

    private final EntityTransaction transaction = repository.getEntityManger().getTransaction();

    @Override
    public Tweet createPublicMessage(User user, PublicMessage repliedTo) {
        Tweet tweet = new Tweet();

        tweet.setText(new Input(
                "Enter your text :",
                "Your text must be a maximum of 280 characters",
                "", null).getInputTextString());

        tweet.setCreateDateTime(LocalDateTime.now());
        tweet.setLastUpdateDateTime(LocalDateTime.now());
        tweet.setUser(user);
        return tweet;
    }

    @Override
    public void addPublicMessage(Tweet tweet) {
        tweet.getUser().getTweets().add(tweet);
        transaction.begin();
        repository.save(tweet);
        transaction.commit();
    }

    @Override
    public void editPublicMessage(Tweet tweet) {
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
    public void showPublicMessage(User user) {
        repository.showPublicMessage(user);
    }

    @Override
    public void deleteById(Long Id) {
        repository.deleteById(Id);
    }


}
