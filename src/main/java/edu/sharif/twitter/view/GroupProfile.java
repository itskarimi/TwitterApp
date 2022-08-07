package edu.sharif.twitter.view;

import edu.sharif.twitter.entity.Group;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.view.data.DataManager;
import edu.sharif.twitter.view.show.GroupMemberView;
import edu.sharif.twitter.view.show.UserView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GroupProfile extends Menu {
    private ArrayList<User> members;
    @FXML
    private VBox memberVbox;
    @FXML
    private Label groupProfileLabel;
    @FXML
    private Button addButton;
    @FXML
    private ImageView profileImage;
    @FXML
    public void initialize() throws IOException {
        if (!DataManager.getGroup().getAdmins().contains(DataManager.getUser()))
            addButton.setDisable(true);

        Circle clipCircle = new Circle(57.5, 57.5, 57.5);
        profileImage.setClip(clipCircle);

        profileImage.setImage(ApplicationContext.getGroupService().getProfileImage(DataManager.getGroup()));

        groupProfileLabel.setText(DataManager.getGroup().getGroupProfile().getName());

        members = new ArrayList<>(DataManager.getGroup().getMembers());

        for (User u : DataManager.getGroup().getMembers()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/show/group-member-view.fxml"));
            Node node = loader.load();
            GroupMemberView groupMemberView = loader.getController();
            groupMemberView.setUser(u);
            memberVbox.getChildren().add(node);
        }

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            try {
                update();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @FXML
    public void gotoAddMember(ActionEvent event) throws IOException {
        DataManager.getMembers().addAll(DataManager.getGroup().getMembers());

        Parent root = FXMLLoader.load(getClass().getResource("fxml/add-member.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("css/theme1/home.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public void update() throws IOException {
        if (DataManager.getGroup().getMembers().size() != members.size()) {
            memberVbox.getChildren().clear();
            members = new ArrayList<>(DataManager.getGroup().getMembers());

            for (User u : DataManager.getGroup().getMembers()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/show/group-member-view.fxml"));
                Node node = loader.load();
                GroupMemberView groupMemberView = loader.getController();
                groupMemberView.setUser(u);
                memberVbox.getChildren().add(node);
            }
        }
    }
}
