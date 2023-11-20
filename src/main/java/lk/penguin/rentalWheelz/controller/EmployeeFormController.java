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
import lk.penguin.rentalWheelz.dto.tm.EmployeeTM;
import lk.penguin.rentalWheelz.model.EmployeeModel;
import lk.penguin.rentalWheelz.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
    private TableView<EmployeeTM> tblEmployee;

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
        boolean isEmpSavedValidated=validateEmployee();

        //Navigation.closePane();
        if(isEmpSavedValidated){
            String id=txtEmpID.getText();
            EmployeeModel model=new EmployeeModel();

            try {
                boolean isDeleted = model.deleteEmployee(id);
                if(isDeleted){
                    new Alert(Alert.AlertType.CONFIRMATION,"Employee Deleted Successfully").show();
                    loadAllEmployees();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
        }
    }

    @FXML
    void btnEmpSalary(ActionEvent event) {

    }

    @FXML
    void btnEmpSave(ActionEvent event) throws IOException {
        boolean isEmployeeValidated=validateEmployee();

       //Navigation.closePane();
        if(isEmployeeValidated){
            Navigation.switchPaging2(GlobalFormController.getInstance().pagingPane,"empSaveForm.fxml");
        }
    }

    private boolean validateEmployee() {
        String idText = txtEmpID.getText();
//        boolean isEmployeeIDValidated = Pattern.compile("[C][0-9]{3,}").matcher(idText).matches();
        boolean isEmployeeIDValidated = Pattern.matches("[E][0-9]{3,}", idText);
        if (!isEmployeeIDValidated) {

            new Alert(Alert.AlertType.ERROR, "Invalid Employee ID!").show();
            return false;
        }
        empID=idText;
        return true;
    }

    @FXML
    void btnEmpSearch(ActionEvent event) throws IOException {
        boolean isEmployeeValidated=validateEmployee();

        //Navigation.closePane();
        if(isEmployeeValidated){
            //var model=new EmployeeModel();
            Navigation.switchPaging2(GlobalFormController.getInstance().pagingPane,"employeeSearchForm.fxml");
        }
    }

    @FXML
    void btnEmpUpdate(ActionEvent event) throws IOException {
        boolean isEmployeeValidated=validateEmployee();

        //Navigation.closePane();
        if(isEmployeeValidated){
            //var model=new EmployeeModel();
            Navigation.switchPaging2(GlobalFormController.getInstance().pagingPane,"employeeSearchForm.fxml");
        }
    }
    public void initialize(){
        setCellValueFactory();
        loadAllEmployees();
    }

    private void loadAllEmployees() {
        EmployeeModel model=new EmployeeModel();
    try {
      ObservableList<EmployeeTM> oblist = FXCollections.observableArrayList();
      List<EmployeeDto> list = model.getAllEmployees();
        for (EmployeeDto dto : list) {
            EmployeeTM employeeTM = new EmployeeTM(dto.getEmpId(),
                    dto.getEmpName(),
                    dto.getEmail(),
                    dto.getPosition(),
                    dto.getAddress(),
                    dto.getContact()
            );
            oblist.add(employeeTM);
        }
        tblEmployee.setItems(oblist);
    }catch (Exception e){
    System.out.println(e.getMessage());
    }
    }

    private void setCellValueFactory() {
        colEmpID.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("empName"));
        colEmpEmail.setCellValueFactory(new PropertyValueFactory<>("empEmail"));
        colEmpPosition.setCellValueFactory(new PropertyValueFactory<>("empPosition"));
        colEmpAddress.setCellValueFactory(new PropertyValueFactory<>("empAddress"));
        colEmpContact.setCellValueFactory(new PropertyValueFactory<>("empContact"));
    }

}
