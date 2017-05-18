package sample.mvc.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by woojen on 2017-05-11.
 */
public class NewBookingForPersonController extends NewBookingController implements Initializable {


    @FXML
    private TextField ssnField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ini();
        isPerson = true;
    }


    @FXML
    protected void bookingConfirmed(ActionEvent ae) {
        confirmBooking(ae);

        String ssn = ssnField.getText();
        int systemId = dbh.getSystemIdFromSsn(ssn);
        dbh.insertPersonHasBooking(systemId, dbh.getMaxBookingId());
        myAlert.billingSent(dbh.getAddress(ssn));

        returnToSearchLocation(ae);


    }
}
