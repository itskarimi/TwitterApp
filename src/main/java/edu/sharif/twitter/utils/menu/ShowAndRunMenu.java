package edu.sharif.twitter.utils.menu;

import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.utils.ApplicationContext;

import java.util.Arrays;
import java.util.Objects;

public class ShowAndRunMenu extends Menu{

    public ShowAndRunMenu(){
        super(Arrays.asList("Log in", "Sign up", "Exit"));
    }

    public void runMenu() {
        while (true) {
            print();
            switch (chooseOperation()) {
                case 1:
                    /*User user = ApplicationContext.getUserService().login();
                    if(Objects.isNull(user)){
                        System.out.println("Your password or username is wrong...");
                        user = ApplicationContext.getUserService().login();
                    }
                    if (user.getIsDeleted()){
                        user.setIsDeleted(false);
                        ApplicationContext.getUserService().save(user);

                    }

                    new HomeMenu(user).runMenu();*/
                    break;
                case 2:
                    //ApplicationContext.getUserService().signUp();
                    break;
                case 3:
                    return;
            }
        }
    }
}
