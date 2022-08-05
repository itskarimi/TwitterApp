package edu.sharif.twitter.view.show;

import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.view.Home;
import edu.sharif.twitter.view.data.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TweetView {
    private Tweet tweet;
    @FXML
    private Label usernameLabel, tweetLabel;
    @FXML
    private Button likeButton, commentButton;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    public void gotoComments(ActionEvent event) throws IOException {
        DataManager.setTweet(tweet);

        Parent root = FXMLLoader.load(Home.class.getResource("fxml/comment.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = Home.class.getResource("css/theme1/home.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
        usernameLabel.setText(tweet.getUser().getUsername());
        tweetLabel.setText(tweet.getText());
    }
}
