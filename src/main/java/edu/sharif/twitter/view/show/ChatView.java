package edu.sharif.twitter.view.show;

import edu.sharif.twitter.entity.Chat;
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
import javafx.util.Callback;

import java.io.IOException;

public class ChatView {
    @FXML
    private Label nameLabel, NMLabel;

    public void setChat(Chat chat) {
        nameLabel.setText(ApplicationContext.getChatService().getName(DataManager.getUser(), chat));

    }
}
