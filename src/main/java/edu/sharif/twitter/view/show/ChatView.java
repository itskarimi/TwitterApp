package edu.sharif.twitter.view.show;

import edu.sharif.twitter.entity.Chat;
import edu.sharif.twitter.entity.DM;
import edu.sharif.twitter.entity.Group;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.view.Home;
import edu.sharif.twitter.view.data.DataManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.util.Callback;

import java.io.IOException;

public class ChatView {
    @FXML
    private Label nameLabel, NMLabel;
    @FXML
    private ImageView profileImage;

    public void setChat(Chat chat) throws IOException {
        nameLabel.setText(ApplicationContext.getChatService().getName(DataManager.getUser(), chat));

        if (chat instanceof DM)
            profileImage.setImage(ApplicationContext.getUserService().
                    getProfileImage(chat.getMembers().get(0).equals(DataManager.getUser()) ? chat.getMembers().get(1) : chat.getMembers().get(0)));
        else
            profileImage.setImage(ApplicationContext.getGroupService().getProfileImage((Group) chat));
        Circle clipCircle = new Circle(15, 15, 15);
        profileImage.setClip(clipCircle);
    }
}
