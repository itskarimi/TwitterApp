package edu.sharif.twitter.view;

import edu.sharif.twitter.entity.Like;
import edu.sharif.twitter.entity.PublicMessage;
import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.service.PublicMessageService;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.view.data.DataManager;
import edu.sharif.twitter.view.show.CommentView;
import edu.sharif.twitter.view.show.TweetView;
import edu.sharif.twitter.view.show.UserView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LikeListScreenController extends Menu{

    @FXML
    private VBox likeVbox;
    public Label usernameLabel;
    public Label publicMessageLabel;

    @FXML
    public void initialize() {

    }

    public void setPublicMessage(String username, PublicMessage publicMessage) throws IOException {
        publicMessageLabel.setText(publicMessage.getText());
        usernameLabel.setText(username);
        List<Like> likeList = publicMessage.getLikes();
        likeList.sort(Comparator.comparing(Like::getCreateDateTime));
        Collections.reverse(likeList);
        for (Like l : likeList) {
            FXMLLoader userLoader = new FXMLLoader(getClass().getResource("fxml/show/userView.fxml"));
            Node node = userLoader.load();
            UserView userView = userLoader.getController();
            userView.setUser(l.getUser());
            likeVbox.getChildren().add(node);
        }
    }
}
