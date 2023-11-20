package lk.penguin.rentalWheelz.util;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.penguin.rentalWheelz.controller.GlobalFormController;

import java.io.IOException;

public class Navigation {
    private static Parent rootNode;
    private static Scene scene;
    private static Stage stage;

    public static void switchNavigation(String path, ActionEvent event) throws IOException {
        rootNode= FXMLLoader.load(Navigation.class.getResource("/view/"+path));

        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();

        scene =new Scene(rootNode);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    public static void switchPaging(Pane pane, String path) throws IOException {
        pane.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(Navigation.class.getResource("/view/" + path));

        Parent root=loader.load();
        pane.getChildren().add(root);

    }
    public static void closePane(){
        GlobalFormController.getInstance().pagingPane.getChildren().clear();
        GlobalFormController.getInstance().pagingPane.setVisible(false);
    }
    public static void switchPaging2(Pane pane, String path) throws IOException {
        //pane.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(Navigation.class.getResource("/view/" + path));

        Parent root=loader.load();
        pane.getChildren().add(root);

    }
}
