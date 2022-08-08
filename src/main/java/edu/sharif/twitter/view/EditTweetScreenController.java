package edu.sharif.twitter.view;

import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.view.data.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;

public class EditTweetScreenController extends Menu {
    public Button cancelButton;
    public Label usernameLabel;
    public Button editButton;
    public TextArea tweetArea;
    private edu.sharif.twitter.entity.Tweet tweet;

    public void setTweet(edu.sharif.twitter.entity.Tweet tweet) {
        this.tweet = tweet;
        usernameLabel.setText(tweet.getUser().getUsername());
        tweetArea.setText(tweet.getText());
    }

    public void cancel(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/profile.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(DataManager.THEME);
        stage.setScene(scene);
        stage.show();
    }

    public void editTweet(ActionEvent event) throws IOException {
        tweet.setText(tweetArea.getText());
        tweet.setLastUpdateDateTime(LocalDateTime.now());
        ApplicationContext.getTweetService().save(tweet);
        Parent root = FXMLLoader.load(getClass().getResource("fxml/profile.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(DataManager.THEME);
        stage.setScene(scene);
        stage.show();
    }
}
