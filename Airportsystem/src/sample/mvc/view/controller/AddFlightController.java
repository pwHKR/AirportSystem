package sample.mvc.view.controller;

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
    private String userType = dbh.printUserType(local.getCurrentUsersUserName());

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


    private String choice;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> airPlanes = dbh.getAllAirplaneRegNumbers();
        regField.setItems(airPlanes);

    }

    @FXML
    private void getAirplaneInfo() {
        String choice = regField.getSelectionModel().getSelectedItem();
        //airPlaneInfoList = dbh.getAirplaneInfo(choice);
        //airplaneList = dbHandler.getAirplaneRegNumber(choice);
        ObservableList<String> airPlaneInfoList = dbh.getAirplaneInfo(choice);
        informationField.setText(airPlaneInfoList.toString().replace("[", "").replace("]", "")
                + airPlaneInfoList.toString().replace("[", "").replace("]", ""));

    }

    @FXML
    private void returnToAdmin(ActionEvent ae) {
        if (userType.matches("Admin")) {
            sw.GoTo(ae, "Admin.fxml");
        }
        if (userType.matches("Customer")) {
            sw.GoTo(ae, "Customer.fxml");
        }
        if (userType.matches("Employee")) {
            sw.GoTo(ae, "Employee.fxml");
        }
    }

    @FXML
    private void addFlight(ActionEvent ae) {

        boolean noError = true;

        if (gateField.getText().isEmpty() || regField.getSelectionModel().isEmpty()) {

            myAlert.noGateOrPlaneError();

            noError = false;
        }

        boolean newFlightStatus;

        newFlightStatus = statusCheckBox.isSelected();

        if (noError) {

            Flight flight = new Flight(statusField.getText(), gateField.getText(), regField.getSelectionModel().getSelectedItem());
            //dbh.insertFlight(flight, newFlightStatus);
            local.saveFlightToFile(flight);
            Airplane airplane = dbh.getAirplaneObject(flight.getRegNumber());
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

