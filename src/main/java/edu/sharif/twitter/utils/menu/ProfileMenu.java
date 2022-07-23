package edu.sharif.twitter.utils.menu;

import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.entity.dto.SearchUserDto;
import edu.sharif.twitter.service.CommentService;
import edu.sharif.twitter.service.TweetService;
import edu.sharif.twitter.service.UserService;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.utils.input.Input;

import java.util.List;
import java.util.Objects;

public class ProfileMenu extends Menu{
    private final User user;

    private static final UserService userService = ApplicationContext.getUserService();
    private static final TweetService tweetService = ApplicationContext.getTweetService();
    private static final CommentService commentService = ApplicationContext.getCommentService();
    public ProfileMenu(User user) {
        super(new String[]{"Edit Profile","Delete Account","Tweet","Comment","like","Show Tweet Of All Users","Explore","Log out"});
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
                    new EditInformationUserMenu(user, userService).runMenu();
                    break;
                case 2:
                    new DeleteAccountMenu(user , userService).runMenu();
                    break;
                case 3:
                    new TweetMenu(user , tweetService).runMenu();
                    break;
                case 4:
                    new CommentMenu(user , commentService  , userService).runMenu();
                    break;
                case 5:
                    new LikeMenu(user , userService , tweetService).runMenu();
                case 6:
                    List<User> users = userService.showTweetAllOfUsers();
                    for (User user1 : users) {
                        System.out.println(user1.getTweets());
                    }
                    break;
                case 7:
                    search();
                    break;
                case 8:
                    return;


            }
        }
    }

    public void search() {
        String username = new Input("Enter your username :").getInputString();
        SearchUserDto search = new SearchUserDto(username);
        User user = userService.findByUsername(search);
        if (Objects.isNull(user))
            System.out.println("User not found...");
        else
            System.out.println(user);
    }


}
