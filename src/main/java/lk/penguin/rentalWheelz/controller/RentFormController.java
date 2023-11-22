package lk.penguin.rentalWheelz.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lk.penguin.rentalWheelz.dto.RentDto;
import lk.penguin.rentalWheelz.model.CarRentModel;
import lk.penguin.rentalWheelz.model.RentModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;


public class RentFormController implements Initializable {


    @FXML
    private TableColumn<?, ?> colEmpAddress;

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
    private TextField txtRentAmount;

    @FXML
    private TextField txtRentCarId;

    @FXML
    private TextField txtRentCustId;

    @FXML
    private Label txtRentId;

    @FXML
    private TextField txtRentStartingDay;

    @FXML
    private TextField txtRentTotal;

    @FXML
    private DatePicker dpEndingDate;


    @FXML
    private JFXComboBox<String> cmbCustomerId;

    RentModel rentModel = new RentModel();
    ArrayList<String[]> carDetails=new ArrayList<>();

    @FXML
    void btnRentAddToCart(ActionEvent event) {
        String[] details=new String[3];
        details[0]=txtRentCarId.getText();
        details[1]=txtRentAmount.getText();
        details[2]="Rented";

        carDetails.add(details);
        if(txtRentTotal.getText().isEmpty()){
            txtRentTotal.setText("0");
        }
        double total = Double.parseDouble(txtRentTotal.getText())+Double.parseDouble(txtRentAmount.getText());
        txtRentTotal.setText(String.valueOf(total));

    }

    @FXML
    void dpEndingDayOnAction(ActionEvent event) {

    }

    @FXML
    void btnRentClear(ActionEvent event) {

    }

    @FXML
    void btnRentDelete(ActionEvent event) {

    }

    @FXML
    void btnRentSave(ActionEvent event) {
        RentDto rentDto=new RentDto();
        //rentDto.setRentId(txtRentId.getText());
        Random in=new Random();
        int rand=in.nextInt(10000);
        rentDto.setRentId(String.valueOf("R"+rand));;

        rentDto.setCustId(txtRentCustId.getText());
        rentDto.setStartingDate(txtRentStartingDay.getText());
        rentDto.setEndingDate(String.valueOf(dpEndingDate.getValue()));

        if (txtRentTotal.getText() != null && !txtRentTotal.getText().isEmpty()) {
            System.out.println("Null");
        }
        rentDto.setTotalAmount(Double.parseDouble(txtRentAmount.getText()));

        rentDto.setCardetails(carDetails);

        CarRentModel carRentModel=new CarRentModel();
        try {
            carRentModel.placeRent(rentDto);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnRentSupOrder(ActionEvent event) {

    }

    @FXML
    void btnRentUpdate(ActionEvent event) {

    }

    public void setDataInComboBox() throws SQLException {
        ArrayList<String> customerIds = rentModel.getAllCustomerId();
        cmbCustomerId.getItems().addAll(customerIds);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setDataInComboBox();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
