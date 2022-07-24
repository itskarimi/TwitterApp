package edu.sharif.twitter.utils.menu;

import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.service.TweetService;
import edu.sharif.twitter.service.UserService;
import edu.sharif.twitter.utils.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TweetingMenu extends Menu{
    private final User user;
    private final TweetService tweetService;
    public final UserService userService = ApplicationContext.getUserService();
    public TweetingMenu(User user, TweetService tweetService) {
        super(new String[]{"Add Tweet","Show My Tweets","Edit Tweet","Delete Tweet","Back", "show followings tweets"});
        this.user = user;
        this.tweetService = tweetService;
    }


    public void runMenu() {
        User user1 = userService.findById(user.getId());
        while (true) {
            print();
            switch (chooseOperation()) {
                case 1:
                    tweetService.addTweet(user);
                    break;
                case 2:
//                    tweetService.showTweets(user);
//                    List<Tweet> tweets = user.getTweets();
//                    for (Tweet tweet : tweets) {
//                        System.out.println(tweet);
//                    }
//                    user1.getTweets().forEach(System.out::println);
                    for (Tweet tweet : user1.getTweets()) {
                        System.out.println(tweet);
                    }

                    break;
                case 3:
                    System.out.println("choose option for edit...");
                    Tweet tweet = getTweetForEditOrDelete();
                    new EditTweetMenu(tweet , tweetService , user1).runMenu();
                    break;

                case 4:
                    System.out.println("choose option for delete...");
                    Tweet deleteTweet = getTweetForEditOrDelete();
//                    tweetService.deleteById(deleteTweet.getId());
//                    user.getTweets().remove(deleteTweet);

//                    Tweet byId = tweetService.findById(deleteTweet.getId());
                    if (!Objects.isNull(deleteTweet))
                        tweetService.delete(deleteTweet);

                    break;
                case 5:
                    return;
                case 6:
                    List<Tweet> tweets = userService.showFollowingsTweets(user);
                    for (Tweet tweet1 : tweets)
                        System.out.println(tweet1);

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
