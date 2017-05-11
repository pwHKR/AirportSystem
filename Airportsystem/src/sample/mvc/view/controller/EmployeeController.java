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

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Stefan on 2017-04-25.
 */
public class EmployeeController implements Initializable {

    private DataStorage dbh = new DBHandler();
    private SwitchScene sw = new SwitchScene();
    private Path path = Paths.get("logInLog.bin");

    @FXML
    private Button browseTripsButton;
    @FXML
    private Label onlineLabel;
    @FXML
    private Button helpButton;
    @FXML
    private Button changeStatusButton;

    @FXML
    private void logout(ActionEvent ae) {

        try {
            List<String> textLines = Files.readAllLines(path);
            String userName = textLines.get(0);

            dbh.setUserOffline(userName);
            sw.setUserLoggedOut(true); // User has voluntary logged out
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        sw.GoTo(ae, "Login.fxml");

    }

    @FXML
    private void addTrip(ActionEvent ae) {

        sw.GoTo(ae, "AddFlight.fxml");

    }

    @FXML
    private void browseTrips(ActionEvent ae) {
        sw.GoTo(ae, "ViewTrip.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DataStorage dbhandler = new DBHandler();
        onlineLabel.setText("Online: " + String.valueOf(dbhandler.getUsersOnlineCount()));
    }

    @FXML
    private void helpFunction(ActionEvent ae) {
        MyAlert alert = new MyAlert();
        alert.helpChangeFlightStatus();
    }

    @FXML
    private void changeFlightStatus(ActionEvent ae) {
        sw.GoTo(ae, "ChangeFlightStatus.fxml");

    }
}
