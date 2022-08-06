package edu.sharif.twitter.view;

import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.service.PublicMessageService;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.view.data.DataManager;
import edu.sharif.twitter.view.show.CommentView;
import edu.sharif.twitter.view.show.TweetView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Comment extends Menu{
    private PublicMessageService<edu.sharif.twitter.entity.Comment> publicMessageService = ApplicationContext.getCommentService();

    private edu.sharif.twitter.entity.Comment replyComment;
    private Tweet tweet = DataManager.getTweet();
    private TweetView tweetView;
    private ArrayList<CommentView> commentViews = new ArrayList<>();
    @FXML
    private VBox commentVbox, tweetVbox;
    @FXML
    private TextArea commentField;
    @FXML
    private Label replyLabel;
    @FXML
    private Button sendButton;

    @FXML
    public void initialize() throws IOException {
        FXMLLoader tweetLoader = new FXMLLoader(getClass().getResource("fxml/show/tweetView.fxml"));

        Node node = tweetLoader.load();
        tweetVbox.getChildren().add(node);
        tweetView = tweetLoader.getController();
        tweetView.setTweet(tweet);

        List<edu.sharif.twitter.entity.Comment> commentList = ApplicationContext.getTweetService().getComments(tweet);
        for (int i =  commentList.size() - 1; i >= 0; i--) {
            FXMLLoader commentLoader = new FXMLLoader(getClass().getResource("fxml/show/commentView.fxml"));

            edu.sharif.twitter.entity.Comment comment = commentList.get(i);

            node = commentLoader.load();
            CommentView commentView = commentLoader.getController();
            commentView.setComment(comment);
            commentViews.add(commentView);
            commentVbox.getChildren().add(node);
        }

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> update()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @FXML
    public void send() throws IOException {
        FXMLLoader commentLoader = new FXMLLoader(getClass().getResource("fxml/show/commentView.fxml"));

        edu.sharif.twitter.entity.Comment origin = DataManager.getComment();
        edu.sharif.twitter.entity.Comment pm;
        if (origin != null)
            pm = publicMessageService.createPublicMessage(DataManager.getUser(), origin, commentField.getText());
        else
            pm = publicMessageService.createPublicMessage(DataManager.getUser(), tweet, commentField.getText());
        
        publicMessageService.save(pm);
        commentField.setText("");

        Node node = commentLoader.load();
        CommentView commentView = commentLoader.getController();
        commentView.setComment(pm);
        commentViews.add(0, commentView);
        commentVbox.getChildren().add(0, node);
    }

    public void update() {
        if (DataManager.getComment() != null) {
            String text = DataManager.getComment().getText();
            if (text.length() > 12) {
                text = text.substring(0, 12) + "...";
            }
            replyLabel.setText("Reply to: " + text);
        }
        else
            replyLabel.setText("set Reply");
    }
}
