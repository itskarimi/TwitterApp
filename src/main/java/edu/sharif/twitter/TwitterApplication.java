package edu.sharif.twitter;

import edu.sharif.twitter.utils.menu.ShowAndRunMenu;
import edu.sharif.twitter.view.data.DataManager;
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
//        FXMLLoader fxmlLoader = new FXMLLoader(TwitterApplication.class.getResource("view/fxml/twitter-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        scene.getStylesheets().add(DataManager.THEME[0]);
//        stage.setTitle("Sharif Twitter");
//        stage.setScene(scene);
//        stage.show();
        new ShowAndRunMenu().runMenu();
    }

}
