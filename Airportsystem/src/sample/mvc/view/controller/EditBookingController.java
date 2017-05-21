package sample.mvc.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import sample.mvc.model.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Stefan on 2017-05-13.
 */
public class EditBookingController extends ControllerModelObject implements Initializable {


    @FXML
    private CheckBox foodCheckBox;

    private int boolToTinyInt;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Booking booking = local.readBookingObjectFromFile();

        foodCheckBox.setSelected(booking.isHasFood());

    }

    @FXML
    private void updateBookingInfo(ActionEvent ae) {
        Booking booking = local.readBookingObjectFromFile();

        if (foodCheckBox.isSelected() == true)
            boolToTinyInt = 1;
        else
            boolToTinyInt = 0;

        sw.GoTo(ae, "ViewBookings.fxml");

        dbh.updateHasFood(foodCheckBox.isSelected(), booking.getTripId());
    }

    @FXML
    private void cancelBooking(ActionEvent ae) {
        Booking booking = local.readBookingObjectFromFile();

        Boolean choice = myAlert.confirmCancelBooking();

        if (choice == true) {
            dbh.cancelBookingId(booking.getTripId());
            dbh.setBalance(Double.parseDouble(dbh.getBalanceFromId(dbh.getIdFromUserName(local.getCurrentUsersUserName()))) + booking.getPrice(), dbh.getIdFromUserName(local.getCurrentUsersUserName()));

            dbh.setTicketAmount((dbh.getTicketAmount(booking.getTripId()) + booking.getPassengers()), booking.getTripId()); //Adds the canceled tickets to total amount of tickets for the trip

            sw.GoTo(ae, "ViewBookings.fxml");
        }

    }

    @FXML
    private void returnToViewBooking(ActionEvent ae) {
        sw.GoTo(ae, "ViewBookings.fxml");
    }

    @FXML
    private void helpFunction(ActionEvent ae) {
        myAlert.editBookingHelp();
    }
}
