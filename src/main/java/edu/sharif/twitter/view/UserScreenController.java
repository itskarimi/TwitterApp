package edu.sharif.twitter.view;

import edu.sharif.twitter.entity.DM;
import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.view.data.DataManager;
import edu.sharif.twitter.view.show.TweetView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
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
    @FXML
    private ImageView profileImage;

    private void initLabels() throws IOException {
        usernameLabel.setText(user.getUsername());
        followButton.setText(user.getFollowers().contains(DataManager.getUser()) ? "Unfollow" : "Follow");
        postsLabel.setText(user.getTweets().size() + " Posts");
        followersButton.setText(user.getFollowers().size() + " Followers");
        followingButton.setText(user.getFollowings().size() + " Following");
        profileImage.setImage(ApplicationContext.getUserService().getProfileImage(user));

        Circle clipCircle = new Circle(57.5, 57.5, 57.5);
        profileImage.setClip(clipCircle);

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
//        ApplicationContext.getUserService().save(DataManager.getUser());
//        ApplicationContext.getUserService().save(user);
        initLabels();
    }

    @FXML
    public void onDirectToUserButtonClick(ActionEvent event) throws IOException {
        DM dm = ApplicationContext.getDmService().findByUsers(DataManager.getUser(), DataManager.getTargetUser());
        if (dm != null)
            DataManager.setChat(dm);
        else
            DataManager.setChat(ApplicationContext.getDmService().newDM(DataManager.getUser(), DataManager.getTargetUser()));
        FXMLLoader userListLoader = new FXMLLoader(getClass().getResource("fxml/chat-screen.fxml"));
        String css = this.getClass().getResource("css/theme1/home.css").toExternalForm();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(userListLoader.load());
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
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
    
}
