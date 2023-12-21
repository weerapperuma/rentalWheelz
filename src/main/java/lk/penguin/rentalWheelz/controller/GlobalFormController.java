package lk.penguin.rentalWheelz.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.penguin.rentalWheelz.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class GlobalFormController implements Initializable {


    @FXML
    public AnchorPane pagingPane;

    private static GlobalFormController controller;
    @FXML
    private Label lblDateandTime;

    public GlobalFormController() {
        controller = this;
    }

    public static GlobalFormController getInstance() {
        return controller;
    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(pagingPane,"customerForm.fxml");
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(pagingPane,"employeeForm.fxml");
    }

    @FXML
    void btnHomeMouseOnExited(MouseEvent event) {

    }

    @FXML
    void btnHomeOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(pagingPane,"dashBoardForm.fxml");
    }

    @FXML
    void btnHomeOnMouseEntered(MouseEvent event) {

    }
    @FXML
    void btnEmpAttendance(ActionEvent event) throws IOException {
        Navigation.switchPaging2(GlobalFormController.getInstance().pagingPane, "attendanceForm.fxml");
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("loginPageForm.fxml",event);
    }

    @FXML
    void btnRentOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(pagingPane,"rentalForm.fxml");
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(pagingPane,"supplierForm.fxml");
    }

    @FXML
    void btnVehicleOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(pagingPane,"carForm.fxml");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Navigation.switchPaging(pagingPane,"dashBoardForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        startClock();
    }
    public void startClock() {
        Timeline clockTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            // Update time label
            //SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
            //String currentTime = timeFormat.format(new Date());
            //lblDateShow.setText(currentTime);

            // Update date-time label
            SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String currentDateTime = dateTimeFormat.format(new Date());
            lblDateandTime.setText(currentDateTime);
        }));

        // Set the timeline to repeat indefinitely
        clockTimeline.setCycleCount(Timeline.INDEFINITE);

        // Start the timeline
        clockTimeline.play();
    }
    @FXML
    void btnOrderOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(pagingPane,"OrderForm.fxml");
    }



}
