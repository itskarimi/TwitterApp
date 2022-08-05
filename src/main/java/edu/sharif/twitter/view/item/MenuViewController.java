package edu.sharif.twitter.view.item;

import edu.sharif.twitter.view.ExploreScreenController;
import edu.sharif.twitter.view.HomeScreenController;
import edu.sharif.twitter.view.ProfileScreenController;
import edu.sharif.twitter.view.TweetingScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuViewController {
    @FXML
    protected Button homeButton, directButton, addButton, exploreButton, profileButton;

    @FXML
    public void switchToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(ExploreScreenController.class.getResource("fxml/home-screen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = ExploreScreenController.class.getResource("css/theme1/home.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchToDirect(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(HomeScreenController.class.getResource("fxml/home-screen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = ExploreScreenController.class.getResource("css/theme1/home.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchToTweet(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(TweetingScreenController.class.getResource("fxml/tweeting-screen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = TweetingScreenController.class.getResource("css/theme1/home.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchToExplore(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(ExploreScreenController.class.getResource("fxml/explore-screen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = ExploreScreenController.class.getResource("css/theme1/home.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchToProfile(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(ProfileScreenController.class.getResource("fxml/profile-screen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = ProfileScreenController.class.getResource("css/theme1/home.css").toExternalForm();
        String tweetCss = ProfileScreenController.class.getResource("css/theme1/tweet.css").toExternalForm();
        scene.getStylesheets().add(css);
        scene.getStylesheets().add(tweetCss);
        stage.setScene(scene);
        stage.show();
    }
}
