package sample;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import others.Students_template;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.home_controller.flag;

/**
 * Created by Xtian Dynageek on 5/26/2017.
 */
public class students_controller extends common implements Initializable{


    @FXML
    private JFXButton all_studs_button;

    @FXML
    private JFXButton add_student_button;


    @FXML
    private JFXButton finance_button;

    @FXML
    private JFXButton home_button;

    @FXML
    private TextField home_search_field;

    @FXML
    private TableView<Students_template> all_students_table;

    @FXML
    private TableColumn<Students_template, Integer> id_col;

    @FXML
    private TableColumn<Students_template, String> first_name_col;

    @FXML
    private TableColumn<Students_template, String> last_name_col;

    @FXML
    private TableColumn<Students_template, Integer> date_of_birth_coll;

    @FXML
    private TableColumn<Students_template, Integer> entry_col;

    @FXML
    private TableColumn<Students_template, String> class_col;
    @FXML
    private StackPane dialog_stack_pane;



    @FXML
    private TableColumn<Students_template, String> contact_col;
    @FXML
    private BorderPane students_home_menu;


    public  static  Stage add_stage;/*   this is the stage that adds the user to the stage  */
    public static  StackPane students_stack_pane;
    public  static  int selected_student_id;
    public  static  String selected_student_name;




    private ObservableList<Students_template> students = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        students_stack_pane=dialog_stack_pane;
        flag = "students";

    set_sub_menu_button_style("Home","icons/home.png",home_button);
    set_sub_menu_button_style("all students","icons/all_studs.png",all_studs_button);
    set_sub_menu_button_style("add student","icons/add2.png",add_student_button);
    set_sub_menu_button_style("Finance","icons/Books-icon-1.png",finance_button);

///////////////////////////////tableview set up here///////////////////////////////////


        id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        first_name_col.setCellValueFactory(new PropertyValueFactory<>("f_name"));
        last_name_col.setCellValueFactory(new PropertyValueFactory<>("l_name"));
        date_of_birth_coll.setCellValueFactory(new PropertyValueFactory<>("birth"));
        class_col.setCellValueFactory(new PropertyValueFactory<>("clas"));
        entry_col.setCellValueFactory(new PropertyValueFactory<>("entry"));
        contact_col.setCellValueFactory(new  PropertyValueFactory<>("conact"));



        /**
         * setting the update database methods for the different columns here
         *
         */

        first_name_col.setCellFactory(TextFieldTableCell.forTableColumn());
        last_name_col.setCellFactory(TextFieldTableCell.forTableColumn());
        class_col.setCellFactory(TextFieldTableCell.forTableColumn());
        contact_col.setCellFactory(TextFieldTableCell.forTableColumn());


        first_name_col.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Students_template, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Students_template, String> event) {

                Students_template student = all_students_table.getSelectionModel().getSelectedItem();
                student.setF_name(event.getNewValue());

                update_database_on_edit_commit(first_name_col,event.getNewValue(),student.getId());
                success_dialog("Student details successfully updated!",dialog_stack_pane);
                students.clear();
                populate_table_with_all_students(students);




            }
        });

        last_name_col.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Students_template, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Students_template, String> event) {
                Students_template s = all_students_table.getSelectionModel().getSelectedItem();
                update_database_on_edit_commit(last_name_col,event.getNewValue(),s.getId());
                success_dialog("Student details successfully updated!",dialog_stack_pane);
                students.clear();
                populate_table_with_all_students(students);
            }
        });
     class_col.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Students_template, String>>() {
         @Override
         public void handle(TableColumn.CellEditEvent<Students_template, String> event) {
             Students_template s = all_students_table.getSelectionModel().getSelectedItem();
             update_database_on_edit_commit(class_col,event.getNewValue(),s.getId());
             success_dialog("Details successfully update",dialog_stack_pane);
             students.clear();
             populate_table_with_all_students(students);
         }
     });
        contact_col.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Students_template, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Students_template, String> event) {
                Students_template s = all_students_table.getSelectionModel().getSelectedItem();
                update_database_on_edit_commit(contact_col,event.getNewValue(),s.getId());
                success_dialog("Details successfully updated",dialog_stack_pane);
                students.clear();
                populate_table_with_all_students(students);
            }
        });





        all_students_table.setItems(students);
        all_students_table.setEditable(true);


        populate_table_with_all_students(students);



      sort_table(home_search_field,all_students_table,students);






        //set the buttons actions here

        home_button.setOnAction(event -> {
            flag="home";
            home();

        });

        add_student_button.setOnAction(show_adding_window->{
            add_student();
        });


        all_studs_button.setOnAction(show_all_students_again  ->{

            try {
                students_home_menu.setCenter(FXMLLoader.load(getClass().getResource("gui/all_students.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }


        });

        finance_button.setOnAction(show_finance_window->{

             if (!all_students_table.getSelectionModel().isEmpty()){
                  selected_student_id=students.get(all_students_table.getSelectionModel().getSelectedIndex()).getId();
                  selected_student_name=students.get(all_students_table.getSelectionModel().getSelectedIndex()).getF_name();
                  prompt_for_date_and_year_dialog(students_stack_pane);
             }else {
                 error_or_confirm_dialog("Please select a student first",students_stack_pane);
             }

        });

    }



    private void repond_to_edit(String name){


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success ");
        alert.setHeaderText("Hello  "+ name);
        alert.setContentText("Your request completed successfully");
        alert.showAndWait();
    }

}
