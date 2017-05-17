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
public class NewBookingForPersonController implements Initializable {

    private LocalFileStorage local = new LocalFileStorage();
    private DataStorage dbh = new DBHandler();
    private Billing billing = new Billing();
    private SwitchScene sw = new SwitchScene();
    private MyAlert myAlert = new MyAlert();
    private ObservableList<String> tripInfoList = FXCollections.observableArrayList();

    private ObservableList<Integer> zeroToTenList = FXCollections.observableArrayList();
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
    @FXML
    private Button cancelButton;
    @FXML
    private TextField ssnField;
    private Booking booking;
    private Trip trip;
    private Flight flight;
    private Airplane airplane;
    private int tripId;
    private int flightId;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        for (int i = 0; i < 10; i++) {

            zeroToTenList.add(i);
        }

        luggageField.setText("0");
        childTicket.setItems(zeroToTenList);
        adultTicket.setItems(zeroToTenList);
        balanceLabel.setText("Balance: " + (dbh.getBalanceFromId(dbh.getIdFromUserName(local.getCurrentUsersUserName()))) + " SEK");

        childTicket.setValue(0);
        adultTicket.setValue(1);

        booking = local.readBookingObjectFromFile();
        tripId = booking.getTripId();

        String stringTripId = String.valueOf(tripId);

        trip = dbh.getTripObject(stringTripId);


        bookingListView.setItems(tripInfoList);

        flightId = trip.getFlightID();

        flight = dbh.getFlightObject(flightId);

        airplane = dbh.getAirplaneObject(flight.getRegNumber());

        tripInfoList.add("Date: " + trip.getDate() + "\nTicket price: " + trip.getTripPrice() +
                "\nLuggage Max (kilo): " + airplane.getMaxLuggageWeight() + "\nChild 0-10 50% of ticket billing");

        showBookingInfo();

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
        setBilling();
        totalPriceArea.setText("Adult tickets: " + getAdultTicket() + "\nPrice: " + getAdultTicket() + " X " +
                String.valueOf(trip.getTripPrice()) + "= " +
                billing.getAdultTotalPrice(trip.getTripPrice(), getAdultTicket()) +
                " SEK\nChild tickets: " + getChildTicket() + "\nPrice: " + getChildTicket() + " X " + String.valueOf(trip.getTripPrice()) + "= "
                + billing.getChildTotalPrice(trip.getTripPrice(), getChildTicket()) + " SEK\nLuggage price: " + String.valueOf(billing.calcLuggagePrice(Double.parseDouble(luggageField.getText()))) + " SEK");
    }


    @FXML
    private void returnToSearchLocation(ActionEvent ae) {

        sw.GoTo(ae, "SearchLocation.fxml");

    }

    @FXML
    private void confirmBooking(ActionEvent ae) {


        boolean luggageMax = true;
        boolean choice; //boolean from confirm dialog
        boolean isBalance;
        int newTotalTicketAmount; // The remaining ticket amount after the booking
        int luggageMaxTot;
        String ssn = ssnField.getText();

        setBilling();

        choice = myAlert.confirmBooking(billing.getTotalPrice());

        luggageMaxTot = Integer.parseInt(luggageField.getText()) * billing.getTicketAmount();


        if (choice) {

            if (luggageMaxTot > airplane.getMaxLuggageWeight()) {
                luggageMax = false;
                myAlert.luggageMaxMsg(String.valueOf(airplane.getMaxLuggageWeight() * billing.getTicketAmount()));

            }


            if (luggageMax) {


                newTotalTicketAmount = dbh.getTicketAmount(tripId) - billing.getTicketAmount();

                dbh.setTicketAmount(newTotalTicketAmount, tripId);

                booking.setPassengers(billing.getTicketAmount());
                booking.setPrice(billing.getTotalPrice());

                dbh.insertBooking(booking);
                int systemId = dbh.getSystemIdFromSsn(ssn);
                dbh.insertPersonHasBooking(systemId, dbh.getMaxBookingId());
                myAlert.billingSent(dbh.getAddress(ssn));
                returnToSearchLocation(ae);

            } else {

            }

        }


    }

    private void setBilling() {

        billing.setLuggagePrice(Double.parseDouble(luggageField.getText()));
        billing.setTripPrice(trip.getTripPrice());
        billing.setAdultAmount(adultTicket.getValue());
        billing.setChildAmount(childTicket.getValue());
        billing.calcAndSetTotalPrice();

    }

    @FXML
    private void cancelBooking(ActionEvent ae) {
        SwitchScene sw = new SwitchScene();
        String userType = dbh.printUserType(local.getCurrentUsersUserName());

        if (userType.matches("Employee")) {
            sw.GoTo(ae, "Employee.fxml");
        }
        if (userType.matches("Customer")) {
            sw.GoTo(ae, "Customer.fxml");
        }
        if (userType.matches("Admin")) {
            sw.GoTo(ae, "Admin.fxml");
        }
    }

    @FXML
    private void help(ActionEvent ae) {
        myAlert.bookingConfirmHelp();
    }

}
