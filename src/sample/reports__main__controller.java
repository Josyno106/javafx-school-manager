package sample;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

import static sample.students_controller.add_stage;

/**
 * Created by Xtian Dynageek on 6/11/2017.
 */
public class reports__main__controller extends common implements Initializable  {


    @FXML
    private TextField home_search_field;

    @FXML
    private TableView<?> all_students_table;

    @FXML
    private TableColumn<?, ?> id_col;

    @FXML
    private TableColumn<?, ?> first_name_col1;

    @FXML
    private TableColumn<?, ?> last_name_col;

    @FXML
    private TableColumn<?, ?> subjects_pareent_col;

    @FXML
    private TableColumn<?, ?> english_col;

    @FXML
    private TableColumn<?, ?> composition_col;

    @FXML
    private TableColumn<?, ?> maths_col;

    @FXML
    private TableColumn<?, ?> science_col;

    @FXML
    private TableColumn<?, ?> kiswahili_col;

    @FXML
    private TableColumn<?, ?> insha_col;

    @FXML
    private TableColumn<?, ?> ss_col;

    @FXML
    private TableColumn<?, ?> cre_col;

    @FXML
    private TableColumn<?, ?> total_col;

    @FXML
    private TableColumn<?, ?> pos_col;


    @FXML
    private JFXButton all_studs_button;

    @FXML
    private JFXButton add_student_button;

    @FXML
    private JFXButton finance_button;

    @FXML
    private JFXButton home_button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /**
         * set the menu button sample.icons here
         */

        set_sub_menu_button_style("Home","icons/home.png",home_button);
        set_sub_menu_button_style("Select","icons/all_studs.png",all_studs_button);
        set_sub_menu_button_style("add student","icons/add2.png",add_student_button);
        set_sub_menu_button_style("Finance","icons/Books-icon-1.png",finance_button);

        /**
         * this section sets the actions for the reports menu buttons
         */

        home_button.setOnAction(event -> {
            home();

        });
      add_student_button.setOnAction(event -> {
            add_student();
      });
    }
}
