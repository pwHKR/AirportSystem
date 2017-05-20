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
public class AddFlightController extends ControllerModelObject implements Initializable {


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
    private ListView<String> regField;

    @FXML
    TextField ticketAmount;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> airPlanes = dbh.getAllAirplaneRegNumbers();
        regField.setItems(airPlanes);

    }

    @FXML
    private void getAirplaneInfo() {
        String choice = regField.getSelectionModel().getSelectedItem();
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

        if (noError) {

            Flight flight = new Flight("On Time", gateField.getText(), regField.getSelectionModel().getSelectedItem());
            local.saveFlightToFile(flight);
            Airplane airplane = dbh.getAirplaneObject(flight.getRegNumber());
            local.saveAirplaneToFile(airplane);

            sw.GoTo(ae, "AddTrip.fxml");
        }
        gateField.clear();
    }

}

