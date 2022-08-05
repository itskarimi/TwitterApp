package edu.sharif.twitter.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu {
    @FXML
    protected Button homeButton, directButton, addButton, exploreButton, profileButton;

    @FXML
    public void switchToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/home.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("css/theme1/home.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchToDirect(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/chat-screen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("css/theme1/home.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchToTweet(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/tweet.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("css/theme1/home.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchToExplore(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/explore.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("css/theme1/home.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchToProfile(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/profile.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("css/theme1/home.css").toExternalForm();
        String tweetCss = this.getClass().getResource("css/theme1/tweet.css").toExternalForm();
        scene.getStylesheets().add(css);
        scene.getStylesheets().add(tweetCss);
        stage.setScene(scene);
        stage.show();
    }
}