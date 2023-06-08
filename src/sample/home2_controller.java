package sample;


import com.jfoenix.controls.JFXButton;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * Created by Xtian Dynageek on 5/28/2017.
 */
public class home2_controller extends common implements Initializable {

    @FXML
    private TextField home_search_field;

    @FXML
    private JFXButton students_button;

    @FXML
    private JFXButton reports_button;

    @FXML
    private JFXButton human_resource_button;

    @FXML
    private JFXButton exams_button;

    @FXML
    private JFXButton library_button;

    @FXML
    private JFXButton misc_button;

    @FXML
    private StackPane dialog_stack_pane;

    public  static  StackPane about;

    private  String normal_button_style="-fx-background-color:transparent;-fx-border-color:floralwhite;";
    private  String hovered_button_style="-fx-background-color:#0B1017;-fx-border-color:#D7000C;-fx-text-fill:#D7000C;-fx-border-width:2;";

    private ArrayList<Label> all_labels= new ArrayList<>();



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        set_desired_image_and_text_n_add_style_hovering("Students","all_studs.png",students_button);

        set_desired_image_and_text_n_add_style_hovering("Reports","icons/reports.png",reports_button);
        set_desired_image_and_text_n_add_style_hovering("Exams","icons/exam_icon.jpg",exams_button);
        set_desired_image_and_text_n_add_style_hovering("HR","icons/hr.png",human_resource_button);
        set_desired_image_and_text_n_add_style_hovering("Library","icons/library.png",library_button);
        set_desired_image_and_text_n_add_style_hovering("Streams","icons/streams.png",misc_button);



         about=dialog_stack_pane;
        //setting actions for the home buttons



        students_button.setOnAction(e->{
            FXMLLoader fxmlLoader= new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("gui/students.fxml"));

            try {
                BorderPane p = fxmlLoader.load();
                main_parent.setCenter(p);

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });


        reports_button.setOnAction(request_details_first_before_proceeding->{

              request_class_and_stream_dialog(dialog_stack_pane);

        });
        human_resource_button.setOnAction(show_hr_menu->{

             hr();

        });


    }
    private void change_hover_property_on_binding(final Node node){
        node.styleProperty().bind(Bindings
                .when(node.hoverProperty())
                .then(new SimpleStringProperty(hovered_button_style))
                .otherwise(new SimpleStringProperty(normal_button_style))
        );
    }


    //setting a reusable method for setting the images in the buttons
    /**
     * this function sets the default styler for the menu buttons
     */


    private void set_desired_image_and_text_n_add_style_hovering(String button_text, String icon_path, JFXButton button ){
        button.setText(button_text);
        Image icon =  new Image(getClass().getResourceAsStream(icon_path));
        ImageView imageView = new ImageView(icon);
        imageView.setFitHeight(110);
        imageView.setFitWidth(130);
        button.setGraphic(imageView);
        button.setContentDisplay(ContentDisplay.TOP);

        //finally....add some effects here

        change_hover_property_on_binding(button);


    }

}
