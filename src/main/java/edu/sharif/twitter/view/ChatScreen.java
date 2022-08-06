package edu.sharif.twitter.view;

import edu.sharif.twitter.entity.Chat;
import edu.sharif.twitter.entity.Message;
import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.service.MessageService;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.view.data.DataManager;
import edu.sharif.twitter.view.show.ChatView;
import edu.sharif.twitter.view.show.CommentView;
import edu.sharif.twitter.view.show.MessageView;
import edu.sharif.twitter.view.show.TweetView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChatScreen extends Menu {
    @FXML
    private ListView<Chat> chatList;
    @FXML
    private VBox chatVbox;
    @FXML
    private Button sendButton;
    @FXML
    private TextArea messageArea;
    @FXML
    private Label replyLabel;
    @FXML
    private ScrollPane scrollPane;
    private ObservableList<Chat> observableList = FXCollections.observableArrayList();
    private ArrayList<MessageView> messageViews = new ArrayList<>();
    private MessageService messageService = ApplicationContext.getMessageService();

    @FXML
    public void initialize() {
        observableList.addAll(ApplicationContext.getChatService().getChats(DataManager.getUser()));
        chatList.setCellFactory(new ChatFactory());
        chatList.setItems(observableList);

        chatList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Chat>() {
            @Override
            public void changed(ObservableValue<? extends Chat> observableValue, Chat chat, Chat t1) {
                try {
                    showChat(chatList.getSelectionModel().getSelectedItem());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> update()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void showChat(Chat chat) throws IOException {
        DataManager.setChat(chat);
        messageViews.clear();
        chatVbox.getChildren().clear();
        for (int i = chat.getMessages().size() - 1; i >= 0; i--) {
            Message message = chat.getMessages().get(i);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/show/messageView.fxml"));
            Node node = loader.load();
            MessageView messageView = loader.getController();
            messageView.setMessage(message);
            messageViews.add(messageView);
            chatVbox.getChildren().add(node);
        }
    }

    @FXML
    public void send() throws IOException {
        if (messageArea.getText().equals(""))
            return;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/show/messageView.fxml"));

        Message origin = DataManager.getMessage();
        Message message;
        if (origin == null)
            message = messageService.addMessage(DataManager.getUser(), DataManager.getChat(), messageArea);
        else
            message = messageService.addReply(DataManager.getUser(), DataManager.getMessage(), messageArea);

        messageArea.setText("");

        Node node = loader.load();
        MessageView messageView = loader.getController();
        messageView.setMessage(message);
        messageViews.add(0, messageView);
        chatVbox.getChildren().add(0, node);

        observableList.clear();
        observableList.addAll(ApplicationContext.getChatService().getChats(DataManager.getUser()));
    }

    public void update() {
        if (DataManager.getMessage() != null) {
            String text = DataManager.getMessage().getText();
            if (text.length() > 12) {
                text = text.substring(0, 12) + "...";
            }
            replyLabel.setText("Reply to: " + text);
        }
        else
            replyLabel.setText("set Reply");
    }
}
