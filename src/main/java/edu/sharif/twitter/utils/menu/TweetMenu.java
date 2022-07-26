package edu.sharif.twitter.utils.menu;


import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.entity.Tweet;

public class TweetMenu extends Menu  {
    private final Tweet tweet;
    public TweetMenu(Tweet tweet) {
        super(new String[]{"Like", "Comment", "Back"});
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
