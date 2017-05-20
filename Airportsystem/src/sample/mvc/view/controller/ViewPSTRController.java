package sample.mvc.view.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by woojen on 2017-04-25.
 */
public class ViewPSTRController extends ControllerModelObject implements Initializable {



    @FXML
    private ListView<String> pstrLocations;

    @FXML
    private TextArea locationInfo;

    @FXML
    private Button helpButton;

    @FXML
    private void getLocationInfo() {


        String choice = pstrLocations.getSelectionModel().getSelectedItem();
        ObservableList<String> infoList = dbh.getPSTRLocationInfo(choice);
        ObservableList<String> airplaneList = dbh.getAirplaneRegNumber(choice);
        locationInfo.setText(infoList.toString().replace("[", "").replace("]", "")
                + airplaneList.toString().replace("[", "").replace("]", ""));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<String> pstrLocationList = dbh.getPSTR();


        pstrLocations.setItems(pstrLocationList);


    }


    @FXML
    private void returnToAdmin(ActionEvent ae) {

        sw.GoTo(ae, "Admin.fxml");

    }


}