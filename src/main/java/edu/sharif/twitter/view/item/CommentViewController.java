package edu.sharif.twitter.view.item;

import edu.sharif.twitter.entity.Comment;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class CommentViewController {
    Comment comment;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label usernameLabel, commentLabel, replyLabel;
    @FXML
    private Button likeButton;

    public void setComment(Comment comment) {
        this.comment = comment;
        usernameLabel.setText(comment.getUser().getUsername());
        commentLabel.setText(comment.getText());
        if (comment.getRepliedTo() == null)
            anchorPane.getChildren().remove(replyLabel);
        else {
            String text = comment.getRepliedTo().getText();
            if (text.length() > 12) {
                text = text.substring(0, 12) + "...";
            }
            replyLabel.setText(text);
        }
    }
}
