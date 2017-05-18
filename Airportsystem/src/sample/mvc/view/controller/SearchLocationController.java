package sample.mvc.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import sample.mvc.model.*;

import java.lang.System;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Stefan on 2017-04-20.
 */
public class SearchLocationController implements Initializable {


    private ObservableList<String> trips = FXCollections.observableArrayList();
    private ObservableList<String> choices = FXCollections.observableArrayList("", "Name", "Price Descending", "Price Ascending");

    private SwitchScene switchScene = new SwitchScene();
    private DataStorage dbh = new DBHandler();

    private MyAlert myAlert = new MyAlert();

    private LocalFileStorage local = new LocalFileStorage();
    private String userType = dbh.printUserType(local.getCurrentUsersUserName());

    private Trip trip;

    @FXML
    private ListView<String> listView;
    @FXML
    private Button returnButton;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TextField textField;
    @FXML
    private TextArea textArea;
    private int removeIndexNum;
    private boolean isSearch = false;

    @FXML
    private void filterSearch() {

        isSearch = true;


        trips = dbh.getFilteredResults(textField.getText(), comboBox.getValue());

        //removeIndexNum = Integer.parseInt(trips.get(0));

        //System.out.println(removeIndexNum);

        //trips.remove(0, 1);

        listView.setItems(trips);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBox.setItems(choices);
        comboBox.setValue(choices.get(0));
        trips = dbh.getTripList();
        System.out.println(userType);
        removeIndexNum = Integer.parseInt(trips.get(0));

        System.out.println(removeIndexNum);

        trips.remove(0, 1);




        listView.setItems(trips);


    }

    @FXML
    private void goBack(ActionEvent ae) {

        if (userType.matches("Admin")) {
            switchScene.GoTo(ae, "Admin.fxml");
        }
        if (userType.matches("Customer")) {
            switchScene.GoTo(ae, "Customer.fxml");
        }
        if (userType.matches("Employee")) {
            switchScene.GoTo(ae, "Employee.fxml");
        }
    }


    @FXML
    private void helpFunction(ActionEvent ae) {
        MyAlert myAlert = new MyAlert();
        myAlert.searchHelp();
    }

    @FXML
    private void checkSelected(MouseEvent me) {


        int choice;
        int tripId;
        String stringChoice = "";


        //String choice = listView.getSelectionModel().getSelectedItems().toString()
        //  .replaceAll("[^0-9.]", "");

        if (!isSearch) {
            choice = listView.getSelectionModel().getSelectedIndex() + removeIndexNum;
        System.out.println(choice);

            stringChoice = String.valueOf(choice);
        }

        if (isSearch) {
            stringChoice = listView.getSelectionModel().getSelectedItems().toString().replaceAll(("\\D+"), "");
        }



        trip = dbh.getTripObject(stringChoice);

        tripId = dbh.getTripId(String.valueOf(trip.getFlightID()));

        Location location = dbh.getFromLocationObject(tripId);


        textArea.setText("Date: " + trip.getDate() + "\nBilling: " + String.valueOf(trip.getTripPrice()) + "SEK\n" +
                "Tickets left: " + String.valueOf(trip.getTicketAmount()) + "\n\n\tFrom: \n"
                + "Airport: " + location.getAirport() + "\nCity: " + location.getCity() +
                "\nCountry: " + location.getCountry());


    }

    @FXML
    private void newBooking(ActionEvent ae) {

        int tripId;

        if (listView.getSelectionModel().getSelectedItem() != null) {

            int choice = listView.getSelectionModel().getSelectedIndex() + removeIndexNum;
            System.out.println(choice);

            String stringChoice = String.valueOf(choice);

            trip = dbh.getTripObject(stringChoice);

            if (trip.getTicketAmount() > 1) {

                tripId = dbh.getTripId(String.valueOf(trip.getFlightID()));

                Booking booking = new Booking(tripId);

                System.out.println(userType);
                local.saveBookingIdToFile(booking);
                if (userType.matches("Employee") || userType.matches("Admin")) {
                    switchScene.GoTo(ae, "NewBookingForPerson.fxml");
                }
                if (userType.matches("Customer")) {
                    switchScene.GoTo(ae, "NewBooking.fxml");
                }

            } else {
                myAlert.soldOutMsg();
            }
        } else {
            myAlert.noLocationSelectedError();
        }

    }
}
