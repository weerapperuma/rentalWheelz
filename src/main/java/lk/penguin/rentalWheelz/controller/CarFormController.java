package lk.penguin.rentalWheelz.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lk.penguin.rentalWheelz.util.Navigation;

import java.io.IOException;

public class CarFormController {

    @FXML
    private TableColumn<?, ?> colCarID;

    @FXML
    private TableColumn<?, ?> colCarName;

    @FXML
    private TableColumn<?, ?> colCarStatus;

    @FXML
    private TableView<?> tblSupplier;

    @FXML
    private TextField txtCarId;

    @FXML
    void btnCarDelete(ActionEvent event) {

    }

    @FXML
    void btnCarSave(ActionEvent event) throws IOException {
        Navigation.switchPaging2(GlobalFormController.getInstance().pagingPane, "carSaveForm.fxml");
    }

    @FXML
    void btnCarSearch(ActionEvent event) {

    }

    @FXML
    void btnCarUpdate(ActionEvent event) {

    }

    @FXML
    void btnEmpClear(ActionEvent event) {

    }

}
