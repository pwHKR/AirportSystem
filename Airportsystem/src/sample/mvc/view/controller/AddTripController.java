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
import sample.mvc.model.DBHandler;
import sample.mvc.model.DataStorage;
import sample.mvc.model.SwitchScene;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Stefan on 2017-04-20.
 */
public class AddTripController implements Initializable {

    private SwitchScene sw = new SwitchScene();
    private ObservableList<Integer> flightIdList = FXCollections.observableArrayList();
    private ObservableList<String> pstrCountryList = FXCollections.observableArrayList();
    private ObservableList<String> cityMatchesCountryList = FXCollections.observableArrayList();
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
    private void addTrip() {
        //Trip trip = new Trip(Double.parseDouble(price.getText()),date.toString(),); ///// still to do

    }

    @FXML
    private void returnToAdmin(ActionEvent ae) {
        sw.GoTo(ae, "Admin.fxml");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    @FXML
    public void pickFlightId(MouseEvent me) {
        flightIdList = dbh.getAllFlightIds();
        flightChoice.setItems(flightIdList);
    }

    @FXML
    public void pickCountryFrom(MouseEvent me) {
        pstrCountryList = dbh.getCountriesWithPstr();
        fromCountryField.setItems(pstrCountryList);
    }

    @FXML
    public void pickCityFrom(MouseEvent me) {
        choice = fromCountryField.getValue();
        cityMatchesCountryList = dbh.matchCityWithCountry(choice);
        fromCityField.setItems(cityMatchesCountryList);
    }

    @FXML
    public void pickCountryFieldTo(MouseEvent me) {

    }

    @FXML
    public void pickCityFieldTo(MouseEvent me) {

    }
    //@FXML
    //public void pick
}
