package edu.sharif.twitter.view;

import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.service.PublicMessageService;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.view.data.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class TweetingScreenController extends ScreenController {
    private PublicMessageService<edu.sharif.twitter.entity.Tweet> publicMessageService = ApplicationContext.getTweetService();
    @FXML
    private TextArea tweetArea;
    @FXML
    private Button shareButton;

    @FXML
    public void initialize() throws IOException {
        initializeGridPane();
    }

    @FXML
    public void tweet(ActionEvent event) {
        Tweet pm = publicMessageService.createPublicMessage(DataManager.getUser(), null, tweetArea);
        publicMessageService.save(pm);
        tweetArea.setText("");
    }
}
