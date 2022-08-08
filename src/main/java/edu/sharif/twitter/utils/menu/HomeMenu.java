package edu.sharif.twitter.utils.menu;

import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.entity.dto.SearchUserDto;
import edu.sharif.twitter.service.CommentService;
import edu.sharif.twitter.service.TweetService;
import edu.sharif.twitter.service.UserService;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.utils.input.Input;

import java.io.IOException;
import java.util.*;

public class HomeMenu extends Menu{
    private final User user;

    private static final UserService userService = ApplicationContext.getUserService();
    private static final TweetService tweetService = ApplicationContext.getTweetService();
    private static final CommentService commentService = ApplicationContext.getCommentService();
    public HomeMenu(User user) {
        super(Arrays.asList("Profile","Tweet","Explore", "Chat", "Home", "Log out"));
        this.user = user;
        System.out.println("Welcome to your work bench... \n"
                +user.getUserProfile().getFirstName() +"  "
                + user.getUserProfile().getLastName());
    }


    public void runMenu() throws IOException {
        while (true){
            print();
            switch (chooseOperation()) {
                case 1:
                    if (new ProfileMenu(user, userService, tweetService).runMenu()) {
                        return;
                    }
                    break;
                case 2:
                    new AddPostMenu<>(user, Tweet.class).runMenu();
                    break;
                case 3:
                    User receiver = search();
                    if (receiver != null)
                        new ChatMenu(user, receiver).runMenu();
                    break;
                case 4:
                    new ShowChatsMenu(user).runMenu();
                    break;
                case 5:
                    new SelectMenu<>(user, getHomeTweets()).runMenu();
                    return;
                case 6:
                    return;
            }
        }
    }
    public User search() {
        String username = new Input("Enter your username :").getInputString();
        SearchUserDto search = new SearchUserDto(username);
        User user = userService.findByUsername(search);
        if (Objects.isNull(user)) {
            System.out.println("User not found...");
            return null;
        }
        else
            System.out.println(user);
        return user;
    }

    private List<Tweet> getHomeTweets() {
        List<Tweet> tweets = new ArrayList<>(user.getTweets());
        for (User u : user.getFollowings()) {
            tweets.addAll(u.getTweets());
        }
        tweets.sort(Comparator.comparing(Tweet::getCreateDateTime));
        Collections.reverse(tweets);
        return tweets;
    }
}
