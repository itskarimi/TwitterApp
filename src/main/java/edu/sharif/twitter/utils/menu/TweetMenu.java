package edu.sharif.twitter.utils.menu;


import edu.sharif.twitter.entity.Comment;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.entity.Tweet;

public class TweetMenu extends Menu  {
    private final Tweet tweet;
    private final User user;
    public TweetMenu(User user, Tweet tweet) {
        super(new String[]{"Like", "Comment", "Back"});
        this.user = user;
        this.tweet = tweet;
    }


    public void runMenu() {
        while (true){
            print();
            switch (chooseOperation()) {
                case 1:

                    break;
                case 2:
                    break;
                case 3:
                    return;


            }
        }
    }
}
