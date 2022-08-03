package edu.sharif.twitter.view;

import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.utils.InputInformation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUp {
    @FXML
    private TextField usernameField, firstNameField, lastNameField, emailField, ageField, bioField;
    @FXML
    private PasswordField passwordField, confirmPasswordField;
    @FXML
    private Button signUpButton;
    @FXML
    private ToggleButton businessToggle;
    @FXML
    private Label warningLabel;

    @FXML
    public void signUp(ActionEvent event) throws IOException {

        String errorCss = this.getClass().getResource("css/labelError.css").toExternalForm();

        if (!ApplicationContext.getUserService().signUp(usernameField, passwordField, confirmPasswordField,
                firstNameField, lastNameField,
                emailField, ageField, bioField,
                businessToggle, errorCss, warningLabel))
            return;


        Parent root = FXMLLoader.load(getClass().getResource("fxml/twitter-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("css/login.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }
}
