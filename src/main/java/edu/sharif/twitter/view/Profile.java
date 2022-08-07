package edu.sharif.twitter.view;

import edu.sharif.twitter.TwitterApplication;
import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.view.data.DataManager;
import edu.sharif.twitter.view.show.TweetView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Profile extends Menu{
    private User user = DataManager.getUser();
    private ArrayList<TweetView> tweetViews = new ArrayList<>();
    private ArrayList<Node> nodes = new ArrayList<>();
    @FXML
    private Label usernameLabel, postsLabel;
    @FXML
    private Button followersButton, followingButton, statButton;
    @FXML
    private VBox postVbox;

    @FXML
    public void initialize() throws IOException {
        usernameLabel.setText(user.getUsername());
        postsLabel.setText(user.getTweets().size() + " Posts");
        followersButton.setText(user.getFollowers().size() + " Followers");
        followingButton.setText(user.getFollowings().size() + " Following");
        statButton.setVisible(user.getIsBusiness());

        for (int i = user.getTweets().size() - 1; i >= 0; i--) {
            Tweet tweet = user.getTweets().get(i);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/show/tweetView.fxml"));
            Node node = loader.load();
            TweetView tweetView = loader.getController();
            tweetView.setTweet(tweet);
            nodes.add(node);
            tweetViews.add(tweetView);
            postVbox.getChildren().add(node);
        }
    }

    @FXML
    public void onFollowersButtonClicked(ActionEvent event) throws IOException {
        FXMLLoader userListLoader = new FXMLLoader(getClass().getResource("fxml/user-list-screen.fxml"));
        String css = this.getClass().getResource("css/theme1/home.css").toExternalForm();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(userListLoader.load());
        UserListScreenController userListScreenController = userListLoader.getController();
        userListScreenController.setUsers(user.getFollowers());
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onFollowingButtonClicked(ActionEvent event) throws IOException {
        FXMLLoader userListLoader = new FXMLLoader(getClass().getResource("fxml/user-list-screen.fxml"));
        String css = this.getClass().getResource("css/theme1/home.css").toExternalForm();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(userListLoader.load());
        UserListScreenController userListScreenController = userListLoader.getController();
        userListScreenController.setUsers(user.getFollowings());
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void logout(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TwitterApplication.class.getResource("view/fxml/twitter-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        String css = TwitterApplication.class.getResource("view/css/theme1/login.css").toExternalForm();
        scene.getStylesheets().add(css);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Sharif Twitter");
        stage.setScene(scene);
        stage.show();
    }

    public void showStat(ActionEvent event) throws IOException {
        FXMLLoader profileStatLoader = new FXMLLoader(getClass().getResource("fxml/profile-stat-screen.fxml"));
        String css = this.getClass().getResource("css/theme1/home.css").toExternalForm();
        String chartCss = this.getClass().getResource("css/theme1/chart.css").toExternalForm();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(profileStatLoader.load());
        scene.getStylesheets().add(css);
        scene.getStylesheets().add(chartCss);
        stage.setScene(scene);
        stage.show();
    }
}
