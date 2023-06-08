package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Xtian Dynageek on 6/25/2017.
 */
public class teachers_window_controller extends  common implements Initializable {

    @FXML
    private VBox personal_info_pane;

    @FXML
    private JFXRadioButton male_radio;

    @FXML
    private JFXRadioButton female_radio;

    @FXML
    private JFXRadioButton single_button;

    @FXML
    private JFXRadioButton married_button;

    @FXML
    private VBox photo_drag_vbox;

    @FXML
    private VBox personal_info_pane1;
    @FXML
    private JFXButton close_button;


    ToggleGroup toggleGroup,toggleGroup1;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        close_button.setOnAction(event -> {
            teachers_stage.close();
        });

        set_depth_of_component(5,personal_info_pane);
        set_depth_of_component(5,photo_drag_vbox);
        set_depth_of_component(5,personal_info_pane1);

        toggleGroup = new ToggleGroup();
        male_radio.setToggleGroup(toggleGroup);
        female_radio.setToggleGroup(toggleGroup);

        toggleGroup1=new ToggleGroup();
        single_button.setToggleGroup(toggleGroup1);
        married_button.setToggleGroup(toggleGroup1);
    }
}
