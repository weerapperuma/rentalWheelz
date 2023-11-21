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
import lk.penguin.rentalWheelz.db.DbConnection;
import lk.penguin.rentalWheelz.dto.CustomerDto;
import lk.penguin.rentalWheelz.dto.EmployeeDto;
import lk.penguin.rentalWheelz.dto.tm.CustomerTM;
import lk.penguin.rentalWheelz.dto.tm.EmployeeTM;
import lk.penguin.rentalWheelz.model.CustomerModel;
import lk.penguin.rentalWheelz.model.EmployeeModel;
import lk.penguin.rentalWheelz.util.Navigation;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class CustomerFormController {

    @FXML
    private TableColumn<?, ?> colCustAddress;

    @FXML
    private TableColumn<?, ?> colCustContact;

    @FXML
    private TableColumn<?, ?> colCustID;

    @FXML
    private TableColumn<?, ?> colCustName;

    @FXML
    private TableColumn<?, ?> colCustUserID;

    @FXML
    private TableView<CustomerTM> tblCustomer;

    @FXML
    private TextField txtCustId;
    public static String custID;

    @FXML
    void btnCustDelete(ActionEvent event) {
        boolean isEmpSavedValidated=validateCustomer();

        //Navigation.closePane();
        if(isEmpSavedValidated){
            String id=txtCustId.getText();
            CustomerModel model=new CustomerModel();

            try {
                boolean isDeleted = model.deleteCustomer(id);
                if(isDeleted){
                    new Alert(Alert.AlertType.CONFIRMATION,"Customer Deleted Successfully").show();
                    loadAllCustomers();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
        }
    }
    public void initialize(){
        setCellValueFactory();
        loadAllCustomers();
    }

    private void setCellValueFactory() {
        colCustID.setCellValueFactory(new PropertyValueFactory<>("custId"));
        colCustName.setCellValueFactory(new PropertyValueFactory<>("custName"));
        colCustAddress.setCellValueFactory(new PropertyValueFactory<>("custAddress"));
        colCustContact.setCellValueFactory(new PropertyValueFactory<>("custContact"));
        colCustUserID.setCellValueFactory(new PropertyValueFactory<>("custUserID"));
    }

    private void loadAllCustomers() {
        CustomerModel model=new CustomerModel();

        try {
            ObservableList<CustomerTM> oblist = FXCollections.observableArrayList();
            List<CustomerDto> list = model.getAllCustomers();

            for (CustomerDto dto : list) {
                CustomerTM customerTM = new CustomerTM(dto.getCustId(),
                        dto.getCustName(),
                        dto.getCustAddress(),
                        dto.getCustContact(),
                        dto.getCustUserID()
                );
                oblist.add(customerTM);
            }
            tblCustomer.setItems(oblist);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void btnCustSave(ActionEvent event) throws IOException {
        boolean isCustomerValidated=validateCustomer();

        //Navigation.closePane();
        if(isCustomerValidated){
            Navigation.switchPaging2(GlobalFormController.getInstance().pagingPane,"custSaveForm.fxml");
        }
    }

    private boolean validateCustomer() {
        String idText = txtCustId.getText();

        boolean isCustomerIDValidated = Pattern.matches("[C][0-9]{3,}", idText);
        if (!isCustomerIDValidated) {

            new Alert(Alert.AlertType.ERROR, "Invalid Customer ID!").show();
            return false;
        }
        custID=idText;
        return true;
    }

    @FXML
    void btnCustSearch(ActionEvent event) throws IOException {
        boolean isCustomerValidated=validateCustomer();

        //Navigation.closePane();
        if(isCustomerValidated){
            //var model=new EmployeeModel();
            Navigation.switchPaging2(GlobalFormController.getInstance().pagingPane,"custSearchForm.fxml");
        }
    }

    @FXML
    void btnCustUpdate(ActionEvent event) throws IOException {
        boolean isCustomerValidated=validateCustomer();

        //Navigation.closePane();
        if(isCustomerValidated){
            //var model=new EmployeeModel();
            Navigation.switchPaging2(GlobalFormController.getInstance().pagingPane,"custSearchForm.fxml");
        }
    }

    @FXML
    void btnEmpClear(ActionEvent event) {
        txtCustId.clear();
    }

    @FXML
    void btnCustPrint(ActionEvent event) throws JRException, SQLException {
        //HashMap hashMap=new HashMap<>();
        //hashMap.put("employee","E001");

        InputStream resourceAsStream=getClass().getResourceAsStream("/reports/Blank_A4_3.jrxml");
        JasperDesign load= JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
        JasperExportManager.exportReportToPdfFile(jasperPrint,"C:\\Users\\sdwee\\OneDrive\\Documents\\whatsapp\\newreport3.pdf");
    }

}
