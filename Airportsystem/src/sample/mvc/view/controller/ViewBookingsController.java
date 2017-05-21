package sample.mvc.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import sample.mvc.model.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Tobias Åkesson on 2017-05-09.
 */
public class ViewBookingsController extends ControllerModelObject implements Initializable {
    @FXML
    private Button returnButton;
    @FXML
    private Button editBookingButton;
    @FXML
    private Button helpButton;
    @FXML
    private TextArea detailArea;
    @FXML
    private ListView<String> idBookingList;

    private ObservableList<String> bookingIds = FXCollections.observableArrayList();


    private String userType = dbh.printUserType(local.getCurrentUsersUserName());


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        bookingIds = dbh.getUserBookings(local.getCurrentUsersUserName());


        //  .replaceAll("[^0-9.]", "");
        idBookingList.setItems(bookingIds);
    }

    @FXML
    private void editBooking(ActionEvent ae) {
        if (idBookingList.getSelectionModel().getSelectedItem() != null) {
            String choiceInput = idBookingList.getSelectionModel().getSelectedItems().toString().replaceAll(("\\D+"), "");

            Booking booking = dbh.getBookingObject(Integer.parseInt(choiceInput));

            local.saveBookingObjectToFile(booking);

            sw.GoTo(ae, "EditBooking.fxml");
        } else {
            myAlert.noBookingSelectedError();
        }
    }

    @FXML
    private void setHelpButton(ActionEvent ae) {
        myAlert.viewBookingsHelp();
    }

    @FXML
    private void goBack(ActionEvent ae) {
        if (userType.matches("Admin")) {
            sw.GoTo(ae, "Admin.fxml");
        }
        if (userType.matches("Customer")) {
            sw.GoTo(ae, "Customer.fxml");
        }
        if (userType.matches("Employee")) {
            sw.GoTo(ae, "Employee.fxml");
        }
    }

    @FXML
    private void getInfo() {

        String choiceInput = idBookingList.getSelectionModel().getSelectedItems().toString().replaceAll(("\\D+"), "");
        java.lang.System.out.println(choiceInput);


        //String choice = idBookingList.getSelectionModel().getSelectedItem().toString();
        if (idBookingList.getSelectionModel().getSelectedItem() != null) {
            String info = dbh.getBookingInfo(Integer.parseInt(choiceInput));
            detailArea.setText(info);
        }
    }
}
