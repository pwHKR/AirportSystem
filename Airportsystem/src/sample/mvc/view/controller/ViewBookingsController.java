package sample.mvc.view.controller;

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
public class ViewBookingsController implements Initializable {
    @FXML
    private Button returnButton;
    @FXML
    private Button editBookingButton;
    @FXML
    private Button helpButton;
    @FXML
    private TextArea detailsArea;
    @FXML
    private ListView<String> bookingsListView;

    DataStorage dbh = new DBHandler();
    LocalFileStorage local = new LocalFileStorage();
    private String userType = dbh.printUserType(local.getCurrentUsersUserName());


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void editBooking(ActionEvent ae) {
        String choice = bookingsListView.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    private void setHelpButton(ActionEvent ae) {
        MyAlert myAlert = new MyAlert();
        myAlert.viewBookingsHelp();
    }

    @FXML
    private void goBack(ActionEvent ae) {
        SwitchScene sw = new SwitchScene();

        sw.gotoCheckUserType(ae, "Admin.fxml", "Customer.fxml", "Employee.fxml");
    }
}
