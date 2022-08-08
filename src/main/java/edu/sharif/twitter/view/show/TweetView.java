package edu.sharif.twitter.view.show;

import edu.sharif.twitter.entity.Like;
import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.view.*;
import edu.sharif.twitter.view.data.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class TweetView {
    private Tweet tweet;
    @FXML
    private Label tweetLabel, adLabel, publicDateLabel;
    @FXML
    private Button likeButton, likesButton, commentButton, usernameButton, statButton;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ImageView profileImage;
    public ImageView likeButtonImage;

    @FXML
    private Button deleteButton, editButton;

    @FXML
    public void gotoComments(ActionEvent event) throws IOException {
        DataManager.setTweet(tweet);

        Parent root = FXMLLoader.load(Home.class.getResource("fxml/comment.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(DataManager.THEME);
        stage.setScene(scene);
        stage.show();
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) throws IOException {
        this.tweet = tweet;
        usernameButton.setText(tweet.getUser().getUsername());
        tweetLabel.setText(tweet.getText());
        adLabel.setVisible(tweet.getUser().getIsBusiness());
        publicDateLabel.setText(tweet.getCreateDateTime().toLocalDate().toString());

        profileImage.setImage(ApplicationContext.getUserService().getProfileImage(tweet.getUser()));
        Circle clipCircle = new Circle(15, 15, 15);
        profileImage.setClip(clipCircle);

        Boolean isCurrentUserTweet = tweet.getUser().equals(DataManager.getUser());
        statButton.setVisible(isCurrentUserTweet && DataManager.getUser().getIsBusiness());
        setLikeInfo();

        if (!isCurrentUserTweet) {
            ApplicationContext.getViewService().addView(DataManager.getUser(), tweet);
        }

        editButton.setVisible(isCurrentUserTweet);
        deleteButton.setVisible(isCurrentUserTweet);
    }

    private void setLikeInfo() {
        File file;
        if (isLikedByUser(DataManager.getUser())) {
            file = new File("src/main/resources/edu/sharif/twitter/images/like-button-filled.png");
        } else {
            file = new File("src/main/resources/edu/sharif/twitter/images/like-button-empty.png");
        }
        likeButtonImage.setImage(new Image(file.toURI().toString()));
        likesButton.setText(tweet.getLikes().size() + " likes");
    }

    private Boolean isLikedByUser(User user) {
        for (Like l : tweet.getLikes()) {
            if (l.getUser().equals(user)) {
                return true;
            }
        }
        return false;
    }

    public void onLikeButtonClicked() {
        if (isLikedByUser(DataManager.getUser())) {
            Like like = ApplicationContext.getLikeService().findByUserAndPublicMessage(DataManager.getUser(), tweet);
            ApplicationContext.getLikeService().delete(like);
        } else {
            ApplicationContext.getLikeService().addLike(DataManager.getUser(), tweet);
        }
        setLikeInfo();
    }

    public void onLikesButtonClicked(ActionEvent event) throws IOException {
        FXMLLoader likeListLoader = new FXMLLoader(Home.class.getResource("fxml/like-list-screen.fxml"));
        Scene scene = new Scene(likeListLoader.load());
        LikeListScreenController likeListScreenController = likeListLoader.getController();
        likeListScreenController.setPublicMessage(tweet.getUser().getUsername(), tweet);
        scene.getStylesheets().addAll(DataManager.THEME);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void gotoProfile(ActionEvent event) throws IOException {
        Parent root;
        if (tweet.getUser().equals(DataManager.getUser())) {
            root = FXMLLoader.load(Profile.class.getResource("fxml/profile.fxml"));
        } else {
            DataManager.setTargetUser(tweet.getUser());
            root = FXMLLoader.load(UserScreenController.class.getResource("fxml/user-screen.fxml"));
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(DataManager.THEME);
        stage.setScene(scene);
        stage.show();
    }


    public void showStat(ActionEvent event) throws IOException {
        FXMLLoader profileStatLoader = new FXMLLoader(TweetStatScreenController.class.getResource("fxml/tweet-stat-screen.fxml"));
        Scene scene = new Scene(profileStatLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        TweetStatScreenController tweetStatScreenController = profileStatLoader.getController();
        tweetStatScreenController.setPublicMessage(tweet.getUser().getUsername(), tweet);
        scene.getStylesheets().addAll(DataManager.THEME);
        stage.setScene(scene);
        stage.show();
    }

    public void deleteTweet(ActionEvent event) throws IOException {
        FXMLLoader deleteTweetLoader = new FXMLLoader(DeleteTweetScreenController.class.getResource("fxml/delete-tweet-screen.fxml"));
        Scene scene = new Scene(deleteTweetLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        DeleteTweetScreenController deleteTweetLoaderController = deleteTweetLoader.getController();
        deleteTweetLoaderController.setTweet(tweet);
        scene.getStylesheets().addAll(DataManager.THEME);
        stage.setScene(scene);
        stage.show();
    }

    public void editTweet(ActionEvent event) throws IOException {
        FXMLLoader editTweetLoader = new FXMLLoader(DeleteTweetScreenController.class.getResource("fxml/edit-tweet-screen.fxml"));
        Scene scene = new Scene(editTweetLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        EditTweetScreenController editTweetLoaderController = editTweetLoader.getController();
        editTweetLoaderController.setTweet(tweet);
        scene.getStylesheets().addAll(DataManager.THEME);
        stage.setScene(scene);
        stage.show();
    }
}
