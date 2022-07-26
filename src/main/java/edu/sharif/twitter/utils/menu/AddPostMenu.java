package edu.sharif.twitter.utils.menu;

import edu.sharif.twitter.entity.PublicMessage;
import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.service.PublicMessageService;
import edu.sharif.twitter.service.TweetService;
import edu.sharif.twitter.service.UserService;
import edu.sharif.twitter.utils.ApplicationContext;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddPostMenu<T extends PublicMessage> extends Menu{
    private final User user;
    private final PublicMessageService<T> publicMessageService;
    public final UserService userService = ApplicationContext.getUserService();
    private PublicMessage repliedTo;
    private final Class<T> type;

    public AddPostMenu(User user, Class<T> type) {
        super(new String[]{"Add Text", "Send","Back"});
        this.user = user;
        this.type = type;
        publicMessageService = type == Tweet.class
                ? (PublicMessageService<T>) ApplicationContext.getTweetService()
                : (PublicMessageService<T>) ApplicationContext.getCommentService();
    }

    public AddPostMenu(User user, PublicMessage repliedTo, Class<T> type) {
        super(new String[]{"Add Text", "Send","Back"});
        this.user = user;
        this.repliedTo = repliedTo;
        this.type = type;
        publicMessageService = type == Tweet.class
                ? (PublicMessageService<T>) ApplicationContext.getTweetService()
                : (PublicMessageService<T>) ApplicationContext.getCommentService();
    }

    public void runMenu() {
        PublicMessage pm = publicMessageService.createPublicMessage(user, repliedTo);

        while (true) {
            print();
            switch (chooseOperation()) {
                case 1:
                    pm = publicMessageService.createPublicMessage(user, repliedTo);
                    break;
                case 2:
                    publicMessageService.addPublicMessage(type.cast(pm));
                    break;
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
        String[] texts = tweetTexts.toArray(new String[0]);
        Tweet tweet = new ShowUsersInformation<>(texts , tweets , true).runMenu();
        tweets.remove(tweet);
        return tweet;
    }
}
