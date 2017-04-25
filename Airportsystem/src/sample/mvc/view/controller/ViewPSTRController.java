package sample.mvc.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import sample.mvc.model.DBHandler;
import sample.mvc.model.DataStorage;
import sample.mvc.model.SwitchScene;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by woojen on 2017-04-25.
 */
public class ViewPSTRController implements Initializable {

    SwitchScene sw = new SwitchScene();

    private ObservableList<String> pstrLocationList = FXCollections.observableArrayList();
    private ObservableList<String> infoList = FXCollections.observableArrayList();

    @FXML
    private ListView<String> pstrLocations;

    @FXML
    private TextArea locationInfo;

    @FXML
    private Button helpButton;

    @FXML
    private void getLocationInfo() {
        DataStorage dbHandler = new DBHandler();

        String choice = pstrLocations.getSelectionModel().getSelectedItem();
        infoList = dbHandler.getPSTRLocationInfo(choice);
        locationInfo.setText(infoList.toString().replace("[", "").replace("]", ""));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DataStorage dbHandler = new DBHandler();

        pstrLocationList = dbHandler.getPSTR();


        pstrLocations.setItems(pstrLocationList);


    }


    @FXML
    private void returnToAdmin(ActionEvent ae) {

        sw.GoTo(ae, "Admin.fxml");

    }


}