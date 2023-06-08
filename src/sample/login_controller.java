package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Timer;

import static java.lang.Thread.sleep;


/**
 * Created by Xtian Dynageek on 5/25/2017.
 */
public class login_controller extends common implements Initializable {
    // /setting the database credentials

    ///////////////////////////////////////////////////////////

    public String db_user = "root";
    public String db_pass = "";

    //////databse connection here/////////////////////////////////
    private ResultSet resultSet;
    public java.sql.Connection connection;
    public java.sql.Statement command;
    public String connector = "jdbc:mysql://localhost:3306/muchatha";
    /////////////////////////////////////////////////////////



    @FXML
    private StackPane dialog_stack_pane;

    @FXML
    private JFXButton login_button;

    @FXML
    private TextField username;

    @FXML
    private JFXButton close_button;

    @FXML
    private PasswordField password;

    @FXML
    private JFXSpinner progress_bar;


    //globals

    private  String normal_button_style="-fx-background-color:transparent;-fx-border-color:floralwhite;-fx-border-radius:20";
    private  String hovered_button_style="-fx-background-color:#00264F;-fx-border-color:#B8C2D3;-fx-text-fill:white;-fx-border-width:2;-fx-border-radius:20";


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        /**
         * set the view of the spinner as gone first
         */

        progress_bar.setOpacity(0);




        change_hover_property_on_binding(login_button);
        close_button.setOnAction(e->{
             System.exit(0);
        });


         login_button.setOnAction(handles_the_validation->{
               if (!username.getText().isEmpty() || !password.getText().isEmpty()){

                   if (!(username.getText().trim().length()<5)){
                         if (!(password.getText().trim().length()<6)){
                              String name = username.getText().trim();
                              String pass = password.getText().trim();


                                         try {
                                             connection = DriverManager.getConnection(connector, db_user, db_pass);
                                             command = connection.createStatement();
                                             resultSet = command.executeQuery("SELECT* FROM users WHERE username='"+name+"' AND password='"+pass+"'");

                                             if (resultSet.first()){
                                                 Parent root = null;
                                                 try {
                                                     main_stage.close();
                                                     main_parent = FXMLLoader.load(getClass().getResource("gui/home.fxml"));
                                                     root = main_parent;
                                                     if(!(root==null)){
                                                         Scene scene = new Scene(root);
                                                         home_stage = new Stage();
                                                         home_stage.setScene(scene);
                                                         home_stage.setMaximized(true);
                                                         home_stage.initStyle(StageStyle.DECORATED);
                                                         home_stage.setTitle("Muchatha Primary School Ms");

                                                         main_parent.setCenter(FXMLLoader.load(getClass().getResource("gui/home2.fxml")));

                                                         home_stage.show();
                                                     } else {
                                                         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                         alert.setContentText(null);
                                                         alert.showAndWait();
                                                     }

                                                 } catch (IOException e) {
                                                     e.printStackTrace();
                                                 }
                                             }else {
                                                 Alert alert  = new Alert(Alert.AlertType.ERROR);
                                                 alert.setHeaderText("Wrong password or username!");
                                                 alert.setContentText(null);
                                                 alert.showAndWait();
                                             }
                                         } catch (SQLException e) {
                                           error_or_confirm_dialog("No database Connection!\nPlease start the database then try again!",dialog_stack_pane);
                                         }

                         }else {
                             error_or_confirm_dialog("Sorry! The Password provided was too short.\n The password should have more than 6 characters",dialog_stack_pane);
                         }
                   }else {
                        error_or_confirm_dialog("Sorry! The username provided was too short.\n The username should have more than 5 characters",dialog_stack_pane);
                   }


               }else {

                   error_or_confirm_dialog("Both Username and Password are \n Required!",dialog_stack_pane);


               }



         });

        //setting the validators for the username and password fields







    }


    private void change_hover_property_on_binding(final javafx.scene.Node node){
        node.styleProperty().bind(Bindings
                .when(node.hoverProperty())
                .then(new SimpleStringProperty(hovered_button_style))
                .otherwise(new SimpleStringProperty(normal_button_style))
        );
    }

}
