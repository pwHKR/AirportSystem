package sample.mvc.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.mvc.model.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Tobias Åkesson on 2017-05-11.
 */
public class flightStatusController implements Initializable {
    @FXML
    private Button returnButton;
    @FXML
    private Button changeStatusButton;
    @FXML
    private Button helpButton;
    @FXML
    private ListView<Integer> flightsView;
    @FXML
    private TextArea flightInformationArea;
    @FXML
    private TextField statusField;

    DataStorage dbh = new DBHandler();
    LocalFileStorage local = new LocalFileStorage();
    private String userType = dbh.printUserType(local.getCurrentUsersUserName());
    private ObservableList<Integer> flightIds = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        flightIds = dbh.getAllFlightIds();
        flightsView.setItems(flightIds);
    }

    @FXML
    private void goBack(ActionEvent actionEvent) {
        SwitchScene sw = new SwitchScene();

        if (userType.matches("Admin")) {
            sw.GoTo(actionEvent, "Admin.fxml");
        }
        if (userType.matches("Customer")) {
            sw.GoTo(actionEvent, "Customer.fxml");
        }
        if (userType.matches("Employee")) {
            sw.GoTo(actionEvent, "Employee.fxml");
        }
    }

    @FXML
    private void setHelpButton(ActionEvent ae) {
        MyAlert myAlert = new MyAlert();
        myAlert.helpChangeFlightStatus();
    }
}
