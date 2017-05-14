package sample.mvc.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import sample.mvc.model.Booking;
import sample.mvc.model.LocalFileStorage;
import sample.mvc.model.SwitchScene;


import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Stefan on 2017-05-13.
 */
public class EditBookingController implements Initializable {

    @FXML
    private TextArea totalPriceArea;
    @FXML
    private TextArea bookingInfoArea;
    @FXML
    private TextField luggageField;
    @FXML
    private Label balanceLabel;

    SwitchScene sw = new SwitchScene();
    LocalFileStorage local = new LocalFileStorage();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Booking booking = local.readBookingObjectFromFile();

        bookingInfoArea.setText("");


    }

    @FXML
    private void returnToViewBooking(ActionEvent ae) {
        sw.GoTo(ae, "ViewBookings.fxml");
    }
}
