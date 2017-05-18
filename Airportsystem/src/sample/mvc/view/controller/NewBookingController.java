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
    protected DataStorage dbh = new DBHandler();
    private Billing billing = new Billing();
    private SwitchScene sw = new SwitchScene();
    protected MyAlert myAlert = new MyAlert();


    private ObservableList<String> tripInfoList = FXCollections.observableArrayList();

    private ObservableList<Integer> zeroToTenList = FXCollections.observableArrayList();
    @FXML
    private CheckBox foodCheckBox;

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
    private Booking booking;
    private Trip trip;
    private Flight flight;
    private Airplane airplane;
    private int tripId;
    private int systemId;
    private int flightId;
    protected boolean isPerson; // Always false in superclass (this)


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ini();
        isPerson = false;


    }


    protected int getChildTicket() {
        int ticketAmount;

        ticketAmount = childTicket.getValue();

        return ticketAmount;
    }

    protected int getAdultTicket() {

        int ticketAmount;

        ticketAmount = adultTicket.getValue();

        return ticketAmount;
    }

    @FXML
    protected void showBookingInfo() {
        setBilling();
        totalPriceArea.setText("Adult tickets: " + getAdultTicket() + "\nPrice: " + getAdultTicket() + " X " +
                String.valueOf(trip.getTripPrice()) + "= " +
                billing.getAdultTotalPrice(trip.getTripPrice(), getAdultTicket()) +
                " SEK\nChild tickets: " + getChildTicket() + "\nPrice: " + getChildTicket() + " X " + String.valueOf(trip.getTripPrice()) + "= "
                + billing.getChildTotalPrice(trip.getTripPrice(), getChildTicket()) + " SEK\nLuggage price: " + String.valueOf(billing.calcLuggagePrice(Double.parseDouble(luggageField.getText()))) + " SEK");
    }


    @FXML
    protected void returnToSearchLocation(ActionEvent ae) {

        sw.GoTo(ae, "SearchLocation.fxml");

    }

    @FXML
    protected void confirmBooking(ActionEvent ae) {


        boolean luggageMax = true;
        boolean choice; //boolean from confirm dialog
        boolean isBalance;
        int newTotalTicketAmount; // The remaining ticket amount after the booking
        int luggageMaxTot;

        setBilling();

        choice = myAlert.confirmBooking(billing.getTotalPrice());

        luggageMaxTot = Integer.parseInt(luggageField.getText()) * billing.getTicketAmount();

        if (choice) {

            isBalance = checkBalance();

            if (luggageMaxTot > airplane.getMaxLuggageWeight()) {
                luggageMax = false;
                myAlert.luggageMaxMsg(String.valueOf(airplane.getMaxLuggageWeight() * billing.getTicketAmount()));

            }


            if (isBalance && luggageMax || isPerson && luggageMax) {


                newTotalTicketAmount = dbh.getTicketAmount(tripId) - billing.getTicketAmount();

                dbh.setTicketAmount(newTotalTicketAmount, tripId);

                booking.setPassengers(billing.getTicketAmount());
                booking.setPrice(billing.getTotalPrice());

                if (foodCheckBox.isSelected() == true) {
                    booking.setHasFood(true);
                } else {
                    booking.setHasFood(false);
                }

                dbh.insertBooking(booking);


            } else {


            }

            // pay booking
        } else {

            //return to booking screen

        }


    }

    protected void setBilling() {

        billing.setLuggagePrice(Double.parseDouble(luggageField.getText()));
        billing.setTripPrice(trip.getTripPrice());
        billing.setAdultAmount(adultTicket.getValue());
        billing.setChildAmount(childTicket.getValue());
        billing.calcAndSetTotalPrice();

    }

    protected boolean checkBalance() {

        boolean isBalance = false;

        setBilling();

        double balance = Double.parseDouble(dbh.getBalanceFromId
                (dbh.getIdFromUserName(local.getCurrentUsersUserName())));


        if (balance >= billing.getTotalPrice()) {
            double newBalance = balance - billing.getTotalPrice();
            dbh.setBalance(newBalance, dbh.getIdFromUserName(local.getCurrentUsersUserName()));

            isBalance = true;
        }

        return isBalance;

    }

    @FXML
    protected void cancelBooking(ActionEvent ae) {
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
    protected void help(ActionEvent ae) {
        myAlert.bookingConfirmHelp();
    }


    protected void ini() {
        for (int i = 1; i < 10; i++) {

            zeroToTenList.add(i);
        }

        luggageField.setText("1");
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

    // Method call for the super class (this class) when confirming a booking

    // Confirm booking insert statement to liking table
    // Also contains "SwitchScene" with return fxml

    @FXML
    private void confirmBookingSuper(ActionEvent ae) {

        confirmBooking(ae);


        insertLinkTblSuper();
        returnToSearchLocation(ae);


    }

    private void insertLinkTblSuper() {

        dbh.insertPersonHasBooking(dbh.getIdFromUserName(local.getCurrentUsersUserName()), dbh.getMaxBookingId());
        myAlert.bookingConfirmed();

    }


}