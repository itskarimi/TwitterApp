package edu.sharif.twitter.view.show;

import edu.sharif.twitter.entity.Message;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.view.data.DataManager;
import edu.sharif.twitter.view.data.MessageMode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class MessageView {
    private Message message;
    @FXML
    private Label usernameLabel, messageLabel;
    @FXML
    private MenuBar optionBar;
    @FXML
    private ImageView profileImage;

    @FXML
    public void reply() {

        if (DataManager.getMode() == MessageMode.NULL ||
                (!DataManager.getMessage().equals(message) && DataManager.getMode() == MessageMode.REPLY))
            DataManager.setMessage(message, MessageMode.REPLY);
        else
            DataManager.setMessage(null, MessageMode.NULL);
    }

    @FXML
    public void edit() {
        DataManager.setMessage(message, MessageMode.EDIT);
    }

    @FXML
    public void delete() {
        DataManager.setMessage(message, MessageMode.DELETE);
    }

    @FXML
    public void forward() {
        DataManager.setMessage(message, MessageMode.FORWARD);
    }


    public void setMessage(Message message) throws IOException {
        this.message = message;
        usernameLabel.setText(message.getUser().getUsername());
        String text = "";
        if (message.getOrigin() != null) {
            text = message.getOrigin().getText();
            if (text.length() > 12) {
                text = text.substring(0, 12) + "...";
            }
            text = "reply: " + text + "\n";
        }
        if (message.getIsForward())
            text = "Forward!\n";
        text += message.getText();
        messageLabel.setText(text);
        if (message.getUser().equals(DataManager.getUser())) {
            double size = 160;
            usernameLabel.setLayoutX(570 - size + 35);
            messageLabel.setLayoutX(570 - size);
            optionBar.setLayoutX(570 - size + 120);
            profileImage.setLayoutX(570 - size);
        }

        profileImage.setImage(ApplicationContext.getUserService().getProfileImage(message.getUser()));
        Circle clipCircle = new Circle(15, 15, 15);
        profileImage.setClip(clipCircle);

    }
}
