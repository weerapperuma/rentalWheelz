package lk.penguin.rentalWheelz.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.penguin.rentalWheelz.util.Navigation;

import java.io.IOException;

public class CarSaveFormController {

    @FXML
    private TextField txtCarID;

    @FXML
    private TextField txtCarName;

    @FXML
    private Label txtEmpIdShow;

    @FXML
    void btnCarSaveBack(ActionEvent event) throws IOException {
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "carForm.fxml");
    }

    @FXML
    void btnCarSaveClear(ActionEvent event) {

    }

    @FXML
    void btncarSaveSaveOnAction(ActionEvent event) {

    }

}
