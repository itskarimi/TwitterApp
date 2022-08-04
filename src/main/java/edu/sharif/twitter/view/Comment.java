package edu.sharif.twitter.view;

import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.view.data.DataManager;
import edu.sharif.twitter.view.show.CommentView;
import edu.sharif.twitter.view.show.TweetView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class Comment extends Menu{
    private Tweet tweet = DataManager.getTweet();
    private TweetView tweetView;
    private ArrayList<CommentView> commentViews = new ArrayList<>();
    @FXML
    private VBox commentVbox, tweetVbox;

    @FXML
    public void initialize() throws IOException {
        FXMLLoader tweetLoader = new FXMLLoader(getClass().getResource("fxml/show/tweetView.fxml"));
        FXMLLoader commentLoader = new FXMLLoader(getClass().getResource("fxml/show/commentView.fxml"));
        tweetVbox.getChildren().add(tweetLoader.load());
        tweetView = tweetLoader.getController();
        tweetView.setTweet(tweet);

        for (int i = tweet.getComments().size() - 1; i >= 0; i--) {
            edu.sharif.twitter.entity.Comment comment = tweet.getComments().get(i);

            commentVbox.getChildren().add(commentLoader.load());
            CommentView commentView = commentLoader.getController();
            commentView.setComment(comment);
            commentViews.add(commentView);
        }
    }
}
