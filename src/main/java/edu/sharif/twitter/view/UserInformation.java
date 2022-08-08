package edu.sharif.twitter.view;

import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.view.data.DataManager;
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

import java.io.IOException;

public class UserInformation extends Menu {
    private User user;
    @FXML
    private Label usernameLabel, postsLabel, firstnameLabel, lastnameLabel, emailLabel, ageLabel, bioLabel;
    @FXML
    private Button followersButton, followingButton;
    @FXML
    private ImageView profileImage;

    public void setUser(User user) throws IOException {
        this.user = user;
        usernameLabel.setText(user.getUsername());
        postsLabel.setText(user.getTweets().size() + " Posts");
        followersButton.setText(user.getFollowers().size() + " Followers");
        followingButton.setText(user.getFollowings().size() + " Following");

        firstnameLabel.setText("Firstname: " + user.getUserProfile().getFirstName());
        lastnameLabel.setText("Lastname: " + user.getUserProfile().getLastName());
        emailLabel.setText("Email: " + user.getUserProfile().getEmail());
        ageLabel.setText("Age: " + user.getUserProfile().getAge().toString());
        bioLabel.setText("Bio: " + user.getUserProfile().getBio());


        Circle clipCircle = new Circle(57.5, 57.5, 57.5);
        profileImage.setClip(clipCircle);

        profileImage.setImage(ApplicationContext.getUserService().getProfileImage(user));
    }

    @FXML
    public void onFollowersButtonClicked(ActionEvent event) throws IOException {
        FXMLLoader userListLoader = new FXMLLoader(getClass().getResource("fxml/user-list-screen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(userListLoader.load());
        UserListScreenController userListScreenController = userListLoader.getController();
        userListScreenController.setUsers(user.getFollowers());
        scene.getStylesheets().addAll(DataManager.THEME);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onFollowingButtonClicked(ActionEvent event) throws IOException {
        FXMLLoader userListLoader = new FXMLLoader(getClass().getResource("fxml/user-list-screen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(userListLoader.load());
        UserListScreenController userListScreenController = userListLoader.getController();
        userListScreenController.setUsers(user.getFollowings());
        scene.getStylesheets().addAll(DataManager.THEME);
        stage.setScene(scene);
        stage.show();
    }
}
