package edu.sharif.twitter.view;

import edu.sharif.twitter.entity.*;
import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.service.MessageService;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.view.data.DataManager;
import edu.sharif.twitter.view.data.MessageMode;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ChatScreen extends Menu {
    @FXML
    private ListView<Chat> chatList;
    @FXML
    private VBox chatVbox;
    @FXML
    private Button sendButton, profileButton, newGroupButton;
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
    public void initialize() throws IOException {
        observableList.addAll(ApplicationContext.getChatService().getChats(DataManager.getUser()));
        chatList.setCellFactory(new ChatFactory());
        chatList.setItems(observableList);

        if (DataManager.getChat() != null)
            showChat(DataManager.getChat());

        chatList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Chat>() {
            @Override
            public void changed(ObservableValue<? extends Chat> observableValue, Chat chat, Chat t1) {
                try {
                    showChat(chatList.getSelectionModel().getSelectedItem());
                } catch (IOException e) {
                    //throw new RuntimeException(e);
                }
            }
        });

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> update()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void showChat(Chat chat) throws IOException {
        if (DataManager.getMode() != MessageMode.FORWARD) {
            replyLabel.setText("");
            DataManager.setMessage(null, MessageMode.NULL);
        }
        profileButton.setText(ApplicationContext.getChatService().getName(DataManager.getUser(), chat));
        messageArea.setText("");
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
        if (messageArea.getText().equals("") && DataManager.getMode() != MessageMode.FORWARD)
            return;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/show/messageView.fxml"));
        Node node = loader.load();
        MessageView messageView = loader.getController();

        Message message;
        if (DataManager.getMode() == MessageMode.REPLY) {
            message = messageService.addReply(DataManager.getUser(), DataManager.getMessage(), messageArea.getText());
            messageView.setMessage(message);
            messageViews.add(0, messageView);
            chatVbox.getChildren().add(0, node);
        } else if (DataManager.getMode() == MessageMode.NULL) {
            message = messageService.addMessage(DataManager.getUser(), DataManager.getChat(), messageArea.getText());
            messageView.setMessage(message);
            messageViews.add(0, messageView);
            chatVbox.getChildren().add(0, node);
        } else if (DataManager.getMode() == MessageMode.EDIT) {
            message = DataManager.getMessage();
            messageService.editMessage(message, messageArea.getText());
        } else if (DataManager.getMode() == MessageMode.FORWARD) {
            if (DataManager.getChat() != null && !DataManager.getChat().equals(DataManager.getMessage().getChat()))
                messageService.forwardMessage(DataManager.getUser(), DataManager.getChat(), DataManager.getMessage());
        }

        DataManager.setMessage(null, MessageMode.NULL);
        messageArea.setText("");

        Collections.sort(observableList, new Comparator<Chat>() {
            @Override
            public int compare(Chat o1, Chat o2) {
                if (o1.getLastUpdateDateTime().isAfter(o2.getLastUpdateDateTime()))
                    return -1;
                return 1;
            }
        });
    }

    @FXML
    public void gotoProfile(ActionEvent event) throws IOException {
        Parent root;
        String css, tweetCss;

        css = UserScreenController.class.getResource("css/theme1/home.css").toExternalForm();
        tweetCss = UserScreenController.class.getResource("css/theme1/tweet.css").toExternalForm();

        if (DataManager.getChat() != null && DataManager.getChat() instanceof DM) {

            if (DataManager.getChat().getMembers().get(0).equals(DataManager.getUser()))
                DataManager.setTargetUser(DataManager.getChat().getMembers().get(1));
            else
                DataManager.getChat().getMembers().get(0);

            root = FXMLLoader.load(UserScreenController.class.getResource("fxml/user-screen.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(css);
            scene.getStylesheets().add(tweetCss);
            stage.setScene(scene);
            stage.show();
        }
        else {
            DataManager.setGroup((Group) DataManager.getChat());

            root = FXMLLoader.load(UserScreenController.class.getResource("fxml/group-profile.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(css);
            scene.getStylesheets().add(tweetCss);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    public void newGroup(ActionEvent event) throws IOException {
        DataManager.getMembers().add(DataManager.getUser());

        Parent root = FXMLLoader.load(getClass().getResource("fxml/new-group.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("css/theme1/home.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public void update() {
        if (DataManager.getMode() == MessageMode.REPLY) {
            String text = DataManager.getMessage().getText();
            if (text.length() > 12) {
                text = text.substring(0, 12) + "...";
            }
            replyLabel.setText("Reply to: " + text);
        } else if (DataManager.getMode() == MessageMode.EDIT) {
            String text = DataManager.getMessage().getText();
            replyLabel.setText("Edit from: " + text);
        } else if (DataManager.getMode() == MessageMode.DELETE) {
            messageService.delete(DataManager.getMessage());
        } else if (DataManager.getMode() == MessageMode.FORWARD) {
            replyLabel.setText("Forward\n!" + DataManager.getMessage().getText());
        }
    }
}
