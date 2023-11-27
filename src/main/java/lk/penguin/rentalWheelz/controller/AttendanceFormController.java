package lk.penguin.rentalWheelz.controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class AttendanceFormController {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox checkBoxContainer;

    // Assume you have a list of employee names
    private String[] employeeNames = {"Employee1", "Employee2", "Employee3","Employee3","Employee3","Employee3","Employee3","Employee3","Employee3","Employee3","Employee3","Employee3","Employee3","Employee3","Employee3","Employee3","Employee3","Employee3","Employee3","Employee3","Employee3","Employee3","Employee3","Employee3","Employee3","Employee3","Employee3","Employee3","Employee3",};

    public void initialize() {
        // Dynamically create checkboxes based on the number of employees
        for (String employeeName : employeeNames) {
            CheckBox checkBox = new CheckBox(employeeName);
            checkBoxContainer.getChildren().add(checkBox);
        }

        // Set the VBox as the content of the ScrollPane
        scrollPane.setContent(checkBoxContainer);
    }
}
