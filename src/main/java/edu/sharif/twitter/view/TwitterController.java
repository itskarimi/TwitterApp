package edu.sharif.twitter.view;

import edu.sharif.twitter.view.data.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TwitterController {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button loginButton, signUpButton;

    @FXML
    public void login(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(DataManager.THEME[0]);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void signUp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/signUp.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(DataManager.THEME[0]);
        stage.setScene(scene);
        stage.show();
    }
}
