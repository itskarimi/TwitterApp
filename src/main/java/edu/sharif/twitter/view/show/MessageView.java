package edu.sharif.twitter.view.show;

import edu.sharif.twitter.entity.Message;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.view.data.DataManager;
import edu.sharif.twitter.view.data.MessageMode;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import lombok.Getter;
import org.hibernate.type.descriptor.ValueBinder;

import java.io.IOException;

@Getter
public class MessageView {
    private Message message;
    @FXML
    private Label usernameLabel, messageLabel, dateLabel;
    @FXML
    private MenuBar optionBar;
    @FXML
    private ImageView profileImage;
    @FXML
    private VBox messageVbox;
    @FXML
    private HBox messageHbox;
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
        dateLabel.setText(message.getCreateDateTime().toLocalDate().toString());

        if (message.getUser().equals(DataManager.getUser()))
            messageHbox.setAlignment(Pos.TOP_RIGHT);

        profileImage.setImage(ApplicationContext.getUserService().getProfileImage(message.getUser()));
        Circle clipCircle = new Circle(15, 15, 15);
        profileImage.setClip(clipCircle);
    }


}
