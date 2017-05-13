package sample.mvc.view.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Stefan on 2017-05-13.
 */
public class EditBookingController implements Initializable {

    @FXML
    private TextArea totalPriceArea;
    @FXML
    private TextArea bookingInfoArea;
    @FXML
    private TextField luggageField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
