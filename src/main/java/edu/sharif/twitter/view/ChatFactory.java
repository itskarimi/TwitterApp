package edu.sharif.twitter.view;

import edu.sharif.twitter.entity.Chat;
import edu.sharif.twitter.view.show.ChatView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.io.IOException;

public class ChatFactory implements Callback<ListView<Chat>, ListCell<Chat>> {

    @Override
    public ListCell<Chat> call(ListView<Chat> chatListView) {
        return new ListCell<>() {
            @Override
            public void updateItem(Chat chat, boolean empty) {
                super.updateItem(chat, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else if (chat != null) {
                    setText(null);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/show/chatView.fxml"));
                    Node node;
                    try {
                        node = loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    ChatView chatView = loader.getController();
                    try {
                        chatView.setChat(chat);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    setGraphic(node);
                } else {
                    setText(null);
                    setGraphic(null);
                }
            }
        };
    }
}
