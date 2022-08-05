package edu.sharif.twitter.view;

import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.view.show.UserView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class UserListScreenController extends Menu {
    @FXML
    private List<User> users;
    @FXML
    private VBox usersVBox;

    @FXML
    public void initialize() {

    }

    public void setUsers(List<User> users) throws IOException {
        this.users = users;
        for (User u : users) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/show/userView.fxml"));
            Node node = loader.load();
            UserView userView = loader.getController();
            userView.setUser(u);
            usersVBox.getChildren().add(node);
        }
    }
}
