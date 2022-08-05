package edu.sharif.twitter.view;

import edu.sharif.twitter.view.item.MenuViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ScreenController {
    @FXML
    private GridPane gridPane;

    protected MenuViewController menuViewController;

    protected void initializeGridPane() throws IOException {
        FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("fxml/item/menu-view.fxml"));
        menuViewController = menuLoader.getController();
        gridPane.getChildren().add(0, menuLoader.load());
    }
}