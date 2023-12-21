package lk.penguin.rentalWheelz.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.penguin.rentalWheelz.dto.EmployeeDto;
import lk.penguin.rentalWheelz.dto.SupplierDto;
import lk.penguin.rentalWheelz.dto.tm.EmployeeTM;
import lk.penguin.rentalWheelz.dto.tm.SupplierTM;
import lk.penguin.rentalWheelz.model.EmployeeModel;
import lk.penguin.rentalWheelz.model.SupplierModel;
import lk.penguin.rentalWheelz.util.Navigation;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class SupplierFormController {

    @FXML
    private TableColumn<?, ?> colSupContact;

    @FXML
    private TableColumn<?, ?> colSupID;

    @FXML
    private TableColumn<?, ?> colSupName;

    @FXML
    private TableColumn<?, ?> colSupOrderID;

    @FXML
    private TableColumn<?, ?> colSupplierAvailableCar;

    @FXML
    private TableView<SupplierTM> tblSupplier;

    @FXML
    private TextField txtSupId;
    public static String supID;

    @FXML
    void btnEmpClear(ActionEvent event) {
        txtSupId.clear();
    }

    @FXML
    void btnSupDelete(ActionEvent event) {

    }

    @FXML
    void btnSupSave(ActionEvent event) throws IOException {
        boolean isSupplierValidated=validateSupplier();

        //Navigation.closePane();
        if(isSupplierValidated){
            Navigation.switchPaging2(GlobalFormController.getInstance().pagingPane,"supplierSaveForm.fxml");
        }
    }

    private boolean validateSupplier() {
        String idText = txtSupId.getText();

        boolean isSupplierIDValidated = Pattern.matches("[S][0-9]{3,}", idText);
        if (!isSupplierIDValidated) {

            new Alert(Alert.AlertType.ERROR, "Invalid Supplier ID!").show();
            return false;
        }
        supID=idText;
        return true;
    }

    @FXML
    void btnSupSearch(ActionEvent event) {

    }

    @FXML
    void btnSupUpdate(ActionEvent event) {

    }
    public void initialize(){
        setCellValueFactory();
        loadAllSuppliers();
    }

    private void loadAllSuppliers() {
        SupplierModel model=new SupplierModel();
        try {
            ObservableList<SupplierTM> oblist = FXCollections.observableArrayList();
            List<SupplierDto> list = model.getAllSuppliers();
            for (SupplierDto dto : list) {
                SupplierTM supplierTM = new SupplierTM(
                        dto.getSupId(),
                        dto.getSupName(),
                        dto.getSupAvailableCar(),
                        dto.getSupOrderId()
                );
                oblist.add(supplierTM);
            }
            tblSupplier.setItems(oblist);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void setCellValueFactory() {
        colSupID.setCellValueFactory(new PropertyValueFactory<>("supId"));
        colSupName.setCellValueFactory(new PropertyValueFactory<>("supName"));
        colSupplierAvailableCar.setCellValueFactory(new PropertyValueFactory<>("supAvailableCar"));
        colSupOrderID.setCellValueFactory(new PropertyValueFactory<>("supOrderId"));
    }

}
