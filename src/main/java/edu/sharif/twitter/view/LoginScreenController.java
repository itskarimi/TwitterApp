package edu.sharif.twitter.view;

import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.view.data.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginScreenController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    @FXML
    public void login(ActionEvent event) throws IOException {

        String errorCss = this.getClass().getResource("css/labelError.css").toExternalForm();

        User user = ApplicationContext.getUserService().login(usernameField, passwordField, errorCss);
        if (user == null)
            return;

        DataManager.setUser(user);

        Parent root = FXMLLoader.load(getClass().getResource("fxml/profile-screen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("css/theme1/home.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }
}
