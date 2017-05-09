package sample.mvc.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.mvc.model.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Stefan on 2017-04-20.
 */
public class ViewTripController implements Initializable {

    private ObservableList<String> trips = FXCollections.observableArrayList();
    private ObservableList<String> choices = FXCollections.observableArrayList("Date", "Name", "Price Descending", "Price Ascending");

    private SwitchScene switchScene = new SwitchScene();
    private DataStorage dbh = new DBHandler();

    private LocalFileStorage local = new LocalFileStorage();
    private String userType = dbh.printUserType(local.getCurrentUsersUserName());

    @FXML
    private ListView<String> listView;
    @FXML
    private Button returnButton;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TextField textField;
    @FXML
    private Button helpButton;

    @FXML
    private void filterSearch() {
        trips = dbh.getFilteredResults(textField.getText(), comboBox.getValue());
        listView.setItems(trips);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBox.setItems(choices);
        trips = dbh.getTrips();

        listView.setItems(trips);
    }

    @FXML
    private void goBack(ActionEvent ae) {
        //switchScene.goToCheckUserType(ae, "Customer");

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
    private void checkSelected(MouseEvent ae) {
        //String listViewChoice = listView.getSelectionModel().getSelectedItem();

    }

    @FXML
    private void helpFunction(ActionEvent actionEvent) {
        MyAlert myAlert = new MyAlert();
        myAlert.searchHelp();
    }
}
