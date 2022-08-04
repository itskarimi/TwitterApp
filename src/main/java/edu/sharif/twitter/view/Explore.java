package edu.sharif.twitter.view;

import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.entity.dto.SearchUserDto;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.view.show.UserView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class Explore extends Menu {
    private ArrayList<UserView> userViews = new ArrayList<>();
    private ArrayList<Node> nodes = new ArrayList<>();
    @FXML
    private TextField searchField;
    @FXML
    private VBox searchVbox;

    @FXML
    public void initialize() {
        searchField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                try {
                    search();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void search() throws IOException {
        SearchUserDto searchUserDto = new SearchUserDto(searchField.getText());
        User searchUser = ApplicationContext.getUserService().findByUsername(searchUserDto);
        searchVbox.getChildren().clear();
        userViews.clear();

        if (searchUser != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/show/userView.fxml"));
            Node node = loader.load();
            UserView userView = loader.getController();
            userView.setUser(searchUser);

            nodes.add(node);
            userViews.add(userView);
            searchVbox.getChildren().add(node);
        }
    }

}
