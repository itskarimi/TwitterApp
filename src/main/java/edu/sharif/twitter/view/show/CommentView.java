package edu.sharif.twitter.view.show;

import edu.sharif.twitter.entity.Comment;
import edu.sharif.twitter.entity.Like;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.view.*;
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
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class CommentView {

    Comment comment;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label commentLabel, commentReplyLabel, publicDateLabel;
    @FXML
    private Button likeButton, likesButton, replyButton, usernameButton;
    @FXML
    private ImageView profileImage;
    public ImageView likeButtonImage;
    public Button editButton;
    public Button deleteButton;

    @FXML
    public void reply() {
        String text ;

        if (DataManager.getComment() == null || !DataManager.getComment().equals(comment))
            DataManager.setComment(comment);
        else
            DataManager.setComment(null);
    }

    public void setComment(Comment comment) throws IOException {
        this.comment = comment;
        usernameButton.setText(comment.getUser().getUsername());
        commentLabel.setText(comment.getText());
        if (comment.getRepliedTo() == null)
            anchorPane.getChildren().remove(commentReplyLabel);
        else {
            String text = comment.getRepliedTo().getText();
            if (text.length() > 12) {
                text = text.substring(0, 12) + "...";
            }
            commentReplyLabel.setText(text);
        }

        publicDateLabel.setText(comment.getCreateDateTime().toLocalDate().toString());

        profileImage.setImage(ApplicationContext.getUserService().getProfileImage(comment.getUser()));
        Circle clipCircle = new Circle(15, 15, 15);
        profileImage.setClip(clipCircle);

        setLikeInfo();

        deleteButton.setVisible(comment.getUser().equals(DataManager.getUser()));
        editButton.setVisible(comment.getUser().equals(DataManager.getUser()));
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
        scene.getStylesheets().addAll(DataManager.THEME);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void gotoProfile(ActionEvent event) throws IOException {
        Parent root;
        if (comment.getUser().equals(DataManager.getUser())) {
            root = FXMLLoader.load(Profile.class.getResource("fxml/profile.fxml"));
        } else {
            DataManager.setTargetUser(comment.getUser());
            root = FXMLLoader.load(UserScreenController.class.getResource("fxml/user-screen.fxml"));
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(DataManager.THEME);
        stage.setScene(scene);
        stage.show();
    }

    public void deleteComment(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(DeleteTweetScreenController.class.getResource("fxml/delete-comment-screen.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        DeleteCommentScreenController controller = loader.getController();
        controller.setComment(comment);
        scene.getStylesheets().addAll(DataManager.THEME);
        stage.setScene(scene);
        stage.show();
    }

    public void editComment(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(DeleteTweetScreenController.class.getResource("fxml/edit-comment-screen.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        EditCommentScreenController controller = loader.getController();
        controller.setComment(comment);
        scene.getStylesheets().addAll(DataManager.THEME);
        stage.setScene(scene);
        stage.show();
    }
}
