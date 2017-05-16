package sample.mvc.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.mvc.model.DBHandler;
import sample.mvc.model.DataStorage;
import sample.mvc.model.MyAlert;
import sample.mvc.model.SwitchScene;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by woojen on 2017-04-18.
 */
public class AdminController implements Initializable {

    private MyAlert myAlert = new MyAlert();
    private SwitchScene sw = new SwitchScene();

    @FXML
    private Label online;  ///label för antalet online användare
    @FXML
    private Button helpButton;
    @FXML
    private Button addTripButton;
    @FXML
    private Button addFlightButton;
    @FXML
    private Button browseTripsButton;
    @FXML
    private Button changeFlightStatusButton;

    @FXML
    private void logout(ActionEvent ae) {

        sw.GoTo(ae, "Login.fxml");

    }


    @FXML
    private void viewServer(ActionEvent ae) {

        sw.GoTo(ae, "Server.fxml");

    }

    @FXML
    private void newEmployee(ActionEvent ae) {

        sw.GoTo(ae, "NewUser.fxml");


    }

    @FXML
    private void viewWorkers(ActionEvent ae) {

        sw.GoTo(ae, "ViewWorkers.fxml");


    }

    @FXML
    private void viewPSTR(ActionEvent ae) {
        sw.GoTo(ae, "ViewPSTR.fxml");
    }


    @FXML
    private void addPSTR(ActionEvent ae) {
        sw.GoTo(ae, "NewPSTR.fxml");

    }

    @FXML
    private void addAirplane(ActionEvent ae) {
        sw.GoTo(ae, "AddAirplane.fxml");

    }


    @FXML
    private void helpFunction(ActionEvent ae) {
        myAlert.helpAdmin();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DataStorage dbhandler = new DBHandler();

        online.setText("Online: " + String.valueOf(dbhandler.getUsersOnlineCount()));
    }

    @FXML
    private void addTrip(ActionEvent ae) {
        sw.GoTo(ae, "AddTrip.fxml");
    }

    @FXML
    private void addFlight(ActionEvent ae) {
        sw.GoTo(ae, "AddFlight.fxml");
    }

    @FXML
    private void browseTrips(ActionEvent ae) {
        sw.GoTo(ae, "SearchLocation.fxml");
    }

    @FXML
    private void changeFlightStatus(ActionEvent ae) {
        sw.GoTo(ae, "ChangeFlightStatus.fxml");
    }
}
