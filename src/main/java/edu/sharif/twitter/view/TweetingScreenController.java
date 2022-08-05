package edu.sharif.twitter.view;

import edu.sharif.twitter.service.PublicMessageService;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.view.data.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class TweetingScreenController extends ScreenController {
    private PublicMessageService<edu.sharif.twitter.entity.Tweet> publicMessageService = ApplicationContext.getTweetService();
    @FXML
    private TextArea tweetArea;
    @FXML
    private Button shareButton;

    @FXML
    public void tweet(ActionEvent event) {
        edu.sharif.twitter.entity.Tweet pm = publicMessageService.createPublicMessage(DataManager.getUser(), null, tweetArea);
        publicMessageService.save(pm);
        tweetArea.setText("");
    }
}
