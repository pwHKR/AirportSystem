package sample.mvc.view.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.mvc.model.*;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by woojen on 2017-04-20.
 */
public class NewPSTRController extends ControllerModelObject implements Initializable {


    @FXML
    private TextField LabelIdPSTR;
    @FXML
    private ComboBox<String> city;

    @FXML
    private ComboBox<String> country;
    @FXML
    private ComboBox<String> airport;


    @FXML
    private void addPSTRLocation(ActionEvent ae) {
        String locationId;
        String idPSTR;


        if (airport.getValue() != null) {
            Location location = new Location(airport.getValue(), city.getValue(), country.getValue());

            locationId = dbh.getLocationId(location);

            idPSTR = LabelIdPSTR.getText();

            dbh.AddLocationPSTR(idPSTR, locationId);


            myAlert.requestSent();


            sw.GoTo(ae, "/Admin.fxml");
        } else {
            myAlert.noAirportCityCountryError();
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        LabelIdPSTR.setText(String.valueOf(dbh.getMaxPstrId() + 1));

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
        ObservableList<String> countryList = dbh.getCountries();

        country.setItems(countryList);


    }

    private void setCitiesLogic() {

        String SelectedCountry = country.getValue();


        ObservableList<String> cityList = dbh.getCities(SelectedCountry);

        city.setItems(cityList);


    }

    private void setAirportLogic() {


        String SelectedCity = city.getValue();


        ObservableList<String> airportList = dbh.getAirports(SelectedCity);

        airport.setItems(airportList);


    }

    @FXML
    private void helpFunction(ActionEvent ae) {
        myAlert.newPSTRHelp();
    }
}


