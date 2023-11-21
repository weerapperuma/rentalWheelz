package lk.penguin.rentalWheelz.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lk.penguin.rentalWheelz.dto.RentDto;
import lk.penguin.rentalWheelz.model.CarRentModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;


public class RentFormController {


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
    private TextField txtRentEndingDay;

    @FXML
    private Label txtRentId;

    @FXML
    private TextField txtRentStartingDay;

    @FXML
    private TextField txtRentTotal;
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
        rentDto.setEndingDate(txtRentEndingDay.getText());
        rentDto.setTotalAmount(Double.parseDouble(txtRentTotal.getText()));

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

}
