package edu.sharif.twitter.view;

import edu.sharif.twitter.entity.DateCount;
import edu.sharif.twitter.entity.Like;
import edu.sharif.twitter.entity.PublicMessage;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.view.show.UserView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class TweetStatScreenController extends Menu {
    public VBox postVbox;
    public Label usernameLabel, publicMessageLabel;
    public LineChart<String, Long> viewChart, likeChart;

    public void setPublicMessage(String username, PublicMessage publicMessage) throws IOException {
        publicMessageLabel.setText(publicMessage.getText());
        usernameLabel.setText(username);
        List<DateCount> viewDateCount = ApplicationContext.getTweetService().getViewStat(publicMessage);
        List<DateCount> likeDateCount = ApplicationContext.getTweetService().getLikeStat(publicMessage);

        XYChart.Series<String, Long> viewSeries = new XYChart.Series<>();
        viewSeries.setName("Number of views");
        for (DateCount d : viewDateCount) {
            viewSeries.getData().add(new XYChart.Data(d.getDate().toString(), d.getCount()));
        }
        viewChart.getData().add(viewSeries);

        XYChart.Series<String, Long> likeSeries = new XYChart.Series<>();
        likeSeries.setName("Number of likes");
        for (DateCount d : likeDateCount) {
            likeSeries.getData().add(new XYChart.Data(d.getDate().toString(), d.getCount()));
        }
        likeChart.getData().add(likeSeries);
    }
}
