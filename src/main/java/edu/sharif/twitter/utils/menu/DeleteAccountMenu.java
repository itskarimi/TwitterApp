package edu.sharif.twitter.utils.menu;

import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.service.UserService;

import java.util.Arrays;

public class DeleteAccountMenu extends Menu{
    private final User user;
    private final UserService userService;

    public DeleteAccountMenu(User user, UserService userService) {
        super(Arrays.asList("Temporary delete","Permanent delete","Back"));
        this.user = user;
        this.userService = userService;
    }

    public void runMenu() {
        while (true) {
            print();
            switch (chooseOperation()) {
                case 1:
                    user.setIsDeleted(true);
                    user.getUserProfile().setIsDeleted(true);
                    userService.save(user);
                    break;
                case 2:
                    System.out.println("Are you sure ?");
                    boolean check = new CheckMenu().runMenu();
                    if (check){
                        userService.delete(user);
                        System.out.println("Your Account is deleted permanent...");
                        return;
                    }
                    else
                        break;
                case 3:
                    return;


            }
        }

    }
}
