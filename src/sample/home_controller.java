package sample;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static sample.home2_controller.about;
import static sample.students_controller.students_stack_pane;


public class home_controller extends common implements Initializable{
    @FXML
    private BorderPane parent_borderpane;

    @FXML
    private Label help_label;

    @FXML
    private Label license_label;

    @FXML
    private Label developer_label;

    @FXML
    private Label about_label;
    @FXML
    private Label institution_label;

    public  static  String flag="home";


    private String normal_label_style = "-fx-text-fill:floralwhite;";
    private String hovered_label_style = "-fx-text-fill:white;-fx-background-color:#0D3867;-fx-padding:2";

    private ArrayList<Label> all_labels= new ArrayList<>();


   // #EFE49E
    private AnchorPane about_anchor_pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            about_anchor_pane = FXMLLoader.load(getClass().getResource("gui/about.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        all_labels.add(help_label);
      all_labels.add(license_label);
      all_labels.add(developer_label);
      all_labels.add(about_label);



         for (int i =0; i<all_labels.size();i++){
             change_label_on_hover(all_labels.get(i));
         }



        about_label.setOnMouseClicked(event -> {
         if (!(event.getButton()== MouseButton.SECONDARY)){

             if (flag=="home"){

                 set_about(about_anchor_pane,about);
             }else if(flag=="students"){

                 set_about(about_anchor_pane,students_stack_pane);

             }

         }



        });


        //setting actions for the home buttons





    }

    private  void change_label_on_hover(Node node){


                node.styleProperty().bind(
                        Bindings
                                .when(node.hoverProperty())
                                .then(new SimpleStringProperty(hovered_label_style))
                                .otherwise(new SimpleStringProperty(normal_label_style))
                );




    }




}
