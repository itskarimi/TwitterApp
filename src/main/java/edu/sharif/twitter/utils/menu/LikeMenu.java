package edu.sharif.twitter.utils.menu;

import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.service.TweetService;
import edu.sharif.twitter.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LikeMenu extends Menu{
    private final User user;
    private final UserService userService;
    private final TweetService tweetService;

    public LikeMenu(User user, UserService userService , TweetService tweetService ){
        super(new String[]{"like","Back"});
        this.user = user;
        this.userService = userService;
        this.tweetService = tweetService;

    }

    public void runMenu() {
        while (true) {
            print();
            switch (chooseOperation()) {
                case 1:
                    Tweet tweet = addLike();
                    if (!Objects.isNull(tweet)) {
                        List<User> likes = tweet.getLikes();
                        boolean isLiked = isIn(user, likes);
                        if (isLiked){
                            tweet.getLikes().remove(user);
                            System.out.println("you disliked this tweet/before you disliked this tweet");

                        }
                        else {
                            tweet.getLikes().add(user);
                            System.out.println("you liked this tweet");
                        }
                        tweetService.save(tweet);
                    }
                    break;

                case 2:
                    return;

            }
        }
    }

    private boolean isIn(User user, List<User> likes) {
        for (User user1 : likes) {
            if (user1.equals(user))
                return true;
        }
        return false;
    }


    public Tweet addLike() {
        {
            List<List<Tweet>> tweets = new ArrayList<>();
            for (User showTweetAllOfUser : userService.showTweetAllOfUsers()) {
                tweets.add(showTweetAllOfUser.getTweets());
            }
            List<Tweet> tweetList = new ArrayList<>();
            for (List<Tweet> tweet : tweets) {
                tweetList.addAll(tweet);
            }

            List<String> texts = new ArrayList<>();
            for (Tweet tweet : tweetList) {
                texts.add(tweet.getText());
            }
            texts.add("Back");
            String[] textTweets = texts.toArray(new String[0]);
            System.out.println("Enter your tweet for add like/dislike :");
            return new ShowUsersInformation<Tweet>(textTweets, tweetList, true).runMenu();

        }
    }
}
