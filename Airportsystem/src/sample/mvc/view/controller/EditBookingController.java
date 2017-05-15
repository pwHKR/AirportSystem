package sample.mvc.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import sample.mvc.model.*;


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
    DataStorage dbh = new DBHandler();
    MyAlert myAlert = new MyAlert();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Booking booking = local.readBookingObjectFromFile();
        System.out.println(booking.getTripId());
        bookingInfoArea.setText("");


    }

    @FXML
    private void cancelBooking(ActionEvent ae) {
        Booking booking = local.readBookingObjectFromFile();

        Boolean choice = myAlert.confirmCancelBooking();

        if (choice == true) {
            dbh.cancelBookingId(booking.getTripId());
            dbh.setBalance(Double.parseDouble(dbh.getBalanceFromId(dbh.getIdFromUserName(local.getCurrentUsersUserName()))) + booking.getPrice(), dbh.getIdFromUserName(local.getCurrentUsersUserName()));
            sw.GoTo(ae, "ViewBookings.fxml");
        }

    }

    @FXML
    private void returnToViewBooking(ActionEvent ae) {
        sw.GoTo(ae, "ViewBookings.fxml");
    }
}
