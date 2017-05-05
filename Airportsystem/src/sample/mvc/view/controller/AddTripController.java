package sample.mvc.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.mvc.model.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Stefan on 2017-04-20.
 */
public class AddTripController implements Initializable {

    private SwitchScene sw = new SwitchScene();

    MyAlert myAlert = new MyAlert();

    private ObservableList<Integer> flightIdList = FXCollections.observableArrayList();
    private ObservableList<String> pstrCountryList = FXCollections.observableArrayList();
    private ObservableList<String> cityMatchesCountryList = FXCollections.observableArrayList();
    private ObservableList<String> countryList = FXCollections.observableArrayList();
    private DataStorage dbh = new DBHandler();
    private String choice;

    @FXML
    private Button addButton;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextField priceField;
    @FXML
    private ComboBox<String> fromCountryField;
    @FXML
    private ComboBox<String> fromCityField;
    @FXML
    private ComboBox<String> toCityField;
    @FXML
    private ComboBox<String> toCountryField;

    @FXML
    private ComboBox<Integer> flightChoice;

    @FXML
    private void addTrip(ActionEvent ae) {

        int locationId = dbh.getLocationId(fromCountryField.getValue(), fromCityField.getValue());
        Trip trip = new Trip(Double.parseDouble(priceField.getText()), dateField.getValue().toString(), flightChoice.getValue());


        if (dateField.getValue().toString() != null) {
            dbh.insertTrip(trip, locationId);
        } else {
            myAlert.noDateError();
        }

    }

    @FXML
    private void returnToAdmin(ActionEvent ae) {
        sw.GoTo(ae, "Admin.fxml");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    @FXML
    private void pickFlightId(MouseEvent me) {
        flightIdList = dbh.getAllFlightIds();
        flightChoice.setItems(flightIdList);


    }

    @FXML
    private void pickCountryFrom(MouseEvent me) {
        pstrCountryList = dbh.getCountriesWithPstr();
        fromCountryField.setItems(pstrCountryList);
    }

    @FXML
    private void pickCityFrom(MouseEvent me) {
        choice = fromCountryField.getValue();
        cityMatchesCountryList = dbh.matchCityWithCountry(choice);
        fromCityField.setItems(cityMatchesCountryList);

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
}
