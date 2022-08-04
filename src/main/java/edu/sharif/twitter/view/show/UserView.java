package edu.sharif.twitter.view.show;

import edu.sharif.twitter.entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UserView {
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
