package sample.mvc.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import sample.mvc.model.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by woojen on 2017-05-11.
 */
public class NewBookingController implements Initializable {

    private LocalFileStorage local = new LocalFileStorage();
    private DataStorage dbh = new DBHandler();
    private Price price = new Price();

    private ObservableList<String> tripInfoList = FXCollections.observableArrayList();
    private ObservableList<Integer> oneToNineteenList = FXCollections.observableArrayList();
    private ObservableList<Integer> zeroToNineteenList = FXCollections.observableArrayList();
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

        for (int i = 0; i < 20; i++) {

            zeroToNineteenList.add(i);
        }

        for (int i = 1; i < 20; i++) {

            oneToNineteenList.add(i);
        }


        childTicket.setItems(zeroToNineteenList);
        adultTicket.setItems(oneToNineteenList);

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

    @FXML
    private void setChildTicket(MouseEvent me) {

        int ticketAmount;

        ticketAmount = childTicket.getSelectionModel().getSelectedItem();

        totalPriceArea.setText("Child tickets: " + ticketAmount + "\nPrice: " + ticketAmount + "*" +
                String.valueOf(price.getChildPrice(trip.getTripPrice()) + "= " +
                        price.getChildTotalPrice(trip.getTripPrice(), ticketAmount)));

    }

    @FXML
    private void setAdultTicket(MouseEvent me) {

        int ticketAmount;

        ticketAmount = adultTicket.getSelectionModel().getSelectedItem();

        totalPriceArea.setText("Adult tickets: " + ticketAmount + "\nPrice: " + ticketAmount + "*" +
                String.valueOf(trip.getTripPrice()) + "= " +
                price.getAdultTotalPrice(trip.getTripPrice(), ticketAmount));


    }


}
