package edu.sharif.twitter.view;

import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.utils.InputInformation;
import edu.sharif.twitter.view.data.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SignUp {
    private FileChooser fileChooser =  new FileChooser();
    @FXML
    private TextField usernameField, firstNameField, lastNameField, emailField, ageField, bioField, hintField;
    @FXML
    private PasswordField passwordField, confirmPasswordField;
    @FXML
    private Button signUpButton, chooseButton;
    @FXML
    private ToggleButton businessToggle;
    @FXML
    private Label warningLabel;
    @FXML
    private ImageView profileImage;

    @FXML
    public void signUp(ActionEvent event) throws IOException {

        String errorCss = this.getClass().getResource("css/labelError.css").toExternalForm();

        if (!ApplicationContext.getUserService().signUp(usernameField, passwordField, confirmPasswordField, profileImage.getImage(), hintField,
                firstNameField, lastNameField,
                emailField, ageField, bioField,
                businessToggle, errorCss, warningLabel))
            return;


        Parent root = FXMLLoader.load(getClass().getResource("fxml/twitter-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(DataManager.THEME[0]);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void selectPhoto(ActionEvent event) {
        Stage stage = (Stage)  ((Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            profileImage.setImage(image);
        }
    }
}
