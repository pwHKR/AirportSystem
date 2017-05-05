package sample.mvc.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.mvc.model.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Tobias Åkesson on 2017-05-02.
 */
public class AddFlightController implements Initializable {

    private MyAlert myAlert = new MyAlert();
    private DataStorage dbh = new DBHandler();
    private SwitchScene sw = new SwitchScene();
    private LocalFileStorage local = new LocalFileStorage();
    private Airplane airplane;

    @FXML
    private Button addButton;
    @FXML
    private Button returnButton;
    @FXML
    private TextArea informationField;
    @FXML
    private TextField gateField;
    @FXML
    private TextField statusField;
    @FXML
    private ListView<String> regField;

    @FXML
    private CheckBox statusCheckBox;

    @FXML
    TextField ticketAmount;


    private ObservableList<String> airPlaneInfoList = FXCollections.observableArrayList();
    private String choice;

    private ObservableList<String> airPlanes = FXCollections.observableArrayList();


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
    private void returnToAdmin(ActionEvent ae) {
        sw.GoTo(ae, "Admin.fxml");
    }

    @FXML
    private void addFlight(ActionEvent ae) {

        boolean noError = true;

        if (gateField.getText().isEmpty() || regField.getSelectionModel().isEmpty()) {

            myAlert.generalError();

            noError = false;
        }

        boolean newFlightStatus;

        if (statusCheckBox.isSelected()) {

            newFlightStatus = true;
        } else {

            newFlightStatus = false;

        }

        if (noError) {

            Flight flight = new Flight(statusField.getText(), gateField.getText(), regField.getSelectionModel().getSelectedItem());
            //dbh.insertFlight(flight, newFlightStatus);
            local.saveFlightToFile(flight);
            airplane = dbh.getAirplaneObject(flight.getRegNumber());
            local.saveAirplaneToFile(airplane);

            sw.GoTo(ae, "AddTrip.fxml");
        }

        statusField.clear();
        gateField.clear();
    }

    @FXML
    private void checkBoxInteraction(ActionEvent ae) {


        if (statusCheckBox.isSelected()) {


            statusField.setEditable(true);

        } else {

            statusField.clear();

            statusField.setEditable(false);
        }


    }

}

