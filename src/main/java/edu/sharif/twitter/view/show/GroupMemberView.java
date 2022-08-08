package edu.sharif.twitter.view.show;

import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.view.Home;
import edu.sharif.twitter.view.Profile;
import edu.sharif.twitter.view.UserScreenController;
import edu.sharif.twitter.view.data.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class GroupMemberView {
    private User user;
    @FXML
    private Label usernameLabel;
    @FXML
    private Button promoteButton, removeButton;
    @FXML
    private AnchorPane userAnchorPane;
    @FXML
    private ImageView profileImage;

    @FXML
    public void initialize() {
        if (!DataManager.getGroup().getAdmins().contains(DataManager.getUser())) {
            promoteButton.setDisable(true);
        }
    }

    @FXML
    public void switchToUserProfile(ActionEvent event) throws IOException {
        Parent root;
        String css, tweetCss;
        if (user.equals(DataManager.getUser())) {
            root = FXMLLoader.load(Profile.class.getResource("fxml/profile.fxml"));
        } else {
            DataManager.setTargetUser(user);
            root = FXMLLoader.load(UserScreenController.class.getResource("fxml/user-screen.fxml"));
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(DataManager.THEME);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void remove(ActionEvent event) throws IOException {
        ApplicationContext.getGroupService().removeMember(DataManager.getGroup(), DataManager.getUser(), user);

        if (DataManager.getUser().equals(user)) {
            DataManager.setChat(null);
            Parent root = FXMLLoader.load(Home.class.getResource("fxml/chat-screen.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().addAll(DataManager.THEME);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    public void promote() {
        if (DataManager.getGroup().getAdmins().contains(user)) {
            ApplicationContext.getGroupService().demoteMember(DataManager.getGroup(), DataManager.getUser(), user);
            promoteButton.setText("promote");
            usernameLabel.setText(user.getUsername());
        }
        else {
                ApplicationContext.getGroupService().promoteMember(DataManager.getGroup(), DataManager.getUser(), user);
                promoteButton.setText("demote");
                usernameLabel.setText(user.getUsername() + "(admin)");
            }
    }

    public void setUser(User user) throws IOException {
        this.user = user;
        String text = user.getUsername();

        profileImage.setImage(ApplicationContext.getUserService().getProfileImage(user));
        Circle clipCircle = new Circle(15, 15, 15);
        profileImage.setClip(clipCircle);

        if (DataManager.getGroup().getAdmins().contains(user))
            text += "(admin)";
        usernameLabel.setText(text);
        if (user.equals(DataManager.getUser())) {
            userAnchorPane.getChildren().remove(promoteButton);
            removeButton.setText("left");
            return;
        } else if (!DataManager.getGroup().getAdmins().contains(DataManager.getUser()))
            removeButton.setDisable(true);

        if (DataManager.getGroup().getAdmins().contains(user))
            promoteButton.setText("demote");
        else
            promoteButton.setText("promote");
    }
}
