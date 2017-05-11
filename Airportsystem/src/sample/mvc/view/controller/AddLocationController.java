package sample.mvc.view.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import sample.mvc.model.*;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;


/**
 * Created by woojen on 2017-04-20.
 */
public class AddLocationController implements Initializable {

    private MyAlert myAlert = new MyAlert();
    private SwitchScene sw = new SwitchScene();


    @FXML
    private ComboBox<String> city;

    @FXML
    private ComboBox<String> country;
    @FXML
    private ComboBox<String> airport;

    private Path path = Paths.get("dID.bin");

    @FXML
    private void addDestination(ActionEvent ae) {
        String destinationId;
        DataStorage dbh = new DBHandler();

        Location destination = new Location(airport.getValue(), city.getValue(), country.getValue());

        destinationId = dbh.getLocationId(destination); // Denna måste till AddTripController

        myAlert.requestSent();


        sw.GoTo(ae, "/AddTrip.fxml");


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setCountriesLogic();


    }

    @FXML
    private void updateCountry(ActionEvent ae) {

        // If country combo box s value is changed after a city already
        // has been set, the city combo box will update as well


        if (city.getValue() != null) {


            setCitiesLogic();

        }

        // If city s combo box s value is changed after a airport already
        // has been set, the airport combo box  will update as well


        if (airport.getValue() != null) {

            setAirportLogic();


        }
    }

    @FXML
    private void updateCity(ActionEvent ae) {

        if (airport.getValue() != null) {

            setAirportLogic();


        }

    }


    @FXML
    private void setCities(MouseEvent mouseEvent) {

        setCitiesLogic();

    }


    @FXML
    private void setAirports(MouseEvent mouseEvent) {

        setAirportLogic();

    }

    @FXML
    private void returnToAdmin(ActionEvent ae) {

        sw.GoTo(ae, "Admin.fxml");

    }

    private void setCountriesLogic() {

        DataStorage dbh = new DBHandler();


        ObservableList<String> countryList = dbh.getCountries();


        country.setItems(countryList);


    }

    private void setCitiesLogic() {

        String SelectedCountry = country.getValue();


        DataStorage dbh = new DBHandler();


        ObservableList<String> cityList = dbh.getCities(SelectedCountry);

        city.setItems(cityList);


    }

    private void setAirportLogic() {


        String SelectedCity = city.getValue();


        DataStorage dbh = new DBHandler();


        ObservableList<String> airportList = dbh.getAirports(SelectedCity);

        airport.setItems(airportList);


    }


}


