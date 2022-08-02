package edu.sharif.twitter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TwitterController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
