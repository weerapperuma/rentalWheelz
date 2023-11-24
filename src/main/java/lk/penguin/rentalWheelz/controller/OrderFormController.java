package lk.penguin.rentalWheelz.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lk.penguin.rentalWheelz.dto.OrderDto;
import lk.penguin.rentalWheelz.model.EmployeeModel;
import lk.penguin.rentalWheelz.model.OrderModel;
import lombok.SneakyThrows;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {
    @FXML
    private ComboBox<String> Supplier;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colorderId;

    @FXML
    private TableColumn<?, ?> colsuupierId;

    @FXML
    private DatePicker date;

    @FXML
    private TextField orderId;


    public void savebtnOnActhion(ActionEvent event) throws SQLException {
        String Date = String.valueOf(date.getValue());
        OrderDto dto = new OrderDto(orderId.getText(), (String) Supplier.getValue(),Date);

        var model = new OrderModel();
        boolean isSave = model.Osave(dto);
        System.out.println(isSave);
        if (isSave){
            System.out.println("code1");
            boolean isOSave = model.Osave(dto);
            if (isOSave){
                new Alert(Alert.AlertType.INFORMATION,"Save is successful").show();
            }
        }
    }

    void loadId() throws SQLException {
        var model = new EmployeeModel();

        ObservableList<String> Id = model.LoadId();

        this.Supplier.setItems((ObservableList<String>)Id);
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadId();
    }
}
