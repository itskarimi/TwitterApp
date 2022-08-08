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
import javafx.stage.Stage;

import java.io.IOException;

public class DeleteTweetScreenController extends Menu {

    public Label usernameLabel;
    public Label publicMessageLabel;
    public Button deleteButton;
    public Button cancelButton;
    private edu.sharif.twitter.entity.Tweet tweet;

    public void setTweet(edu.sharif.twitter.entity.Tweet tweet) {
        this.tweet = tweet;
        usernameLabel.setText(tweet.getUser().getUsername());
        publicMessageLabel.setText(tweet.getText());
    }

    public void deleteTweet(ActionEvent event) throws IOException {
        ApplicationContext.getTweetService().delete(tweet);
        Parent root = FXMLLoader.load(getClass().getResource("fxml/profile.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(DataManager.THEME);
        stage.setScene(scene);
        stage.show();
    }

    public void cancel(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/profile.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(DataManager.THEME);
        stage.setScene(scene);
        stage.show();
    }
}
