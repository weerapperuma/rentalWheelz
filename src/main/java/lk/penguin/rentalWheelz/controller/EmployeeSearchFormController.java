package lk.penguin.rentalWheelz.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.penguin.rentalWheelz.dto.EmployeeDto;
import lk.penguin.rentalWheelz.model.EmployeeModel;
import lk.penguin.rentalWheelz.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class EmployeeSearchFormController {

    @FXML
    private TextField txtEmpAddress;

    @FXML
    private TextField txtEmpContact;

    @FXML
    private TextField txtEmpEmail;

    @FXML
    private TextField txtEmpID;

    @FXML
    private Label txtEmpIdShow;

    @FXML
    private TextField txtEmpName;

    @FXML
    private TextField txtEmpPosition;

    @FXML
    void btnEmpSearchBack(ActionEvent event) throws IOException {
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "employeeForm.fxml");
    }

    @FXML
    void btnEmpUpdateClear(ActionEvent event) {
        //txtEmpID.clear();
        txtEmpName.clear();
        txtEmpEmail.clear();
        txtEmpPosition.clear();
        txtEmpAddress.clear();
        txtEmpContact.clear();
    }

    @FXML
    void btnEmpUpdateOnAction(ActionEvent event) {
        boolean isEmpSavedValidated=validateEmpSave();

        if(isEmpSavedValidated){
            updateEmployee();
        }
    }
    private void updateEmployee() {
        String id= txtEmpID.getText();
        String name=txtEmpName.getText();
        String address = txtEmpAddress.getText();
        String contact = txtEmpContact.getText();
        String email = txtEmpEmail.getText();
        String position = txtEmpPosition.getText();

        var dto = new EmployeeDto(id,name,email,position,address,contact);
        var model= new EmployeeModel();

        try {
            boolean isUpdated = model.updateEmployee(dto);

            if(isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"Employee Updated Successfully").show();
                //EmployeeFormController.loadAllEmployees();
            }
        } catch (SQLException e) {
            System.out.println("hello");
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private boolean validateEmpSave() {
        boolean isEmpIDValidated= Pattern.compile("^(E)[0-9]{1,3}$").matcher(txtEmpID.getText()).matches();
        if(!isEmpIDValidated){
            new Alert(Alert.AlertType.ERROR,"Invalid Employee ID").show();
            return false;
        }

        boolean isEmpNameValidated=Pattern.compile("^[A-z]{1,}$").matcher(txtEmpName.getText()).matches();
        if(!isEmpNameValidated){
            new Alert(Alert.AlertType.ERROR,"Invalid Employee Name").show();
            return false;
        }

        boolean isEmpAddressValidated=Pattern.compile("^[A-z]{1,}$").matcher(txtEmpAddress.getText()).matches();
        if(!isEmpAddressValidated){
            new Alert(Alert.AlertType.ERROR,"Invalid Employee Address").show();
            return false;
        }

        boolean isEmpContactValidated=Pattern.compile("^[0-9]{1,}$").matcher(txtEmpContact.getText()).matches();
        if(!isEmpContactValidated){
            new Alert(Alert.AlertType.ERROR,"Invalid Employee Contact").show();
            return false;
        }

        boolean isEmpEmailValidated=Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$").matcher(txtEmpEmail.getText()).matches();
        if(!isEmpEmailValidated){
            new Alert(Alert.AlertType.ERROR,"Invalid Employee Email").show();
            return false;
        }

        boolean isEmpPositionValidated=Pattern.compile("^[A-z]{1,}$").matcher(txtEmpPosition.getText()).matches();
        if(!isEmpPositionValidated){
            new Alert(Alert.AlertType.ERROR,"Invalid Employee Position").show();
            return false;
        }

        return true;
    }
    public void initialize() {
        // Initialization logic can go here
        searchAndFillFields(EmployeeFormController.empID);
    }
    public void searchAndFillFields(String empId){
        var model=new EmployeeModel();

        try {
            EmployeeDto dto=model.searchEmployee(empId);

            if(dto!=null){
                fillFields(dto);
            }
            else{
                new Alert(Alert.AlertType.INFORMATION,"Employee not Found").show();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void fillFields(EmployeeDto dto) {
        txtEmpID.setText(dto.getEmpId());
        txtEmpName.setText(dto.getEmpName());
        txtEmpEmail.setText(dto.getEmail());
        txtEmpPosition.setText(dto.getPosition());
        txtEmpAddress.setText(dto.getAddress());
        txtEmpContact.setText(dto.getContact());
    }

}
