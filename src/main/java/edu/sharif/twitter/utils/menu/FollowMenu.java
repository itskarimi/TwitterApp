package edu.sharif.twitter.utils.menu;

import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.service.UserService;
import edu.sharif.twitter.utils.ApplicationContext;

public class FollowMenu extends Menu{

    public final User user;
    public final UserService userService = ApplicationContext.getUserService();

    public FollowMenu(User user) {
        super(new String[] {"follow", "unfollow", "show followers", "show followings", "BACK"});
        this.user = user;
    }

    public void runMenu() {
        while(true) {
            print();
            switch (chooseOperation()) {
                case 1:
                    userService.follow(user);
                    userService.save(user);
                    break;
                case 2:
                    userService.unfollow(user);
                    userService.save(user);
                    break;
                case 3:
                    for (User user1 : user.getFollowers())
                        System.out.println(user1.getUsername());
                    break;
                case 4:
                    for (User user1 : user.getFollowings())
                        System.out.println(user1.getUsername());
                    break;
                case 5:
                    return;
            }
        }
    }
}
