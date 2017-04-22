package sample.mvc.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.mvc.model.SwitchScene;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Stefan on 2017-04-20.
 */
public class AddTripController implements Initializable {

    SwitchScene sw = new SwitchScene();
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

    @FXML
    private void returnToAdmin(ActionEvent ae) {

        sw.GoTo(ae, "Admin.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
