package edu.sharif.twitter.view;

import edu.sharif.twitter.service.PublicMessageService;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.view.data.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Tweet extends Menu {
    private PublicMessageService<edu.sharif.twitter.entity.Tweet> publicMessageService = ApplicationContext.getTweetService();
    private FileChooser fileChooser = new FileChooser();
    @FXML
    private TextArea tweetArea;
    @FXML
    private Button shareButton, addImage, removeButton;
    @FXML
    private ImageView imageView;

    @FXML
    public void tweet(ActionEvent event) {
        edu.sharif.twitter.entity.Tweet pm = publicMessageService.createPublicMessage(DataManager.getUser(), null, tweetArea.getText());
        if (imageView.getImage() != null) {
            pm.setImage(imageView.getImage());
            imageView.setImage(null);
        }
        ApplicationContext.getTweetService().save(pm);
        tweetArea.setText("");
    }

    @FXML
    public void setTweetImage(ActionEvent event) {
        Stage stage = (Stage)  ((Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            imageView.setImage(image);
            tweetArea.setDisable(true);
            tweetArea.setText("");
            removeButton.setDisable(false);
        }
        else {
            tweetArea.setDisable(false);
            removeButton.setDisable(true);
        }
    }

    @FXML
    public void removeTweetImage() {
        imageView.setImage(null);
        tweetArea.setDisable(false);
        removeButton.setDisable(true);
    }
}
