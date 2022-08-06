package edu.sharif.twitter.view;

import edu.sharif.twitter.entity.Group;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.view.data.DataManager;
import edu.sharif.twitter.view.show.GroupMemberView;
import edu.sharif.twitter.view.show.UserView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class GroupProfile extends Menu {

    private ArrayList<User> members;
    @FXML
    private VBox memberVbox;
    @FXML
    private Label groupProfileLabel;

    @FXML
    public void initialize() throws IOException {
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
