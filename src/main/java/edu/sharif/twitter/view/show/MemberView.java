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
import javafx.stage.Stage;

public class MemberView {
    private User user;
    @FXML
    private Label usernameLabel;
    @FXML
    private Button addButton;

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

    public void setUser(User user) {
        this.user = user;
        usernameLabel.setText(user.getUsername());
    }
}
