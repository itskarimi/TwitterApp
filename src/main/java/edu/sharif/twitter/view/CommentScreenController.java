package edu.sharif.twitter.view;

import edu.sharif.twitter.entity.Comment;
import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.view.data.DataManager;
import edu.sharif.twitter.view.item.CommentViewController;
import edu.sharif.twitter.view.item.TweetViewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class CommentScreenController extends ScreenController {
    private Tweet tweet = DataManager.getTweet();
    private TweetViewController tweetView;
    private ArrayList<CommentViewController> commentViews = new ArrayList<>();
    @FXML
    private VBox commentVbox, tweetVbox;

    @FXML
    public void initialize() throws IOException {
        initializeGridPane();

        FXMLLoader tweetLoader = new FXMLLoader(getClass().getResource("fxml/item/tweet-view.fxml"));
        FXMLLoader commentLoader = new FXMLLoader(getClass().getResource("fxml/item/comment-view.fxml"));
        tweetVbox.getChildren().add(tweetLoader.load());
        tweetView = tweetLoader.getController();
        tweetView.setTweet(tweet);

        for (int i = tweet.getComments().size() - 1; i >= 0; i--) {
            Comment comment = tweet.getComments().get(i);

            commentVbox.getChildren().add(commentLoader.load());
            CommentViewController commentView = commentLoader.getController();
            commentView.setComment(comment);
            commentViews.add(commentView);
        }
    }
}
