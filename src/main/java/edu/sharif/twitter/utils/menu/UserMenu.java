package edu.sharif.twitter.utils.menu;

import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.service.UserService;

public class UserMenu extends Menu {
    private final User user;
    public UserMenu(User user) {
        super(new String[]{"Show profile","Show tweets", "Back"});
        this.user = user;
    }


    public void runMenu() {
        while (true){
            print();
            switch (chooseOperation()) {
                case 1:
                    System.out.println(user.getUserProfile());
                    break;
                case 2:
                    System.out.println(user.getTweets());
                    break;
                case 3:
                    return;


            }
        }
    }
}
