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
import java.util.regex.Pattern;

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
    void btnCustSaveClear(ActionEvent event) {
        txtCustID.clear();
        txtCustName.clear();
        txtCustAddress.clear();
        txtCustContact.clear();
        txtCustUserID.clear();
    }

    @FXML
    void btnCustSaveSaveOnAction(ActionEvent event) {
        boolean isCustSavedValidated=validateCustSave();

        if(isCustSavedValidated){
            saveCustomer();
        }
    }

    private boolean validateCustSave() {
        boolean isCustIDValidated= Pattern.compile("^(C)[0-9]{1,3}$").matcher(txtCustID.getText()).matches();
        if(!isCustIDValidated){
            new Alert(Alert.AlertType.ERROR,"Invalid Customer ID").show();

            return false;
        }
        boolean isCustNameValidated = Pattern.compile("^[a-zA-Z]+( [a-zA-Z]+)?$").matcher(txtCustName.getText()).matches();
        if (!isCustNameValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid Customer Name").show();
            return false;
        }
        boolean isCustAddressValidated=Pattern.compile("^[a-zA-Z, ]{1,100}$").matcher(txtCustAddress.getText()).matches();
        if(!isCustAddressValidated){
            new Alert(Alert.AlertType.ERROR,"Invalid Customer Address").show();
            return false;
        }
        boolean isCustContactValidated=Pattern.compile("^[0-9]{1,}$").matcher(txtCustContact.getText()).matches();
        if(!isCustContactValidated){
            new Alert(Alert.AlertType.ERROR,"Invalid Customer Contact").show();
            return false;
        }
        boolean isCustUserIDValidated= Pattern.compile("^(U)[0-9]{1,3}$").matcher(txtCustUserID.getText()).matches();
        if(!isCustUserIDValidated){
            new Alert(Alert.AlertType.ERROR,"Invalid User ID").show();

            return false;
        }

        return true;
    }

    public void initialize(){
        txtCustID.setText(CustomerFormController.custID);
        txtCustIdShow.setText((CustomerFormController.custID));
    }

    private void saveCustomer(){
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
                new Alert(Alert.AlertType.CONFIRMATION,"Customer Saved Successfully").show();
                //EmployeeFormController.loadAllEmployees();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
}
