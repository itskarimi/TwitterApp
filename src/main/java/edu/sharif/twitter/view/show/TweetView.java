package edu.sharif.twitter.view.show;

import edu.sharif.twitter.entity.Like;
import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.view.Home;
import edu.sharif.twitter.view.LikeListScreenController;
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

public class TweetView {
    private Tweet tweet;
    @FXML
    private Label usernameLabel, tweetLabel;
    @FXML
    private Button likeButton, likesButton, commentButton;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ImageView profileImage;
    public ImageView likeButtonImage;

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

    public void setTweet(Tweet tweet) throws IOException {
        this.tweet = tweet;
        usernameLabel.setText(tweet.getUser().getUsername());
        tweetLabel.setText(tweet.getText());

        profileImage.setImage(ApplicationContext.getUserService().getProfileImage(tweet.getUser()));
        Circle clipCircle = new Circle(15, 15, 15);
        profileImage.setClip(clipCircle);

        setLikeInfo();
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
        String css = Home.class.getResource("css/theme1/home.css").toExternalForm();
        scene.getStylesheets().add(css);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
