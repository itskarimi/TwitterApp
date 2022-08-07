package edu.sharif.twitter.view.show;

import edu.sharif.twitter.entity.Like;
import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.view.Home;
import edu.sharif.twitter.view.LikeListScreenController;
import edu.sharif.twitter.view.TweetStatScreenController;
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
import javafx.stage.Stage;
import lombok.Data;

import java.io.File;
import java.io.IOException;

public class TweetView {
    private Tweet tweet;
    @FXML
    private Label usernameLabel, adLabel, tweetLabel;
    @FXML
    private Button likeButton, likesButton, commentButton, statButton;
    @FXML
    private AnchorPane anchorPane;
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

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
        usernameLabel.setText(tweet.getUser().getUsername());
        adLabel.setVisible(tweet.getUser().getIsBusiness());
        tweetLabel.setText(tweet.getText());
        statButton.setVisible(DataManager.getUser().getIsBusiness());
        setLikeInfo();

        if (!tweet.getUser().equals(DataManager.getUser())) {
            ApplicationContext.getViewService().addView(DataManager.getUser(), tweet);
        }
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

    public void showStat(ActionEvent event) throws IOException {
        FXMLLoader profileStatLoader = new FXMLLoader(TweetStatScreenController.class.getResource("fxml/tweet-stat-screen.fxml"));
        String css = TweetStatScreenController.class.getResource("css/theme1/home.css").toExternalForm();
        String chartCss = TweetStatScreenController.class.getResource("css/theme1/chart.css").toExternalForm();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(profileStatLoader.load());
        TweetStatScreenController tweetStatScreenController = profileStatLoader.getController();
        tweetStatScreenController.setPublicMessage(tweet.getUser().getUsername(), tweet);
        scene.getStylesheets().add(css);
        scene.getStylesheets().add(chartCss);
        stage.setScene(scene);
        stage.show();
    }
}
