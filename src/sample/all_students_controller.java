package sample;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.StackPane;
import others.Students_template;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Xtian Dynageek on 6/9/2017.
 */
public class all_students_controller extends common implements Initializable {
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

    private ObservableList<Students_template> students = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        ///////////////////////////////tableview set up here///////////////////////////////////


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


        /**
         * despite being duplicated, these methods must remain the same as those in the other students class
         *
         */
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



        /**
         * end of duplicated method codes
         */



        all_students_table.setItems(students);
        all_students_table.setEditable(true);


        populate_table_with_all_students(students);
        sort_table(home_search_field,all_students_table,students);







    }
}
