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
import javafx.scene.input.MouseEvent;
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
    private ObservableList<String> flightInformation = FXCollections.observableArrayList();
    private int choice;

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

    @FXML
    private void getFlightInfo(MouseEvent me) {
        int choice = flightsView.getSelectionModel().getSelectedItem();
        flightInformation = dbh.getFlightinformation(choice);
        flightInformationArea.setText(flightInformation.toString().replace("[", "").replace("]", ""));
    }

    @FXML
    private void changeStatus(ActionEvent ae) {
        MyAlert myAlert = new MyAlert();
        int id = flightsView.getSelectionModel().getSelectedItem();
        if (flightsView.getSelectionModel().getSelectedItem() == null || statusField.getText().isEmpty()) {
            myAlert.errorChangingFlightStatus();
        } else {
            dbh.changeFlightStatus(statusField.getText(), id);
            flightInformation = dbh.getFlightinformation(choice);
            flightInformationArea.setText(flightInformation.toString().replace("[", "").replace("]", ""));
            myAlert.flighStatusChanged();
        }
    }
}
