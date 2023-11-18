package lk.penguin.rentalWheelz.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lk.penguin.rentalWheelz.dto.EmployeeDto;
import lk.penguin.rentalWheelz.model.EmployeeModel;

public class EmpSaveFormController {

    @FXML
    private TextField txtEmpAddress;

    @FXML
    private TextField txtEmpContact;

    @FXML
    private TextField txtEmpEmail;

    @FXML
    private TextField txtEmpID;

    @FXML
    private TextField txtEmpName;

    @FXML
    private TextField txtEmpPosition;

    @FXML
    void btnEmpSaveBack(ActionEvent event) {

    }

    @FXML
    void btnEmpSaveSaveOnAction(ActionEvent event) {
        String id=txtEmpID.getText();
        String name=txtEmpName.getText();
        String address = txtEmpAddress.getText();
        String contact = txtEmpContact.getText();
        String email = txtEmpEmail.getText();
        String position = txtEmpPosition.getText();

        EmployeeDto dto = new EmployeeDto(id,name,email,position,address,contact);
        EmployeeModel model= new EmployeeModel();


    }

}
