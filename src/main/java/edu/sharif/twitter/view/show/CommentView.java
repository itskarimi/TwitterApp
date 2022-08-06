package edu.sharif.twitter.view.show;

import edu.sharif.twitter.entity.Comment;
import edu.sharif.twitter.entity.Like;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.view.Home;
import edu.sharif.twitter.view.LikeListScreenController;
import edu.sharif.twitter.view.data.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class CommentView {
    Comment comment;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label usernameLabel, commentLabel, replyLabel;
    @FXML
    private Button likeButton, likesButton, replyButton;
    public ImageView likeButtonImage;

    @FXML
    public void reply() {
        String text ;

        if (DataManager.getComment() == null || !DataManager.getComment().equals(comment))
            DataManager.setComment(comment);
        else
            DataManager.setComment(null);
    }

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
        setLikeInfo();
    }

    private void setLikeInfo() {
        File file;
        if (isLikedByUser(DataManager.getUser())) {
            file = new File("src/main/resources/edu/sharif/twitter/images/like-button-filled.png");
        } else {
            file = new File("src/main/resources/edu/sharif/twitter/images/like-button-empty.png");
        }
        likeButtonImage.setImage(new Image(file.toURI().toString()));
        likesButton.setText(comment.getLikes().size() + " likes");
    }

    private Boolean isLikedByUser(User user) {
        for (Like l : comment.getLikes()) {
            if (l.getUser().equals(user)) {
                return true;
            }
        }
        return false;
    }

    public void onLikeButtonClicked() {
        if (isLikedByUser(DataManager.getUser())) {
            Like like = ApplicationContext.getLikeService().findByUserAndPublicMessage(DataManager.getUser(), comment);
            ApplicationContext.getLikeService().delete(like);
        } else {
            ApplicationContext.getLikeService().addLike(DataManager.getUser(), comment);
        }

        setLikeInfo();
    }

    public void onLikesButtonClicked(ActionEvent event) throws IOException {
        FXMLLoader likeListLoader = new FXMLLoader(Home.class.getResource("fxml/like-list-screen.fxml"));
        Scene scene = new Scene(likeListLoader.load());
        LikeListScreenController likeListScreenController = likeListLoader.getController();
        likeListScreenController.setPublicMessage(comment.getUser().getUsername(), comment);
        String css = Home.class.getResource("css/theme1/home.css").toExternalForm();
        scene.getStylesheets().add(css);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
