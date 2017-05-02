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
 * Created by Tobias Åkesson on 2017-05-02.
 */
public class AddFlightController implements Initializable {
    @FXML
    private Button addButton;
    @FXML
    private Button returnButton;
    @FXML
    private TextArea informationField;
    @FXML
    private ListView<String> regField;

    private ObservableList<String> airPlaneInfoList = FXCollections.observableArrayList();

    private ObservableList<String> airPlanes = FXCollections.observableArrayList();
    DataStorage dbh = new DBHandler();
    SwitchScene sw = new SwitchScene();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        airPlanes = dbh.getAllAirplaneRegNumbers();
        regField.setItems(airPlanes);

    }

    @FXML
    private void getAirplaneInfo() {
        String choice = regField.getSelectionModel().getSelectedItem();
        //airPlaneInfoList = dbh.getAirplaneInfo(choice);
        //airplaneList = dbHandler.getAirplaneRegNumber(choice);
        airPlaneInfoList = dbh.getAirplaneInfo(choice);
        informationField.setText(airPlaneInfoList.toString().replace("[", "").replace("]", "")
                + airPlaneInfoList.toString().replace("[", "").replace("]", ""));

    }

    @FXML
    public void returnToAdmin(ActionEvent ae) {
        sw.GoTo(ae, "Admin.fxml");
    }

    @FXML
    public void addFlight(ActionEvent ae) {

    }
}
