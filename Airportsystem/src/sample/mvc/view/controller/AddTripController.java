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
public class AddTripController implements Initializable {

    private SwitchScene sw = new SwitchScene();

    private MyAlert myAlert = new MyAlert();

    private LocalFileStorage local = new LocalFileStorage();

    private Airplane airplane;
    private Flight flight;


    private ObservableList<String> pstrCountryList = FXCollections.observableArrayList();
    private ObservableList<String> cityMatchesCountryList = FXCollections.observableArrayList();
    private ObservableList<String> countryList = FXCollections.observableArrayList();
    private ObservableList<String> airportList = FXCollections.observableArrayList();
    private ObservableList<String> pstrLocationList = FXCollections.observableArrayList();
    private ObservableList<String> infoList = FXCollections.observableArrayList();
    private DataStorage dbh = new DBHandler();
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



        Trip trip = new Trip(Double.parseDouble(priceField.getText()), dateField.getValue().toString(),
                dbh.getLastFlightId() + 1, airplane.getPassengerCapacity());

        String choiceFrom = pstrLocation.getSelectionModel().getSelectedItem();
        from = dbh.getLocationIdFromPstrId(Integer.parseInt(choiceFrom));

        Location location = new Location(toAirportField.getValue(), toCityField.getValue(), toCountryField.getValue());

        String choiceTo = dbh.getLocationId(location);
        to = Integer.parseInt(choiceTo);


        if (dateField.getValue().toString() != null) {
            dbh.insertFlight(local.readFlightFromFile(), false);// FIXME: 2017-05
            dbh.insertTrip(trip, from, to);
        } else {
            myAlert.noDateError();
        }

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
    private void returnToAdmin(ActionEvent ae) {


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Flight for this trip has not been inserted to DataStorage yet(therefor +1)
        flightChoice.setText(String.valueOf(dbh.getLastFlightId() + 1));

        airplane = local.readAirplaneFromFile();

        ticketAmount.setText(String.valueOf(airplane.getPassengerCapacity()));
        model.setText(airplane.getModel());
        maxSpeed.setText(airplane.getMaxSpeed());

        pstrLocationList = dbh.getPSTR();


        pstrLocation.setItems(pstrLocationList);






    }




    @FXML
    private void pickCountryFieldTo(MouseEvent me) {
        countryList = dbh.getCountries();

        toCountryField.setItems(countryList);

    }

    @FXML
    private void pickCityFieldTo(MouseEvent me) {
        String SelectedCountry = toCountryField.getValue();

        cityMatchesCountryList = dbh.getCities(SelectedCountry);

        toCityField.setItems(cityMatchesCountryList);

    }

    @FXML
    private void pickAirportFieldTo(MouseEvent me) {
        String selectedCity = toCityField.getValue();

        airportList = dbh.getAirports(selectedCity);

        toAirportField.setItems(airportList);

    }

    @FXML
    private void getLocationInfo() {
        DataStorage dbHandler = new DBHandler();

        String choice = pstrLocation.getSelectionModel().getSelectedItem();
        infoList = dbHandler.getPSTRLocationInfo(choice);
        locationInfo.setText(infoList.toString().replace("[", "").replace
                ("]", ""));
    }



}
