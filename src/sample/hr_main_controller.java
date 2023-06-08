package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.effects.JFXDepthManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.controlsfx.control.InfoOverlay;
import org.controlsfx.control.PopOver;

import java.net.URL;
import java.util.ResourceBundle;

import static sample.common.hr_stage;

/**
 * Created by Xtian Dynageek on 6/22/2017.
 */
public class hr_main_controller extends common implements Initializable {
    @FXML
    private VBox everyone_vbox;

    @FXML
    private Pane everyone_parent_pane;

    @FXML
    private Text everyone_total;

    @FXML
    private VBox casual_laborers_vbox;

    @FXML
    private Pane casual_labourers_parent_pane;

    @FXML
    private Text casual_workers_total_text;

    @FXML
    private VBox teachers_vbox;

    @FXML
    private Pane teachers_parent_anchor_pane;

    @FXML
    private Text teachers_tota_text;

    @FXML
    private JFXButton cancel_button;
    @FXML
    private AnchorPane all_rippler_parent_pane;

    JFXRippler jfxRippler;

    JFXPopup jfxPopup1,jfxPopup2,jfxPopup3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cancel_button.setOnAction(event -> {
         hr_stage.close();
        });

        /**
         * set the depth for the components
         */
      set_depth_of_component(5,casual_laborers_vbox);
      set_depth_of_component(5,everyone_vbox);
      set_depth_of_component(5,teachers_vbox);


      /**
       * set everyone window popup
       */

      jfxPopup1 = new JFXPopup();

        JFXButton view_everyone_in_detail_button = new JFXButton("view details");
        JFXButton update_everyone_info_button = new JFXButton("update any info");

        view_everyone_in_detail_button.setStyle("-fx-background-color: floralwhite;-fx-text-fill:gray;-fx-pref-width: 300;-fx-font-style: oblique;-fx-font-size: 16");
        update_everyone_info_button.setStyle("-fx-background-color: floralwhite;-fx-text-fill:gray;-fx-pref-width: 300;-fx-font-style: oblique;-fx-font-size: 16");





        VBox container = new VBox(5);
        container.setStyle("-fx-background-color: cornsilk;-fx-pref-height: 120;-fx-pref-width: 180");
        container.getChildren().addAll(view_everyone_in_detail_button,update_everyone_info_button);
        container.setNodeOrientation(NodeOrientation.INHERIT);
        container.setAlignment(Pos.TOP_CENTER);
        container.setPadding(new Insets(5));



        jfxPopup1.setContent(container);
        jfxPopup1.setSource(everyone_parent_pane);


        everyone_vbox.setOnMouseClicked(show_popup->{
            if (show_popup.getButton()== MouseButton.SECONDARY){

                jfxPopup1.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT,show_popup.getX(),show_popup.getY());
            }

        });



         teachers_vbox.setOnMouseClicked(event -> {
              show_custom_popup(event);
         });


        casual_laborers_vbox.setOnMouseClicked(e->{


             show_casual_laborers_popup(e);

        });

    }

    private void show_custom_popup(MouseEvent event){

        /**
         * set teachers window popup
         */

        jfxPopup2 = new JFXPopup();
        JFXButton add_teacher_button = new JFXButton("add a teacher");
        JFXButton update_teacher_button= new JFXButton("update teacher details");

        add_teacher_button.setStyle("-fx-background-color: floralwhite;-fx-text-fill:gray;-fx-pref-width: 300;-fx-font-style: oblique;-fx-font-size: 16");
        update_teacher_button.setStyle("-fx-background-color: floralwhite;-fx-text-fill:gray;-fx-pref-width: 300;-fx-font-style: oblique;-fx-font-size: 16");

        add_teacher_button.setOnAction(event1 -> {
            show_teachers_home();
        });

        VBox  teacher_popup_container = new VBox(5);
        teacher_popup_container.setStyle("-fx-background-color: cornsilk;-fx-pref-height: 160;-fx-pref-width: 200");
        teacher_popup_container.getChildren().addAll(add_teacher_button,update_teacher_button);
        teacher_popup_container.setNodeOrientation(NodeOrientation.INHERIT);
        teacher_popup_container.setAlignment(Pos.TOP_CENTER);
        teacher_popup_container.setPadding(new Insets(5));



        jfxPopup2.setContent(teacher_popup_container);
        jfxPopup2.setSource(teachers_parent_anchor_pane);


            if (event.getButton()== MouseButton.SECONDARY){

                jfxPopup2.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT,event.getX(),event.getY());
            }


    }

    private void show_casual_laborers_popup(MouseEvent event){

        /**
         * set teachers window popup
         */

        jfxPopup3 = new JFXPopup();
        JFXButton add_worker = new JFXButton("add new worker");
        JFXButton update_worker_details= new JFXButton("update worker details");

        add_worker.setStyle("-fx-background-color: floralwhite;-fx-text-fill:gray;-fx-pref-width: 300;-fx-font-style: oblique;-fx-font-size: 16");
        update_worker_details.setStyle("-fx-background-color: floralwhite;-fx-text-fill:gray;-fx-pref-width: 300;-fx-font-style: oblique;-fx-font-size: 16");

        VBox  worker_popup_container = new VBox(5);
        worker_popup_container.setStyle("-fx-background-color: cornsilk;-fx-pref-height: 160;-fx-pref-width: 200");
        worker_popup_container.getChildren().addAll(add_worker,update_worker_details);
        worker_popup_container.setNodeOrientation(NodeOrientation.INHERIT);
        worker_popup_container.setAlignment(Pos.TOP_CENTER);
        worker_popup_container.setPadding(new Insets(5));



        jfxPopup3.setContent(worker_popup_container);
        jfxPopup3.setSource(casual_laborers_vbox);


        if (event.getButton()== MouseButton.SECONDARY){

            jfxPopup3.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT,event.getX(),event.getY());
        }


    }




}
