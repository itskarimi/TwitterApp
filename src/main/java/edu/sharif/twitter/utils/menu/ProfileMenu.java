package edu.sharif.twitter.utils.menu;

import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.entity.dto.SearchUserDto;
import edu.sharif.twitter.service.CommentService;
import edu.sharif.twitter.service.TweetService;
import edu.sharif.twitter.service.UserService;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.utils.input.Input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ProfileMenu extends Menu{
    private final User user;
    private final UserService userService;
    private final TweetService tweetService;
    public ProfileMenu(User user, UserService userService, TweetService tweetService) {
        super(new ArrayList<>(Arrays.asList("Show profile","Edit profile", "Followers", "Following", "Show tweets", "Delete Account", "Back")));
        this.user = user;
        this.userService = userService;
        this.tweetService = tweetService;
        if (user.getIsBusiness()) {
            items.add("Show stats");
        }
    }


    public boolean runMenu() {
        while (true){
            print();
            switch (chooseOperation()) {
                case 1:
                    System.out.println(user.getUserProfile());
                    break;
                case 2:
                    new EditInformationUserMenu(user, userService).runMenu();
                    break;
                case 3:
                    new SelectMenu<>(user, user.getFollowers()).runMenu();
                    break;
                case 4:
                    new SelectMenu<>(user, user.getFollowings()).runMenu();
                    break;
                case 5:
                    new SelectMenu<>(user, user.getTweets()).runMenu();
                    break;
                case 6:
                    if (new DeleteAccountMenu(user , userService).runMenu()) {
                        return true;
                    }
                    break;
                case 7:
                    return false;
                case 8:
                    userService.showStats(user);
                    break;
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
