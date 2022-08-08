package edu.sharif.twitter.view;

import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.entity.dto.SearchUserDto;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.view.data.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {
    @FXML
    private Label hintLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    private  int tries = 0;

    @FXML
    public void login(ActionEvent event) throws IOException {

        String errorCss = this.getClass().getResource("css/labelError.css").toExternalForm();

        User user = ApplicationContext.getUserService().login(usernameField, passwordField, errorCss);

        tries++;
        if (tries >= 4) {
            User hintUser = ApplicationContext.getUserService().findByUsername(new SearchUserDto(usernameField.getText()));
            if (hintUser != null && hintUser.getUserProfile().getPasswordHint() != null)
                hintLabel.setText(hintUser.getUserProfile().getPasswordHint());
        }
        if (user == null)
            return;

        DataManager.setUser(user);

        Parent root = FXMLLoader.load(getClass().getResource("fxml/profile.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(DataManager.THEME);
        stage.setScene(scene);
        stage.show();
    }
}
