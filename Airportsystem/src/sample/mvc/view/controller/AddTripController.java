package sample.mvc.view.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Stefan on 2017-04-20.
 */
public class AddTripController implements Initializable {

    ArrayList<String> arr = new ArrayList<>();


    @FXML
    private Label destination;

    @FXML
    private DatePicker date;

    @FXML
    private TextField price;

    @FXML
    private void addTrip() {
        //Trip trip = new Trip(Double.parseDouble(price.getText()),date.toString(),); ///// still to do
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

      
    }
}
