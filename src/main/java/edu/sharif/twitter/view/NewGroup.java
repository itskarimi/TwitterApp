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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class NewGroup extends Menu {
    private FileChooser fileChooser = new FileChooser();
    private ArrayList<MemberView> memberViews = new ArrayList<>();
    @FXML
    private TextField groupNameField;
    @FXML
    private VBox followerVbox;
    @FXML
    private Button chooseButton;
    @FXML
    private ImageView profileImage;

    @FXML
    public void initialize() throws IOException {

        for (int i = DataManager.getUser().getFollowers().size() - 1; i >= 0; i--) {
            User member = DataManager.getUser().getFollowers().get(i);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/show/memberView.fxml"));
            Node node = loader.load();
            MemberView memberView = loader.getController();
            memberView.setUser(member);
            memberViews.add(memberView);
            followerVbox.getChildren().add(node);
        }
    }

    @FXML
    public void selectProfile(ActionEvent event) {
        Stage stage = (Stage)  ((Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            profileImage.setImage(image);
        }
    }

    @FXML
    public void newGroup(ActionEvent event) throws IOException {
        DataManager.setGroup(
                ApplicationContext.getGroupService().newGroup(
                        DataManager.getUser(), groupNameField.getText(), "", DataManager.getMembers(), profileImage.getImage()));
        DataManager.getMembers().clear();

        Parent root = FXMLLoader.load(getClass().getResource("fxml/chat-screen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(DataManager.THEME);
        stage.setScene(scene);
        stage.show();
    }
}
