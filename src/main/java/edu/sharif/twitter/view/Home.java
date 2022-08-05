package edu.sharif.twitter.view;

import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.view.data.DataManager;
import edu.sharif.twitter.view.show.TweetView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Home extends Menu{

    List<edu.sharif.twitter.entity.Tweet> tweets = new ArrayList<>();

    @FXML
    private Label homeTitle;
    @FXML
    private VBox postVbox;

    @FXML
    public void initialize() throws IOException {
        homeTitle.setText("Home");

        for(User u : DataManager.getUser().getFollowings()) {
            tweets.addAll(u.getTweets());
        }

        tweets.sort(Comparator.comparing(edu.sharif.twitter.entity.Tweet::getCreateDateTime));
        Collections.reverse(tweets);
        for (edu.sharif.twitter.entity.Tweet t : tweets) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/show/tweetView.fxml"));
            Node node = loader.load();
            TweetView tweetView = loader.getController();
            tweetView.setTweet(t);
            postVbox.getChildren().add(node);
        }
    }
}
