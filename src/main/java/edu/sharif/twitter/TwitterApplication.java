package edu.sharif.twitter;

import edu.sharif.twitter.utils.menu.ShowAndRunMenu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TwitterApplication extends Application{
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(TwitterApplication.class.getResource("view/fxml/twitter-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        String css = this.getClass().getResource("view/css/theme1/login.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Sharif Twitter");
        stage.setScene(scene);
        stage.show();
    }
}
