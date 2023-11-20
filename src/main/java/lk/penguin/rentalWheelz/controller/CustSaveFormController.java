package lk.penguin.rentalWheelz.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.penguin.rentalWheelz.dto.CustomerDto;
import lk.penguin.rentalWheelz.dto.EmployeeDto;
import lk.penguin.rentalWheelz.model.CustomerModel;
import lk.penguin.rentalWheelz.model.EmployeeModel;
import lk.penguin.rentalWheelz.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;

public class CustSaveFormController {

    @FXML
    private TextField txtCustAddress;

    @FXML
    private TextField txtCustContact;

    @FXML
    private TextField txtCustID;

    @FXML
    private Label txtCustIdShow;

    @FXML
    private TextField txtCustName;

    @FXML
    private TextField txtCustUserID;

    @FXML
    void btnCustSaveBack(ActionEvent event) throws IOException {
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "customerForm.fxml");
    }

    @FXML
    void btnCustSaveClear(ActionEvent event) {
        txtCustID.clear();
        txtCustName.clear();
        txtCustAddress.clear();
        txtCustContact.clear();
        txtCustUserID.clear();
    }

    @FXML
    void btnCustSaveSaveOnAction(ActionEvent event) {
        String id= txtCustID.getText();
        String name=txtCustName.getText();
        String address = txtCustAddress.getText();
        String contact = txtCustContact.getText();
        String userID = txtCustUserID.getText();

        CustomerDto dto = new CustomerDto(id,name,address,contact,userID);
        CustomerModel model= new CustomerModel();

        try {
            boolean isSaved = model.savedCustomer(dto);

            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Employee Saved Successfully").show();
                //EmployeeFormController.loadAllEmployees();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    public void initialize(){
        txtCustID.setText(CustomerFormController.custID);
        txtCustIdShow.setText((CustomerFormController.custID));
    }
}
