package edu.sharif.twitter.utils.menu;

import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.entity.dto.SearchUserDto;
import edu.sharif.twitter.service.CommentService;
import edu.sharif.twitter.service.TweetService;
import edu.sharif.twitter.service.UserService;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.utils.input.Input;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class HomeMenu extends Menu{
    private final User user;

    private static final UserService userService = ApplicationContext.getUserService();
    private static final TweetService tweetService = ApplicationContext.getTweetService();
    private static final CommentService commentService = ApplicationContext.getCommentService();
    public HomeMenu(User user) {
        super(Arrays.asList("Profile","Tweet","Explore", "Chat", "Log out"));
        this.user = user;
        System.out.println("Welcome to your work bench... \n"
                +user.getUserProfile().getFirstName() +"  "
                + user.getUserProfile().getLastName());
    }


    public void runMenu() {
        while (true){
            print();
            switch (chooseOperation()) {
                case 1:
                    new ProfileMenu(user, userService, tweetService).runMenu();
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
}
