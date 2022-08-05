package edu.sharif.twitter.view;

import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.view.data.DataManager;
import edu.sharif.twitter.view.item.TweetViewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class ProfileScreenController extends ScreenController {
    private User user = DataManager.getUser();
    private ArrayList<TweetViewController> tweetViews = new ArrayList<>();
    private ArrayList<Node> nodes = new ArrayList<>();
    @FXML
    private Label usernameLabel, postsLabel;
    @FXML
    private Button followersButton, followingButton;
    @FXML
    private VBox postVbox;

    @FXML
    public void initialize() throws IOException {
        initializeGridPane();

        usernameLabel.setText(user.getUsername());
        postsLabel.setText(user.getTweets().size() + " Posts");
        followersButton.setText(user.getFollowers().size() + " Followers");
        followingButton.setText(user.getFollowings().size() + " Following");

        for (int i = user.getTweets().size() - 1; i >= 0; i--) {
            Tweet tweet = user.getTweets().get(i);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/item/tweet-view.fxml"));
            Node node = loader.load();
            TweetViewController tweetViewController = loader.getController();
            tweetViewController.setTweet(tweet);
            nodes.add(node);
            tweetViews.add(tweetViewController);
            postVbox.getChildren().add(node);
        }
    }
}
