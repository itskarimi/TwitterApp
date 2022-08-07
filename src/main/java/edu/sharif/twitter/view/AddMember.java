package edu.sharif.twitter.view;

import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.view.data.DataManager;
import edu.sharif.twitter.view.show.MemberView;
import edu.sharif.twitter.view.show.TweetView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AddMember extends Menu {
    private ArrayList<MemberView> memberViews = new ArrayList<>();
    @FXML
    private VBox followerVbox;

    @FXML
    public void initialize() throws IOException {

        for (int i = DataManager.getUser().getFollowers().size() - 1; i >= 0; i--) {
            User member = DataManager.getUser().getFollowers().get(i);
            if (!DataManager.getGroup().getMembers().contains(member)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/show/memberView.fxml"));
                Node node = loader.load();
                MemberView memberView = loader.getController();
                memberView.setUser(member);
                memberViews.add(memberView);
                followerVbox.getChildren().add(node);
            }
        }
    }

    @FXML
    public void addMember(ActionEvent event) throws IOException {

        for (User member : DataManager.getMembers())
            if (!DataManager.getGroup().getMembers().contains(member))
                ApplicationContext.getGroupService().addMember(DataManager.getGroup(), DataManager.getUser(), member);
        DataManager.getMembers().clear();

        Parent root = FXMLLoader.load(getClass().getResource("fxml/group-profile.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(DataManager.THEME);
        stage.setScene(scene);
        stage.show();
    }
}
