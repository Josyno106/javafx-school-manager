package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static sample.students_controller.add_stage;


/**
 * Created by Xtian Dynageek on 6/9/2017.
 */
public class add_student_controller extends common implements Initializable {
    @FXML
    private JFXTextField first_name_field;

    @FXML
    private JFXTextField last_name_field;

    @FXML
    private JFXComboBox<Integer> data_of_birth_combobox;

    @FXML
    private JFXTextField guardian_contact_field;

    @FXML
    private JFXComboBox<Integer> entry_year_combobox;

    @FXML
    private JFXTextField entry_behavior_field;

    @FXML
    private JFXComboBox<String> stream_combobox;

    @FXML
    private JFXComboBox<Integer> class_combobox;
    @FXML
    private StackPane dialog_stack_pane;

    @FXML
    private JFXButton add_button;

    @FXML
    private JFXButton cancel_button;
   private Stage closer_stage = add_stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

         cancel_button.setOnAction(close_the_adding_student_stage->{
                add_stage.close();
         });


        //setting the variables here

        ObservableList<Integer> birth_dates = FXCollections.observableArrayList(
                1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2012,2013,2014,2015
        );
        ObservableList<Integer> entry_years = FXCollections.observableArrayList(
                1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2012,2013,2014,2015,2016,2017,2018,2019,2020
        );

        ObservableList<String>  streams= FXCollections.observableArrayList(
                "Q","X","Y","Z"
        );
       ObservableList<Integer> class_levels = FXCollections.observableArrayList(1,2,3,4,5,6,7,8);
        /**
         * set the data to the comboboxes here
         */
        data_of_birth_combobox.setItems(birth_dates);
        entry_year_combobox.setItems(entry_years);
        stream_combobox.setItems(streams);
        class_combobox.setItems(class_levels);


         add_button.setOnAction(add_this_student->{

             /**
              *  starting the data validation here
              *
              */
             if (!first_name_field.getText().isEmpty()){
                    if(first_name_field.getText().trim().length()>=3){
                         if (!last_name_field.getText().isEmpty()){
                              if (last_name_field.getText().trim().length()>=3){
                                   if (!data_of_birth_combobox.getSelectionModel().isEmpty()){
                                         if (!guardian_contact_field.getText().isEmpty()){
                                             if (guardian_contact_field.getText().trim().length()>=10){
                                                    if (!entry_year_combobox.getSelectionModel().isEmpty()){
                                                           if (!entry_behavior_field.getText().isEmpty()){
                                                                if (entry_behavior_field.getText().trim().length()>=0){
                                                                      if ((Double.parseDouble(entry_behavior_field.getText().trim()))>=100){
                                                                              if (!class_combobox.getSelectionModel().isEmpty()) {
                                                                                       if (!stream_combobox.getSelectionModel().isEmpty()){

                                                                                           /**
                                                                                            * all the validation is a success, now the student is ready to be inserted into the database
                                                                                            */
                                                                                                  add_student_to_database(first_name_field.getText(),last_name_field.getText(),data_of_birth_combobox.getValue(),
                                                                                                          entry_year_combobox.getValue(),Integer.parseInt(String.valueOf(class_combobox.getValue()))
                                                                                                          ,guardian_contact_field.getText(),stream_combobox.getValue(),Double.parseDouble(entry_behavior_field.getText().trim()));
                                                                                       }else {
                                                                                            error_or_confirm_dialog("Please select the stream for the student",dialog_stack_pane);
                                                                                           stream_combobox.requestFocus();
                                                                                       }
                                                                              }else {
                                                                                   error_or_confirm_dialog("Please select a class for the student",dialog_stack_pane);
                                                                                  class_combobox.requestFocus();
                                                                              }
                                                                      }else {
                                                                           error_or_confirm_dialog("entry behavior marks must be at least 100",dialog_stack_pane);
                                                                          entry_behavior_field.requestFocus();
                                                                      }
                                                                }else {
                                                                     error_or_confirm_dialog("Sorry, you must provide the entry behavior mark",dialog_stack_pane);
                                                                    entry_behavior_field.requestFocus();
                                                                }
                                                           }else {
                                                                error_or_confirm_dialog("Please provide the entry marks of this student.\nIf the student is a transferee,\nplease enter the total mark attained\nfrom " +
                                                                        "the last exam the student sat for.\nThis value is required!",dialog_stack_pane);
                                                               entry_behavior_field.requestFocus();
                                                           }
                                                    }else {
                                                         error_or_confirm_dialog("Please select the year of entry.\nThis is the year of admission.",dialog_stack_pane);
                                                        entry_year_combobox.requestFocus();
                                                    }
                                             }else {
                                                  error_or_confirm_dialog("Contact should contain at least 10 digits!",dialog_stack_pane);
                                                 guardian_contact_field.requestFocus();
                                             }
                                         }else {
                                              error_or_confirm_dialog("Contact is required and must be \n digits only ( 0-9 )",dialog_stack_pane);
                                             guardian_contact_field.requestFocus();
                                         }
                                   }else {
                                        error_or_confirm_dialog("Please select a birth year", dialog_stack_pane);
                                       data_of_birth_combobox.requestFocus();
                                   }
                              }else {
                                   error_or_confirm_dialog("Last name too short",dialog_stack_pane);
                                  last_name_field.requestFocus();
                              }
                         }else {
                             error_or_confirm_dialog("Last name is required", dialog_stack_pane);
                             last_name_field.requestFocus();
                         }
                    }else {
                         error_or_confirm_dialog("First name too short",dialog_stack_pane);
                        first_name_field.requestFocus();
                    }
             }else {
                 error_or_confirm_dialog("First name is required",dialog_stack_pane);
                 first_name_field.requestFocus();
             }

         });


    }

    public StackPane return_stack_pane(){
         return dialog_stack_pane;
    }
}
