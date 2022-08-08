package edu.sharif.twitter.view;

import edu.sharif.twitter.entity.DateCount;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.view.data.DataManager;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.util.List;

public class ProfileStatScreenController extends Menu {

    public Label usernameLabel;
    public LineChart<String, Long> chart;

    @FXML
    public void initialize() {
        usernameLabel.setText(DataManager.getUser().getUsername());
        List<DateCount> dateCountList = ApplicationContext.getUserService().showStats(DataManager.getUser());
        XYChart.Series<String, Long> series = new XYChart.Series<>();
        series.setName("Number of views");
        for (DateCount d : dateCountList) {
            series.getData().add(new XYChart.Data(d.getDate().toString(), d.getCount()));
        }
        chart.getData().add(series);
    }
}
