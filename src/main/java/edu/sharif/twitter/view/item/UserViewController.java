package edu.sharif.twitter.view.item;

import edu.sharif.twitter.entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UserViewController {
    private User user;
    @FXML
    private Label usernameLabel;

    @FXML
    public void switchToUserProfile(ActionEvent event) {

    }

    public void setUser(User user) {
        this.user = user;
        usernameLabel.setText(user.getUsername());
    }
}
