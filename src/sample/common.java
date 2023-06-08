package sample;

import com.jfoenix.controls.*;
import com.jfoenix.effects.JFXDepthManager;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import others.Students_template;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static sample.home2_controller.about;
import static sample.students_controller.add_stage;



public class common extends Main implements  Initializable {

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


    //setting the homestage

    public static Stage home_stage;

    public   static BorderPane main_parent;

     public static int selected_term;
     public static int selected_year;
     public  static Stage hr_stage,teachers_stage;

    JFXDepthManager depthManager;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
           System.err.println("successful");
    }

    private void change_hover_property_on_binding(final Node node){
        String normal_sub_menu="-fx-background-color:transparent;-fx-border:none";
        String hovered_sub_menu="-fx-background-color:#0B1017;-fx-border-color:crimson;-fx-text-fill:crimson;-fx-border-width:2;";
        node.styleProperty().bind(Bindings
                .when(node.hoverProperty())
                .then(new SimpleStringProperty(hovered_sub_menu))
                .otherwise(new SimpleStringProperty(normal_sub_menu))
        );
    }



    //setting a reusable method for setting the images in the buttons

    protected void set_sub_menu_button_style(String sub_menu_button_text, String sub_menu_icon_path, JFXButton sub_menu_button ){
        sub_menu_button.setText(sub_menu_button_text);
        Image sub_menu_image = new Image(getClass().getResourceAsStream(sub_menu_icon_path));
        ImageView sub_menu_image_view= new ImageView(sub_menu_image);
        sub_menu_image_view.setFitWidth(40);
        sub_menu_image_view.setFitHeight(30);
        sub_menu_button.setGraphic(sub_menu_image_view);
        sub_menu_button.setContentDisplay(ContentDisplay.TOP);

        change_hover_property_on_binding(sub_menu_button);



    }

    /**
     * this is the sample.common error dialog
     * @param message
     * @param dialog_stack_pane
     * @return
     */
    protected JFXDialog error_or_confirm_dialog(String message, StackPane dialog_stack_pane){
        JFXDialogLayout empty_selection_detected = new JFXDialogLayout();

        empty_selection_detected.setBody(new Text(message));
        JFXButton exit = new JFXButton("Cancel");
        exit.setStyle("-fx-background-color: #DE210A;-fx-text-fill:#ffffff");

        empty_selection_detected.setActions(exit);

        JFXDialog error_or_confirm_dialog = new JFXDialog(dialog_stack_pane,empty_selection_detected, JFXDialog.DialogTransition.CENTER);
        exit.setOnAction(e->{

            error_or_confirm_dialog.close();
            dialog_stack_pane.toBack();
        });
        dialog_stack_pane.toFront();
        error_or_confirm_dialog.show();

        return  error_or_confirm_dialog;
    }

    /**
     *this is the sample.common success message dialog
     * @param message
     * @param dialog_pane
     */

    protected JFXDialog success_dialog(String message, StackPane dialog_pane){
        JFXDialogLayout success = new JFXDialogLayout();

        success.setBody(new Text(message));
        JFXButton Ok = new JFXButton("OK");
        Ok.setStyle("-fx-background-color: #0E63CE;-fx-text-fill:#ffffff");

        success.setActions(Ok);

        JFXDialog success_confirm_dialog = new JFXDialog(dialog_pane,success, JFXDialog.DialogTransition.CENTER);
        Ok.setOnAction(e->{

            success_confirm_dialog.close();
            dialog_pane.toBack();
        });
        dialog_pane.toFront();
        success_confirm_dialog.show();

        return  success_confirm_dialog;
    }
/**
 * this function requests for the user to request a class and stream before proceeding to view the reports
 */

   protected  JFXDialog request_class_and_stream_dialog(StackPane stackPane){

       JFXDialogLayout set_student_request_data = new JFXDialogLayout();
       set_student_request_data.setHeading(new Text("Select requested info"));


       JFXDialog confirm_dialog = new JFXDialog(stackPane, set_student_request_data, JFXDialog.DialogTransition.CENTER);

       JFXButton proceed = new JFXButton("Proceed");
       JFXButton cancel = new JFXButton("Cancel");
       cancel.setStyle("-fx-background-color: #DE210A;-fx-text-fill:#ffffff");
       proceed.setStyle("-fx-background-color: #009D22;-fx-text-fill:#ffffff");

       ObservableList<Integer> term = FXCollections.observableArrayList(1,2,3);
       ObservableList<Integer> clas = FXCollections.observableArrayList(1,2,3,4,5,6,7,8);
       ObservableList<String> stream = FXCollections.observableArrayList("Q","X","y","Z");
       ObservableList<String>  exam= FXCollections.observableArrayList("End term","Mid Term");
       ObservableList<Integer>  year= FXCollections.observableArrayList(2017,2018,2019,2020,2021,2023,2024,2025);

       ComboBox<Integer> year_combox = new ComboBox<Integer>();
       year_combox.setPromptText("Select the year");
       year_combox.setItems(year);
       ComboBox<Integer>term_combobox = new ComboBox<Integer>(term);
       term_combobox.setPromptText("Select Term");
       ComboBox<String>exam_combobox = new ComboBox<String>(exam);
       exam_combobox.setPromptText("Select exam");
       ComboBox<Integer> classes_combobox = new ComboBox<>();
       classes_combobox.setPromptText("Select class");
       classes_combobox.setItems(clas);
       ComboBox<String> streams_combobox = new ComboBox<>();
       streams_combobox.setPromptText("Select stream");
       streams_combobox.setItems(stream);

       VBox container = new VBox(5);
       container.setAlignment(Pos.CENTER);

       cancel.setOnAction(event -> {
            confirm_dialog.close();
       });

       proceed.setOnAction(ensuring_that_required_data_has_been_provided_from_the_above_check_boxes->{
             if (!classes_combobox.getSelectionModel().isEmpty()){
                     if(!streams_combobox.getSelectionModel().isEmpty()){
                             if (!term_combobox.getSelectionModel().isEmpty()){
                                 if (!exam_combobox.getSelectionModel().isEmpty()) {
                                        if(!year_combox.getSelectionModel().isEmpty()){

                                             /**
                                              * validation successful
                                             */
                                              selected_term=term_combobox.getValue();
                                              selected_year = year_combox.getValue();
                                               redirect_to_reports_home();
                                        }else {
                                             error_or_confirm_dialog("Please select an year!",stackPane);
                                            year_combox.requestFocus();
                                        }
                                 }else {
                                      error_or_confirm_dialog("Please select an exam!",stackPane);
                                      exam_combobox.requestFocus();
                                 }
                             }else {

                                 error_or_confirm_dialog("Please select the term!",stackPane);
                             }
                     }else {
                          error_or_confirm_dialog("Please select the stream!",stackPane);
                          streams_combobox.requestFocus();
                     }
             }else {
                  error_or_confirm_dialog("Please select a class!",stackPane);
             }
       });

       container.getChildren().addAll(new Separator(),new Text("Class"),classes_combobox,new Separator(),new Text("Stream"),streams_combobox,new Separator(),new Text("Term"),
               term_combobox,new Separator(),new Text("Exam"),exam_combobox,new Separator(),new Text("Year"),year_combox,new Separator());
       set_student_request_data.setBody(container);
       set_student_request_data.setActions(cancel);

       //set the hbox for the two buttons
       HBox hBox = new HBox(20);
       hBox.getChildren().addAll(cancel,proceed);
       set_student_request_data.setActions(hBox);

       confirm_dialog.show();

     return confirm_dialog;
   }


    /**
     * this function prompts the user to select some term and year so as to see the finance information
     */

    protected  JFXDialog prompt_for_date_and_year_dialog(StackPane stackPane){

        JFXDialogLayout set_student_request_data = new JFXDialogLayout();
        JFXDialog confirm_dialog = new JFXDialog(stackPane, set_student_request_data, JFXDialog.DialogTransition.CENTER);

        JFXButton proceed = new JFXButton("Proceed");
        JFXButton cancel = new JFXButton("Cancel");
        cancel.setStyle("-fx-background-color: #DE210A;-fx-text-fill:#ffffff");
        proceed.setStyle("-fx-background-color: #009D22;-fx-text-fill:#ffffff");

        ObservableList<Integer> term = FXCollections.observableArrayList(1,2,3);
        ObservableList<Integer> year = FXCollections.observableArrayList(2017,2018,2019,2020,2021,2023,2024,2025);

        ComboBox<Integer> year_combox = new ComboBox<Integer>();
        year_combox.setPromptText("Select the year");
        year_combox.setItems(year);
        ComboBox<Integer>term_combobox = new ComboBox<Integer>(term);
        term_combobox.setPromptText("Select Term");


        VBox container = new VBox(5);
        container.setAlignment(Pos.CENTER);

        cancel.setOnAction(event -> {
            confirm_dialog.close();
        });

        proceed.setOnAction(event -> {
            if (!term_combobox.getSelectionModel().isEmpty()){
                if (!year_combox.getSelectionModel().isEmpty()){
                    selected_term=term_combobox.getValue();
                    selected_year = year_combox.getValue();
                    confirm_dialog.close();
                    show_finance_window();
                }else {
                    error_or_confirm_dialog("Please select the year!",stackPane);
                }
            }else {
                error_or_confirm_dialog("You must select the term first!",stackPane);
            }

        });

        container.getChildren().addAll(new Text("Select term"),term_combobox,new Separator(),new Text("Year"),year_combox,new Separator());
        set_student_request_data.setBody(container);

         //set the hbox for the two buttons
        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(cancel,proceed);
        set_student_request_data.setActions(hBox);

        confirm_dialog.show();

        return confirm_dialog;
    }

    /**
     * setting one query method that should be able to execute most of the queries in the database here
     * @
     */

    private void query(String query){
        try {
            connection = DriverManager.getConnection(connector, db_user, db_pass);
            command = connection.createStatement();
            command.execute(query);



    } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    /**
     * Redirect user to the homepage after they have been validated
     * @param username
     * @param password
     */

    protected void redirect_successfull_Login(String username, String password){

        try {
            connection = DriverManager.getConnection(connector, db_user, db_pass);
            command = connection.createStatement();
            resultSet = command.executeQuery("SELECT* FROM users WHERE username='"+username+"' AND password='"+password+"'");

            if (resultSet.first()){
                //do something
            }else {


                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Invalid username or password \n please try again");
                alert.setTitle("User not found");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    /**
     * set the user menu
     */

    public static void home(){

        FXMLLoader fxmlLoader= new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("gui/home2.fxml"));

        try {


            BorderPane p=fxmlLoader.load();
            main_parent.setCenter(p);



        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }



    /**
     * This function adds the student into the database upon successful validation of all the student's details
     *
     */

    protected void add_student_to_database(String firstname,String lastname,int birthyear,int entry_year,int clas,String contact,String stream,Double entry_mark){

        try {
            connection = DriverManager.getConnection(connector, db_user, db_pass);
            command = connection.createStatement();

            command.executeUpdate("insert into students(first_name,last_name,birth,entry_year,class,contact,stream,entry_mark) Values('" + firstname.trim() + "'," +
                    "'" + lastname.trim() + "','" + Integer.parseInt(String.valueOf(birthyear)) + "'," +
                    "'" +  Integer.parseInt(String.valueOf(entry_year)) + "','" + clas + "','" + contact.trim() + "','" + stream + "','"+entry_mark+"') ");

              Alert confirm = new Alert(Alert.AlertType.INFORMATION);
              confirm.setHeaderText("Student was successfully added");
              confirm.setContentText(null);
              confirm.initOwner(add_stage);
              confirm.initStyle(StageStyle.UNDECORATED);
              confirm.showAndWait();

            add_stage.close();


    } catch (SQLException e) {

            System.out.println(e.toString());
        }


    }

    /**
     * this function populates the tables view with data on the all the studentys
     *
     */

    protected void populate_table_with_all_students(ObservableList list){
        try {
            connection = DriverManager.getConnection(connector, db_user, db_pass);
            command = connection.createStatement();

            resultSet = command.executeQuery("SELECT* FROM students  ORDER BY first_name ASC");

            while (resultSet.next()){

                Students_template student = new Students_template(

                        resultSet.getString("first_name"),resultSet.getString("last_name"),resultSet.getInt("birth"),resultSet.getInt("entry_year"),(
                        resultSet.getInt("class")+" "+resultSet.getString("stream")),resultSet.getString("contact"),resultSet.getInt("id")
                );

                list.add(student);



            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     *  create a method for making updating the database upon edit_commit of the table view
     */

    protected void update_database_on_edit_commit(TableColumn columns, String new_value, int id){

             switch (columns.getId()){

                 case "first_name_col":

                     query("Update students set first_name = '"+new_value+"' where id='"+id+"'");

                     break;
                 case "last_name_col":
                     query("Update students set last_name = '"+new_value+"' where id='"+id+"'");
                     break;
                 case "date_of_birth_col":
                     System.out.println("birth col");
                     break;
                 case "entry_col":
                     System.out.println("entry col");
                     break;
                 case "class_col":
                     query("Update students set class = '"+new_value+"' where id='"+id+"'");
                     break;
                 case "contact_col":
                     query("Update students set contact = '"+new_value+"' where id='"+id+"'");



             }

    }

    /**
     *
     * this function is responsible for sorting out the students table view
     */

    protected void sort_table(TextField search_field,TableView tableView,ObservableList students){

        FilteredList<Students_template> filteredData = new FilteredList<>(students, p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        search_field.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getF_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (person.getL_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }else if(person.getConact().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;

                }
                return false; // Does not match.
            });
        });


        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Students_template> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableView.setItems(sortedData);
    }


    /**
     * this function sets the reports main menu immediately the student gets into the reports section
     */

    private void redirect_to_reports_home(){

        try {
            main_parent.setCenter(FXMLLoader.load(getClass().getResource("gui/view_reports.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * add student window
     */

    protected void add_student(){
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource("gui/new_student.fxml"));
            add_stage= new Stage();
            add_stage.initStyle(StageStyle.UNDECORATED);
            // stage.setTitle(addNewProductMenu.getText().toUpperCase());
            add_stage.initOwner(home_stage);
            add_stage.setResizable(false);
            add_stage.initModality(Modality.APPLICATION_MODAL);
            add_stage.setScene(new Scene(root));
            add_stage.show();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    /**
     * show the student finance window here
     */

     protected void show_finance_window(){
         Parent finance_root = null;
      //   Stage finance = add_stage;
         try {

             finance_root = FXMLLoader.load(getClass().getResource("gui/student_finance.fxml"));
             add_stage= new Stage();
             add_stage.initStyle(StageStyle.UNDECORATED);
             // stage.setTitle(addNewProductMenu.getText().toUpperCase());
             add_stage.initOwner(home_stage);
             add_stage.setResizable(false);
             add_stage.initModality(Modality.APPLICATION_MODAL);
             add_stage.setScene(new Scene(finance_root));
             add_stage.show();
         }catch (Exception ex){
             ex.printStackTrace();
         }
     }


    /**
     *get student details based on the selected id and set the values according to data from database
     */

      protected void get_student_info_from_provided_id(int id, Text name){
          try {
              connection = DriverManager.getConnection(connector, db_user, db_pass);
              command = connection.createStatement();

              resultSet = command.executeQuery("SELECT first_name,last_name  FROM students  WHERE id='"+id+"'");

              if (resultSet.first()){
                   name.setText(resultSet.getString("first_name") + " "+resultSet.getString("last_name"));

              }
          } catch (SQLException e) {
              e.printStackTrace();
          }
      }

    /**
     * show hr menu
     */

     protected void hr(){
         Parent hr_root = null;
           hr_stage = new Stage();
         try {

             hr_root= FXMLLoader.load(getClass().getResource("gui/hr_home.fxml"));
             hr_stage.initStyle(StageStyle.UNDECORATED);
             hr_stage.initOwner(home_stage);
             hr_stage.setResizable(false);
             hr_stage.initModality(Modality.APPLICATION_MODAL);
             hr_stage.setScene(new Scene(hr_root));
             hr_stage.show();
         }catch (Exception ex){
             ex.printStackTrace();
         }
     }

    /**
     * sett the teachers window menu here
     */

    protected void show_teachers_home(){
         teachers_stage= new Stage();
         Parent teachers_root=null;

        try{
             teachers_root= FXMLLoader.load(getClass().getResource("gui/teachers_window.fxml"));
             teachers_stage.initStyle(StageStyle.UNDECORATED);
            teachers_stage.initOwner(home_stage);
            teachers_stage.setResizable(false);
            teachers_stage.initModality(Modality.APPLICATION_MODAL);
            teachers_stage.setScene(new Scene(teachers_root));
            teachers_stage.show();
        }catch (Exception e){
             e.printStackTrace();
        }
    }

    /**
     * set the about label here
     *
     */
    protected void set_about(AnchorPane anchorPane, StackPane stackPane){
        JFXDialogLayout about_layout = new JFXDialogLayout();

        about_layout.setBody(anchorPane);

        JFXDialog about_dialog = new JFXDialog(stackPane,about_layout, JFXDialog.DialogTransition.CENTER);


            about_dialog.show();

    }


    /**
     *  depth control of the individual components and rippler
     */

    protected void set_depth_of_component(int depth, Node node){

         depthManager.setDepth(node,depth);
    }

   
}

