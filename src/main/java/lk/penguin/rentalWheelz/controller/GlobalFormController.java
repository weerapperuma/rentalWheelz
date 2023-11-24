package lk.penguin.rentalWheelz.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.penguin.rentalWheelz.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GlobalFormController implements Initializable {


    @FXML
    public AnchorPane pagingPane;

    private static GlobalFormController controller;

    public GlobalFormController() {
        controller = this;
    }

    public static GlobalFormController getInstance() {
        return controller;
    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(pagingPane,"customerForm.fxml");
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(pagingPane,"employeeForm.fxml");
    }

    @FXML
    void btnHomeMouseOnExited(MouseEvent event) {

    }

    @FXML
    void btnHomeOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(pagingPane,"dashBoardForm.fxml");
    }

    @FXML
    void btnHomeOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("loginPageForm.fxml",event);
    }

    @FXML
    void btnRentOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(pagingPane,"rentForm.fxml");
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(pagingPane,"supplierForm.fxml");
    }

    @FXML
    void btnVehicleOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(pagingPane,"carForm.fxml");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Navigation.switchPaging(pagingPane,"dashBoardForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnOrderOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(pagingPane,"OrderForm.fxml");
    }

}
