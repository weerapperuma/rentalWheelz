package lk.penguin.rentalWheelz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppInitializer extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader.load(this.getClass().getResource("/view/loginPageForm.fxml"));

        Scene scene=new Scene(FXMLLoader.load(this.getClass().getResource("/view/loginPageForm.fxml")));
        primaryStage.setResizable(false);
        primaryStage.setTitle("RentalWheelz by penguin coperation");
        primaryStage.centerOnScreen();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
