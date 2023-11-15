package lk.penguin.rentalWheelz.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lk.penguin.rentalWheelz.model.UserModel;
import lk.penguin.rentalWheelz.util.Navigation;

import java.io.IOException;

public class LoginPageFormController {

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        if(UserModel.verifyCredentials(txtUserName.getText(),txtPassword.getText())){
            try {
                Navigation.switchNavigation("globalForm.fxml",event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}