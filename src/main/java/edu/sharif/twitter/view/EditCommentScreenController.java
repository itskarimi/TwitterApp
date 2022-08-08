package edu.sharif.twitter.view;

import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.view.data.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;

public class EditCommentScreenController extends Menu {
    public Button editButton;
    public TextArea commentArea;
    public Label usernameLabel;
    public Button cancelButton;
    private edu.sharif.twitter.entity.Comment comment;
    
    public void setComment(edu.sharif.twitter.entity.Comment comment) {
        this.comment = comment;
        usernameLabel.setText(comment.getUser().getUsername());
        commentArea.setText(comment.getText());
    }

    public void editComment(ActionEvent event) throws IOException {
        comment.setText(commentArea.getText());
        comment.setLastUpdateDateTime(LocalDateTime.now());
        ApplicationContext.getCommentService().save(comment);
        Parent root = FXMLLoader.load(getClass().getResource("fxml/profile.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(DataManager.THEME);
        stage.setScene(scene);
        stage.show();
    }

    public void cancel(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/profile.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(DataManager.THEME);
        stage.setScene(scene);
        stage.show();
    }
}
