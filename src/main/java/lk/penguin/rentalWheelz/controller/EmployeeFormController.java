package lk.penguin.rentalWheelz.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lk.penguin.rentalWheelz.util.Navigation;

import java.io.IOException;

public class EmployeeFormController {

    @FXML
    private TableColumn<?, ?> colEmpAddress;

    @FXML
    private TableColumn<?, ?> colEmpContact;

    @FXML
    private TableColumn<?, ?> colEmpEmail;

    @FXML
    private TableColumn<?, ?> colEmpID;

    @FXML
    private TableColumn<?, ?> colEmpName;

    @FXML
    private TableColumn<?, ?> colEmpPosition;

    @FXML
    private TableView<?> tblEmployee;

    @FXML
    private TextField txtEmpId;

    @FXML
    void btnEmpAttendance(ActionEvent event) {

    }

    @FXML
    void btnEmpClear(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtEmpId.clear();
    }

    @FXML
    void btnEmpDelete(ActionEvent event) {

    }

    @FXML
    void btnEmpSalary(ActionEvent event) {

    }

    @FXML
    void btnEmpSave(ActionEvent event) throws IOException {
       //Navigation.closePane();
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane,"empSaveForm.fxml");
    }

    @FXML
    void btnEmpSearch(ActionEvent event) {

    }

    @FXML
    void btnEmpUpdate(ActionEvent event) {

    }

}
