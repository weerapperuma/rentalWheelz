package lk.penguin.rentalWheelz.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lk.penguin.rentalWheelz.util.Navigation;

import java.io.IOException;
import java.util.regex.Pattern;

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
    private TextField txtEmpID;

    @FXML
    void btnEmpAttendance(ActionEvent event) {

    }

    public static String empID;
    @FXML
    void btnEmpClear(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtEmpID.clear();
    }

    @FXML
    void btnEmpDelete(ActionEvent event) {

    }

    @FXML
    void btnEmpSalary(ActionEvent event) {

    }

    @FXML
    void btnEmpSave(ActionEvent event) throws IOException {
        boolean isEmpSavedValidated=validateEmpSave();

       //Navigation.closePane();
        if(isEmpSavedValidated){
            Navigation.switchPaging(GlobalFormController.getInstance().pagingPane,"empSaveForm.fxml");
        }

    }

    private boolean validateEmpSave() {
        String idText = txtEmpID.getText();
//        boolean isCustomerIDValidated = Pattern.compile("[C][0-9]{3,}").matcher(idText).matches();
        boolean isCustomerIDValidated = Pattern.matches("[E][0-9]{3,}", idText);
        if (!isCustomerIDValidated) {

            new Alert(Alert.AlertType.ERROR, "Invalid Customer ID!").show();
            return false;
        }
        empID=idText;
        return true;
    }

    @FXML
    void btnEmpSearch(ActionEvent event) {

    }

    @FXML
    void btnEmpUpdate(ActionEvent event) {

    }

}
