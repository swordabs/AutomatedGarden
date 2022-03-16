package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainDrive extends Application
{

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage)
    {
        try {
            FXMLLoader fxmlLoader = FXMLLoader.load(Controller.class.getResource("system.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1024, 768);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}