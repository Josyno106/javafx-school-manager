package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class Main extends Application {

    public static Stage main_stage;

    public   static Parent root=null;


    @Override
    public void start(Stage primaryStage) throws Exception{

        main_stage =primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("gui/login.fxml"));
        main_stage.setTitle("Hello World");
        main_stage.setScene(new Scene(root, 546, 703));
        main_stage.initStyle(StageStyle.UNDECORATED);
        main_stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
