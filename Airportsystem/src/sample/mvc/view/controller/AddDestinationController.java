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
import java.io.Serializable;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;


/**
 * Created by woojen on 2017-04-20.
 */
public class AddDestinationController implements Initializable, Serializable {


    private ObservableList<String> countryList = FXCollections.observableArrayList();
    private ObservableList<String> cityList = FXCollections.observableArrayList();
    private ObservableList<String> airportList = FXCollections.observableArrayList();


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

        Node node = (Node) ae.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Admin.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

    private void setCountriesLogic() {

        DataStorage dbh = new DBHandler();


        countryList = dbh.getCountries();


        country.setItems(countryList);


    }

    private void setCitiesLogic() {

        String SelectedCountry = country.getValue();


        DataStorage dbh = new DBHandler();


        cityList = dbh.getCities(SelectedCountry);

        city.setItems(cityList);


    }

    private void setAirportLogic() {


        String SelectedCity = city.getValue();


        DataStorage dbh = new DBHandler();


        airportList = dbh.getAirports(SelectedCity);

        airport.setItems(airportList);


    }


}


