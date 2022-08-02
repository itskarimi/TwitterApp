package edu.sharif.twitter.utils.menu;

import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.service.UserService;
import edu.sharif.twitter.service.ViewProfileService;
import edu.sharif.twitter.utils.ApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;


public class UserMenu extends Menu {
    private static final UserService userService = ApplicationContext.getUserService();
    private final User user;
    private final User targetUser;
    private final ViewProfileService viewProfileService = ApplicationContext.getViewProfileService();
    public UserMenu(User user, User targetUser) {
        super(new ArrayList<>(Arrays.asList("Show profile", "Show tweets", "Follow", "Back")));
        this.user = user;
        this.targetUser = targetUser;
    }


    public void runMenu() {
        while (true){
            if (user.getFollowings().contains(targetUser)) {
                items.set(2, "Unfollow");
            }
            else {
                items.set(2, "Follow");
            }
            print();
            switch (chooseOperation()) {
                case 1:
                    System.out.println(targetUser.getUserProfile());
                    viewProfileService.addViewProfile(user, targetUser);
                    break;
                case 2:
                    new SelectMenu<>(user, targetUser.getTweets()).runMenu();
                    break;
                case 3:
                    userService.follow(user, targetUser);
                    break;
                case 4:
                    return;


            }
        }
    }
}
