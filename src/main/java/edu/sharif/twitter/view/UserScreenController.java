package edu.sharif.twitter.view;

import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.view.data.DataManager;
import edu.sharif.twitter.view.show.TweetView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import lombok.Data;

import java.io.IOException;

public class UserScreenController extends Menu {
    private final User user = DataManager.getTargetUser();
    @FXML
    private Label usernameLabel, postsLabel;
    @FXML
    private Button directToUserButton, followButton, followersButton, followingButton;
    @FXML
    private VBox postVbox;

    private void initLabels() {
        usernameLabel.setText(user.getUsername());
        followButton.setText(user.getFollowers().contains(DataManager.getUser()) ? "Unfollow" : "Follow");
        postsLabel.setText(user.getTweets().size() + " Posts");
        followersButton.setText(user.getFollowers().size() + " Followers");
        followingButton.setText(user.getFollowings().size() + " Following");
    }

    @FXML
    public void initialize() throws IOException {
        initLabels();

        for (int i = user.getTweets().size() - 1; i >= 0; i--) {
            Tweet tweet = user.getTweets().get(i);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/show/tweetView.fxml"));
            Node node = loader.load();
            TweetView tweetView = loader.getController();
            tweetView.setTweet(tweet);
            postVbox.getChildren().add(node);
        }
    }

    @FXML
    public void onFollowButtonClick() throws IOException {
        ApplicationContext.getUserService().follow(DataManager.getUser(), user);
        initLabels();
    }

    @FXML
    public void onDirectToUserButtonClick() {

    }
}