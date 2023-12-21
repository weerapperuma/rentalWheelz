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

public class CustSearchFormController {

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
    private TextField txtCustUserId;

    @FXML
    void btnCustSearchBack(ActionEvent event) throws IOException {
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "customerForm.fxml");
    }

    @FXML
    void btnCustUpdateClear(ActionEvent event) {
        txtCustName.clear();
        txtCustAddress.clear();
        txtCustContact.clear();
        txtCustUserId.clear();
    }

    @FXML
    void btnCustUpdateOnAction(ActionEvent event) throws SQLException {
        boolean isCustSavedValidated=validateCustSave();

        if(isCustSavedValidated){
            updateCustomer();
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
        boolean isCustUserIDValidated= Pattern.compile("^(U)[0-9]{1,3}$").matcher(txtCustUserId.getText()).matches();
        if(!isCustUserIDValidated){
            new Alert(Alert.AlertType.ERROR,"Invalid User ID").show();

            return false;
        }

        return true;
    }

    private void updateCustomer() throws SQLException {
        String id= txtCustID.getText();
        String name=txtCustName.getText();
        String address = txtCustAddress.getText();
        String contact = txtCustContact.getText();
        String userId = txtCustUserId.getText();

        var dto = new CustomerDto(id,name,address,contact,userId);
        var model= new CustomerModel();

        boolean isUpdated = model.updateCustomer(dto);

        if(isUpdated){
            new Alert(Alert.AlertType.CONFIRMATION,"Customer Updated Successfully").show();
            //EmployeeFormController.loadAllEmployees();
        }
    }
    public void initialize() {
        // Initialization logic can go here
        searchAndFillFields(CustomerFormController.custID);
    }

    private void searchAndFillFields(String custID) {
        var model=new CustomerModel();

        CustomerDto dto= null;
        try {
            dto = model.searchCustomer(custID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(dto!=null){
            fillFields(dto);
        }
        else{
            new Alert(Alert.AlertType.INFORMATION,"Customer not Found").show();
        }

    }

    private void fillFields(CustomerDto dto) {
        txtCustID.setText(dto.getCustId());
        txtCustName.setText(dto.getCustName());
        txtCustAddress.setText(dto.getCustAddress());
        txtCustContact.setText(dto.getCustContact());
        txtCustUserId.setText(dto.getCustUserID());
    }

}
