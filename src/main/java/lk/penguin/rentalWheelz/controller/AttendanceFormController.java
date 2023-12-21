package lk.penguin.rentalWheelz.controller;

import com.mysql.cj.jdbc.ConnectionImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import lk.penguin.rentalWheelz.db.DbConnection;
import lk.penguin.rentalWheelz.dto.AttendanceDto;
import lk.penguin.rentalWheelz.model.AttendanceModel;
import lk.penguin.rentalWheelz.model.EmployeeModel;
import lk.penguin.rentalWheelz.util.Navigation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AttendanceFormController {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox checkBoxContainer;

    private int selectedCheckboxCount = 0;

    public void initialize() throws SQLException, IOException {
        EmployeeModel model = new EmployeeModel();
        List<String> employeeNames = model.retrieveEmployeeNamesFromDatabase();

        for (String employeeName : employeeNames) {
            CheckBox checkBox = new CheckBox(employeeName);

            checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    System.out.println("Checkbox is checked"+employeeName);
                    saveAttendance(employeeName);

                } else {
                    System.out.println("Checkbox is unchecked");
                }
            });

            checkBoxContainer.getChildren().add(checkBox);
        }

        scrollPane.setContent(checkBoxContainer);
    }

    private void saveAttendance(String empName) {
        AttendanceModel attendanceModel = new AttendanceModel();

        try (Connection connection = DbConnection.getInstance().getConnection()) {
            // Auto-generate attendance ID
            //String attendID = attendanceModel.generateAttendID(connection);
            String attendID=attendanceModel.generateAttendanceId();
            System.out.println(attendID);

            String currentDate = getCurrentDate();
            AttendanceDto attendanceDto = new AttendanceDto(attendID, currentDate, empName);
            attendanceModel.saveAttendance(attendanceDto);
            // ... (rest of the code)
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }

    public void btnAttendanceEmployee(ActionEvent event) {

    }
}
