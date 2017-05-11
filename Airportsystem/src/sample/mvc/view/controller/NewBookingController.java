package sample.mvc.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    private Trip trip;
    private double totalTripPrice;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        for (int i = 0; i < 10; i++) {

            zeroToNineteenList.add(i);
        }

        for (int i = 0; i < 10; i++) {

            oneToNineteenList.add(i);
        }


        childTicket.setItems(zeroToNineteenList);
        adultTicket.setItems(oneToNineteenList);

        childTicket.setValue(0);
        adultTicket.setValue(0);

        Booking booking = local.readBookingIdFromFile();
        int tripId = booking.getTripId();

        String stringTripId = String.valueOf(tripId);

        trip = dbh.getTripObject(stringTripId);


        bookingListView.setItems(tripInfoList);

        int flightId = trip.getFlightID();

        Flight flight = dbh.getFlightObject(flightId);

        Airplane airplane = dbh.getAirplaneObject(flight.getRegNumber());

        tripInfoList.add("Date: " + trip.getDate() + "\nTicket price: " + trip.getTripPrice() +
                "\nLuggage Max (kilo): " + airplane.getMaxLuggageWeight());


        totalPriceArea.setText("Child 0-10 50% of ticket billing");

        int systemId = dbh.getIdFromUserName(local.getCurrentUsersUserName());
        balanceLabel.setText(dbh.getBalanceFromId(systemId) + "  sek");


    }

    private int getChildTicket() {
        int ticketAmount;

        ticketAmount = childTicket.getValue();

        return ticketAmount;
    }

    private int getAdultTicket() {

        int ticketAmount;

        ticketAmount = adultTicket.getValue();

        return ticketAmount;
    }

    @FXML
    private void showBookingInfo() {

        totalPriceArea.setText("Adult tickets: " + getAdultTicket() + "\nBilling: " + getAdultTicket() + " X " +
                String.valueOf(trip.getTripPrice()) + "= " +
                billing.getAdultTotalPrice(trip.getTripPrice(), getAdultTicket()) +
                "\nChild tickets: " + getChildTicket() + "\nBilling: " + getChildTicket() + " X " + String.valueOf(trip.getTripPrice() / 2) + "= "
                + billing.getChildTotalPrice(trip.getTripPrice(), getChildTicket()));

        tripInfoList.clear();
        totalTripPrice = (billing.getAdultTotalPrice(trip.getTripPrice(), getAdultTicket()) + billing.getChildTotalPrice(trip.getTripPrice(), getChildTicket()));
        tripInfoList.add("Date: " + trip.getDate() + "\nTicket price: " + totalTripPrice);
        bookingListView.setItems(tripInfoList);
    }


    @FXML
    private void returnToSearchLocation(ActionEvent ae) {

        sw.GoTo(ae, "SearchLocation.fxml");

    }


}
