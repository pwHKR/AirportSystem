package sample.mvc.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.mvc.model.DBHandler;
import sample.mvc.model.DataStorage;
import sample.mvc.model.Destination;
import sample.mvc.model.MyAlert;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by woojen on 2017-04-20.
 */
public class AddDestinationController implements Initializable {


    ObservableList<String> countryList = FXCollections.observableArrayList();
    ObservableList<String> cityList = FXCollections.observableArrayList();
    ObservableList<String> airportList = FXCollections.observableArrayList();


    @FXML
    ComboBox<String> city;

    @FXML
    ComboBox<String> country;
    @FXML
    ComboBox<String> airport;

    @FXML
    private void addDestination(ActionEvent ae) {
        String destinationId;
        DataStorage dbh = new DBHandler();

        Destination destination = new Destination(airport.getValue(), city.getValue(), country.getValue());

        destinationId = dbh.getDestinationId(destination); // Denna måste till AddTripController

        MyAlert.requestSent();


        Node node = (Node) ae.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../AddTrip.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);






    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DataStorage dbHandler = new DBHandler();

        // country box code

        countryList = dbHandler.getCountries();


        country.setItems(countryList);


    }

    // Get the selected country from the combox and sends its to the db handler
    @FXML
    public void setCities(MouseEvent mouseEvent) {

        String SelectedCountry = country.getValue();


        DataStorage dbh = new DBHandler();


        cityList = dbh.getCities(SelectedCountry);

        city.setItems(cityList);


    }

    @FXML
    public void setAirports(MouseEvent mouseEvent) {

        String SelectedCity = city.getValue();


        DataStorage dbh = new DBHandler();


        airportList = dbh.getAirports(SelectedCity);

        airport.setItems(airportList);


    }

    @FXML
    private void logout(ActionEvent ae) {

        Node node = (Node) ae.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Login.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }


}


