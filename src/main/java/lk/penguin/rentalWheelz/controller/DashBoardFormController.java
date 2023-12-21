package lk.penguin.rentalWheelz.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DashBoardFormController {

    @FXML
    private Label lblDateShow;

    @FXML
    private Label lblCalenderShow;

    @FXML
    public void initialize() {
        Timeline clockTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            // Update time label
            //SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
            //String currentTime = timeFormat.format(new Date());
            //lblDateShow.setText(currentTime);

            // Update date-time label
            SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String currentDateTime = dateTimeFormat.format(new Date());
            lblCalenderShow.setText(currentDateTime);
        }));

        // Set the timeline to repeat indefinitely
        clockTimeline.setCycleCount(Timeline.INDEFINITE);

        // Start the timeline
        clockTimeline.play();
    }
}
