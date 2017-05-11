package sample.mvc.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import sample.mvc.model.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by woojen on 2017-05-11.
 */
public class NewBookingController implements Initializable {

    private LocalFileStorage local = new LocalFileStorage();
    private DataStorage dbh = new DBHandler();

    private ObservableList<String> tripInfoList = FXCollections.observableArrayList();
    private ObservableList<Integer> oneToTwentyList = FXCollections.observableArrayList();
    private ObservableList<Integer> zeroToTwentyList = FXCollections.observableArrayList();
    @FXML
    private ListView<String> bookingListView;
    @FXML
    private TextArea totalPriceArea;
    @FXML
    private ComboBox<Integer> adultTicket;
    @FXML
    private ComboBox<Integer> childTicket;
    @FXML
    private Label balanceLabel;
    private Booking booking;
    private Trip trip;
    private int tripId;
    private int systemId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for (int i = 0; i == 20; i++) {

            zeroToTwentyList.add(i);
        }

        for (int i = 1; i == 20; i++) {

            oneToTwentyList.add(i);
        }


        childTicket.setItems(zeroToTwentyList);
        adultTicket.setItems(oneToTwentyList);

        booking = local.readBookingIdFromFile();
        tripId = booking.getTripId();

        String stringTripId = String.valueOf(tripId);

        trip = dbh.getTripObject(stringTripId);

        tripInfoList.add("Date: " + trip.getDate() + "\nTicket price: " + trip.getTripPrice());

        bookingListView.setItems(tripInfoList);

        totalPriceArea.setText("Child 0-10 50% of ticket price");

        systemId = dbh.getIdFromUserName(local.getCurrentUsersUserName()); // Get systemID of the current user
        balanceLabel.setText(dbh.getBalanceFromId(systemId) + "  sek");

    }

    
}
