package sample.mvc.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    private Billing billing = new Billing();
    private SwitchScene sw = new SwitchScene();

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
    @FXML
    private TextField luggageField;
    private Booking booking;
    private Trip trip;
    private Flight flight;
    private Airplane airplane;
    private int tripId;
    private int systemId;
    private int flightId;


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


        bookingListView.setItems(tripInfoList);

        flightId = trip.getFlightID();

        flight = dbh.getFlightObject(flightId);

        airplane = dbh.getAirplaneObject(flight.getRegNumber());

        tripInfoList.add("Date: " + trip.getDate() + "\nTicket price: " + trip.getTripPrice() +
                "\nLuggage Max (kilo): " + airplane.getMaxLuggageWeight());


        totalPriceArea.setText("Child 0-10 50% of ticket billing");

        systemId = dbh.getIdFromUserName(local.getCurrentUsersUserName()); // Get systemID of the current user
        balanceLabel.setText(dbh.getBalanceFromId(systemId) + "  sek");


    }

    @FXML
    private void setChildTicket(MouseEvent me) {

        int ticketAmount;

        ticketAmount = childTicket.getValue();

        totalPriceArea.setText("Child tickets: " + ticketAmount + "\nBilling: " + ticketAmount + "*" +
                String.valueOf(billing.getChildPrice(trip.getTripPrice()) + "= " +
                        billing.getChildTotalPrice(trip.getTripPrice(), ticketAmount)));

    }

    @FXML
    private void setAdultTicket(MouseEvent me) {

        int ticketAmount;

        ticketAmount = adultTicket.getValue();

        totalPriceArea.setText("Adult tickets: " + ticketAmount + "\nBilling: " + ticketAmount + "*" +
                String.valueOf(trip.getTripPrice()) + "= " +
                billing.getAdultTotalPrice(trip.getTripPrice(), ticketAmount));


    }

    @FXML
    private void returnToSearchLocation(ActionEvent ae) {

        sw.GoTo(ae, "SearchLocation.fxml");

    }


}
