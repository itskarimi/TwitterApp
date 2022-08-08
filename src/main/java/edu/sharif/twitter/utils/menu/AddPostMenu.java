package edu.sharif.twitter.utils.menu;

import edu.sharif.twitter.entity.PublicMessage;
import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.service.PublicMessageService;
import edu.sharif.twitter.service.TweetService;
import edu.sharif.twitter.service.UserService;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.utils.input.Input;
import javafx.scene.control.TextField;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class AddPostMenu<T extends PublicMessage> extends Menu{
    private final User user;
    private final PublicMessageService<T> publicMessageService;
    public final UserService userService = ApplicationContext.getUserService();
    private PublicMessage repliedTo;
    private final Class<T> type;

    public AddPostMenu(User user, Class<T> type) {
        super(Arrays.asList("Change Text", "Send","Back"));
        this.user = user;
        this.type = type;
        publicMessageService = type == Tweet.class
                ? (PublicMessageService<T>) ApplicationContext.getTweetService()
                : (PublicMessageService<T>) ApplicationContext.getCommentService();
    }

    public AddPostMenu(User user, PublicMessage repliedTo, Class<T> type) {
        super(Arrays.asList("Change Text", "Send","Back"));
        this.user = user;
        this.repliedTo = repliedTo;
        this.type = type;
        publicMessageService = type == Tweet.class
                ? (PublicMessageService<T>) ApplicationContext.getTweetService()
                : (PublicMessageService<T>) ApplicationContext.getCommentService();
    }

    public void runMenu() {
        PublicMessage pm = publicMessageService.createPublicMessage(user, repliedTo,
                new Input(
                    "Enter your text :",
                    "Your text must be a maximum of 280 characters",
                    "", null).getInputTextString());

        while (true) {
            print();
            switch (chooseOperation()) {
                case 1:
                    pm = publicMessageService.createPublicMessage(user, repliedTo,
                            new Input(
                                "Enter your text :",
                                "Your text must be a maximum of 280 characters",
                                "", null).getInputTextString());
                    break;
                case 2:
                    publicMessageService.save(type.cast(pm));
                    return;
                case 3:
                    return;
            }
        }
    }

    private Tweet getTweetForEditOrDelete() {
        User user1 = userService.findById(user.getId());
        List<Tweet> tweets = user1.getTweets();
        List<String> tweetTexts = new ArrayList<>();
        for (Tweet tweet : tweets) {
            tweetTexts.add(tweet.getText());
        }
        tweetTexts.add("Back");
        Tweet tweet = new ShowUsersInformation<>(tweetTexts , tweets , true).runMenu();
        tweets.remove(tweet);
        return tweet;
    }
}
