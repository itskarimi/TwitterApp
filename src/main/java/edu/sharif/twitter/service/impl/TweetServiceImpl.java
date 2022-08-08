package edu.sharif.twitter.service.impl;

import edu.sharif.twitter.entity.Comment;
import edu.sharif.twitter.entity.PublicMessage;
import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.repository.TweetRepository;
import edu.sharif.twitter.service.TweetService;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.utils.input.Input;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;

import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TweetServiceImpl extends PublicMessageServiceImpl<Tweet>
        implements TweetService {

    public TweetServiceImpl(TweetRepository repository) {
        super(repository);
    }

    private final EntityTransaction transaction = repository.getEntityManger().getTransaction();

    @Override
    public Tweet createPublicMessage(User user, PublicMessage repliedTo, String text) {
        Tweet tweet = new Tweet();

        tweet.setText(text);

        tweet.setCreateDateTime(LocalDateTime.now());
        tweet.setLastUpdateDateTime(LocalDateTime.now());
        tweet.setUser(user);
        tweet.getUser().getTweets().add(tweet);

        return tweet;
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

    @Override
    public List<Comment> getComments(Tweet publicMessage) {
        ArrayList<Comment> comments = new ArrayList<>();
        for (Comment comment : publicMessage.getComments()) {
            comments.addAll(ApplicationContext.getCommentService().getComments(comment));
        }
        System.out.println(comments.size());
        ArrayList<Comment> sorted = new ArrayList<>();
        for (int i = 0; i < comments.size(); i++) {
            int s = 0;
            for (int j = 0; j < comments.size(); j++)
                if (comments.get(j) != null && (comments.get(s) == null ||
                        comments.get(j).getCreateDateTime().isBefore(comments.get(s).getCreateDateTime())))
                    s = j;
            System.out.println(i + "\t" + comments.get(s));
            sorted.add(comments.get(s));
            comments.set(s, null);
        }
        return sorted;
    }
}
