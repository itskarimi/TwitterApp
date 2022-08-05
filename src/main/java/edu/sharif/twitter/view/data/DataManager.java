package edu.sharif.twitter.view.data;

import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;

public class DataManager {
    private static User user;
    private static Tweet tweet;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        DataManager.user = user;
    }

    public static Tweet getTweet() {
        return tweet;
    }

    public static void setTweet(Tweet tweet) {
        DataManager.tweet = tweet;
    }
}