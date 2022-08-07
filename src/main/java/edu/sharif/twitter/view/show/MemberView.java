package edu.sharif.twitter.view.show;

import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.entity.dto.SearchUserDto;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.view.Profile;
import edu.sharif.twitter.view.data.DataManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class MemberView {
    private User user;
    @FXML
    private Label usernameLabel;
    @FXML
    private Button addButton;
    @FXML
    private ImageView profileImage;

    @FXML
    public void addMember() {
        if (DataManager.getMembers().contains(user)) {
            DataManager.getMembers().remove(user);
            addButton.setText("+Add");
        } else {
            DataManager.getMembers().add(user);
            addButton.setText("-Remove");
        }
    }

    public void setUser(User user) throws IOException {
        this.user = user;
        usernameLabel.setText(user.getUsername());

        profileImage.setImage(ApplicationContext.getUserService().getProfileImage(user));
        Circle clipCircle = new Circle(15, 15, 15);
        profileImage.setClip(clipCircle);
    }
}
