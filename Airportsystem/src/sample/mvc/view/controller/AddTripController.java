package sample.mvc.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import sample.mvc.model.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Stefan on 2017-04-20.
 */
public class AddTripController extends ControllerModelObject implements Initializable {


    private Airplane airplane;
    private Flight flight;


    private ObservableList<String> pstrCountryList = FXCollections.observableArrayList();

    private String choice;
    private String userType = dbh.printUserType(local.getCurrentUsersUserName());


    @FXML
    private ListView<String> pstrLocation;

    @FXML
    private TextArea locationInfo;


    @FXML
    private Button addButton;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextField priceField;

    @FXML
    private ComboBox<String> toCityField;
    @FXML
    private ComboBox<String> toCountryField;


    @FXML
    private ComboBox<String> toAirportField;

    @FXML
    private TextField flightChoice;

    @FXML
    private TextField ticketAmount;

    @FXML
    private TextField model;

    @FXML
    private TextField maxSpeed;

    @FXML
    private void addTrip(ActionEvent ae) {

        int from;
        int to;
        if (toAirportField.getSelectionModel().getSelectedItem() != null) {
            if (pstrLocation.getSelectionModel().getSelectedItem() != null) {

                if (dateField.getValue() != null) {

                    if (priceField.getText().matches("^\\d+$")) {


                        Trip trip = new Trip(Double.parseDouble(priceField.getText()), dateField.getValue().toString(), dbh.getLastFlightId() + 1,
                                airplane.getPassengerCapacity());

                        String choiceFrom = pstrLocation.getSelectionModel().getSelectedItem();
                        from = dbh.getLocationIdFromPstrId(Integer.parseInt(choiceFrom));

                        Location location = new Location(toAirportField.getValue(), toCityField.getValue(), toCountryField.getValue());

                        String choiceTo = dbh.getLocationId(location);
                        to = Integer.parseInt(choiceTo);


                        dbh.insertFlight(local.readFlightFromFile(), false);
                        dbh.insertTrip(trip, from, to);
                        if (userType.matches("Admin")) {
                            sw.GoTo(ae, "Admin.fxml");
                        }
                        if (userType.matches("Customer")) {
                            sw.GoTo(ae, "Customer.fxml");
                        }
                        if (userType.matches("Employee")) {
                            sw.GoTo(ae, "Employee.fxml");
                        }

                    } else {
                        myAlert.noPriceError();
                    }
                } else {
                    myAlert.noDateError();
                }
            } else {
                myAlert.noOriginEror();
            }
        } else {
            myAlert.noToCountryCityAirportError();
        }


    }

    @FXML
    private void returnToAdmin(ActionEvent ae) {
        sw.GoTo(ae, "AddFlight.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Flight for this trip has not been inserted to DataStorage yet(therefor +1)
        flightChoice.setText(String.valueOf(dbh.getLastFlightId() + 1));

        airplane = local.readAirplaneFromFile();

        ticketAmount.setText(String.valueOf(airplane.getPassengerCapacity()));
        model.setText(airplane.getModel());
        maxSpeed.setText(airplane.getMaxSpeed());

        ObservableList<String> pstrLocationList = dbh.getPSTR();

        pstrLocation.setItems(pstrLocationList);

    }


    @FXML
    private void pickCountryFieldTo(MouseEvent me) {
        ObservableList<String> countryList = dbh.getCountries();

        toCountryField.setItems(countryList);

    }

    @FXML
    private void pickCityFieldTo(MouseEvent me) {
        String SelectedCountry = toCountryField.getValue();

        ObservableList<String> cityMatchesCountryList = dbh.getCities(SelectedCountry);

        toCityField.setItems(cityMatchesCountryList);

    }

    @FXML
    private void pickAirportFieldTo(MouseEvent me) {
        String selectedCity = toCityField.getValue();

        ObservableList<String> airportList = dbh.getAirports(selectedCity);

        toAirportField.setItems(airportList);

    }

    @FXML
    private void getLocationInfo() {
        DataStorage dbHandler = new DBHandler();

        String choice = pstrLocation.getSelectionModel().getSelectedItem();
        ObservableList<String> infoList = dbHandler.getPSTRLocationInfo(choice);
        locationInfo.setText(infoList.toString().replace("[", "").replace
                ("]", ""));
    }

    @FXML
    private void helpFunction(ActionEvent ae) {
        myAlert.addTripHelp();
    }
}
