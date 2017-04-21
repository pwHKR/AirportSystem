package sample.mvc.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import sample.mvc.model.DBHandler;
import sample.mvc.model.DataStorage;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by woojen on 2017-04-20.
 */
public class AddDestinationController implements Initializable {


    ObservableList<String> countryList = FXCollections.observableArrayList();
    ObservableList<String> cityList = FXCollections.observableArrayList();


    @FXML
    ChoiceBox<String> city;

    @FXML
    ComboBox<String> country;
    @FXML
    ChoiceBox<String> airport;

    @FXML
    private void addDestination() {


        DataStorage dbh = new DBHandler();


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DataStorage dbHandler = new DBHandler();

        // country box code

        countryList = dbHandler.getCountries();


        country.setItems(countryList);

        // city box code

        cityList = dbHandler.getCities();
        city.setItems(cityList);


    }
}


