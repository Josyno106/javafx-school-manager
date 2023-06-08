package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

import static sample.students_controller.add_stage;
import static sample.students_controller.selected_student_id;

/**
 * Created by Xtian Dynageek on 6/22/2017.
 */
public class student_finance_controller  extends  common implements Initializable {

    @FXML
    private JFXTabPane tabs;

    @FXML
    private VBox paid_vbox;

    @FXML
    private Text paid_fee_label;

    @FXML
    private VBox term_vbox;

    @FXML
    private Text term_label;

    @FXML
    private VBox year_vbox;

    @FXML
    private Text year_label;

    @FXML
    private VBox owed_vbox;

    @FXML
    private Text owed_label;

    @FXML
    private HBox quick_summary_hbox;

    @FXML
    private Text student_name_label;

    @FXML
    private JFXButton finance_cance_button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /**
         * adding depth to components
         */

        set_depth_of_component(5,year_vbox);
        set_depth_of_component(5,paid_vbox);
        set_depth_of_component(5,term_vbox);
        set_depth_of_component(5,owed_vbox);
        set_depth_of_component(5,quick_summary_hbox);

        /**
         *end of setting depth to components
         */

          finance_cance_button.setOnAction(event -> {
               add_stage.close();
          });

          get_student_info_from_provided_id(selected_student_id,student_name_label);
          term_label.setText(String.valueOf(selected_term));
          year_label.setText(String.valueOf(selected_year));

    }


}
