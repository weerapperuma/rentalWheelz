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
import lk.penguin.rentalWheelz.dto.CarDto;
import lk.penguin.rentalWheelz.dto.CustomerDto;
import lk.penguin.rentalWheelz.dto.EmployeeDto;
import lk.penguin.rentalWheelz.dto.tm.EmployeeTM;
import lk.penguin.rentalWheelz.model.CarModel;
import lk.penguin.rentalWheelz.model.CustomerModel;
import lk.penguin.rentalWheelz.model.EmployeeModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class CarFormController {

    @FXML
    private TableColumn<?, ?> colCarID;

    @FXML
    private TableColumn<?, ?> colCarName;

    @FXML
    private TableColumn<?, ?> colCarStatus;

    @FXML
    private TableView<CarTM> tblSupplier;

    @FXML
    private TextField txtCarId;

    @FXML
    private TextField txtCarName;

    @FXML
    void btnCarDelete(ActionEvent event) throws SQLException {
        String id = txtCarId.getText();

        var carModel = new CarModel();
        boolean isDeleted = carModel.deleteCar(id);

        if(isDeleted) {
            tblSupplier.refresh();
            new Alert(Alert.AlertType.CONFIRMATION, "car deleted!").show();
        }
    }

    @FXML
    void btnCarSave(ActionEvent event) throws SQLException {
        String id = txtCarId.getText();
        String name = txtCarName.getText();
        String availability = "Available";
        var dto = new CarDto(id, name, availability);

        var model = new CarModel();
        boolean isSaved = model.saveNewCar(dto);
        if (isSaved) {
            System.out.println("hello");
            new Alert(Alert.AlertType.CONFIRMATION, "car saved!").show();
            clearFields();
        }
    }
    void clearFields() {
        txtCarId.setText("");
        txtCarName.setText("");
    }

    @FXML
    void btnCarSearch(ActionEvent event) {
        boolean isCarSavedValidated=validateCarId();

        if(isCarSavedValidated){
            searchCar();
        }
    }

    private void searchCar() {
        String id = txtCarId.getText();

        var model = new CarModel();
        try {
            CarDto dto = model.searchCar(id);

            if(dto != null) {
                fillFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "car not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void fillFields(CarDto dto) {
        txtCarId.setText(dto.getCarId());
        txtCarName.setText(dto.getCarCategory());
    }


    private boolean validateCarId() {
        boolean isCarIdValidated= Pattern.compile("^(V)[0-9]{1,3}$").matcher(txtCarId.getText()).matches();
        if(!isCarIdValidated){
            new Alert(Alert.AlertType.ERROR,"Invalid Car ID").show();
            return false;
        }
        return true;
    }

    @FXML
    void btnCarUpdate(ActionEvent event) throws SQLException {
        boolean isCustSavedValidated=validateCarId();

        if(isCustSavedValidated){
            updateCar();
        }
    }

    @FXML
    void btnEmpClear(ActionEvent event) throws SQLException {
        boolean isCarSavedValidated=validateCarId();

        if(isCarSavedValidated){
            System.out.println("in the update");
            updateCar();
        }
    }

    private void updateCar() throws SQLException {
        String id = txtCarId.getText();
        String name = txtCarName.getText();
        String status="Available";

        var dto = new CarDto(id, name, status);

        var model = new CarModel();
        boolean isUpdated = model.updateCar(dto);
        System.out.println(isUpdated);
        if(isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "car updated!").show();
        }
    }
    public void initialize(){
        setCellValueFactory();
        loadAllCars();
    }

    private void setCellValueFactory() {
        colCarID.setCellValueFactory(new PropertyValueFactory<>("carId"));
        colCarName.setCellValueFactory(new PropertyValueFactory<>("carCategory"));
        colCarStatus.setCellValueFactory(new PropertyValueFactory<>("carStatus"));
    }

    private void loadAllCars() {
        CarModel model=new CarModel();
        try {
            ObservableList<CarTM> oblist = FXCollections.observableArrayList();
            List<CarDto> list = model.getAllCars();
            for (CarDto dto : list) {
                CarTM carTM = new CarTM(
                        dto.getCarId(),
                        dto.getCarCategory(),
                        dto.getCarStatus()
                );
                oblist.add(carTM);
            }
            //TableView<CarTM> tblSupplier = new TableView<>();
            tblSupplier.setItems(oblist);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
