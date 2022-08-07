package edu.sharif.twitter.view.show;

import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.view.Profile;
import edu.sharif.twitter.view.UserScreenController;
import edu.sharif.twitter.view.data.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class UserView {
    private User user;
    @FXML
    private Label usernameLabel;
    @FXML
    private ImageView profileImage;


    @FXML
    public void switchToUserProfile(ActionEvent event) throws IOException {
        Parent root;
        String css, tweetCss;
        if (user.equals(DataManager.getUser())) {
            root = FXMLLoader.load(Profile.class.getResource("fxml/profile.fxml"));
            css = Profile.class.getResource("css/theme1/home.css").toExternalForm();
            tweetCss = Profile.class.getResource("css/theme1/tweet.css").toExternalForm();
        } else {
            DataManager.setTargetUser(user);
            root = FXMLLoader.load(UserScreenController.class.getResource("fxml/user-screen.fxml"));
            css = UserScreenController.class.getResource("css/theme1/home.css").toExternalForm();
            tweetCss = UserScreenController.class.getResource("css/theme1/tweet.css").toExternalForm();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        scene.getStylesheets().add(tweetCss);
        stage.setScene(scene);
        stage.show();
    }

    public void setUser(User user) throws IOException {
        this.user = user;
        usernameLabel.setText(user.getUsername());

        Circle clipCircle = new Circle(15, 15, 15);
        profileImage.setImage(ApplicationContext.getUserService().getProfileImage(user));
        profileImage.setClip(clipCircle);
    }
}
