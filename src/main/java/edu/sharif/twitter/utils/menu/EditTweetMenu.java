package edu.sharif.twitter.utils.menu;

import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.service.TweetService;
import edu.sharif.twitter.service.UserService;
import edu.sharif.twitter.utils.ApplicationContext;

public class EditTweetMenu extends Menu{
    private final Tweet tweet;
    private final TweetService tweetService;
    private final UserService userService = ApplicationContext.getUserService();
    private User user;

    public EditTweetMenu(Tweet tweet, TweetService tweetService, User user1) {
        super(new String[]{"Edit Text","Back"});
        this.tweetService = tweetService;
        this.tweet = tweet;
        this.user = user1;
    }

    public void runMenu() {
        while (true) {
            print();
            switch (chooseOperation()) {
                case 1:
                    //tweetService.editTweet(tweet);
                    userService.save(user);
                    break;
                case 2:
                    return;

            }
        }
    }
}
