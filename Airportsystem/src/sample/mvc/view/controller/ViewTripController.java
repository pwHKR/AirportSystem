package sample.mvc.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import sample.mvc.model.DBHandler;
import sample.mvc.model.DataStorage;
import sample.mvc.model.LocalFileStorage;
import sample.mvc.model.SwitchScene;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Stefan on 2017-04-20.
 */
public class ViewTripController implements Initializable {

    private ObservableList<String> trips = FXCollections.observableArrayList();
    private ObservableList<String> choices = FXCollections.observableArrayList("Name", "Date", "Price");
    private SwitchScene switchScene = new SwitchScene();
    private DataStorage dbh = new DBHandler();
    private LocalFileStorage local = new LocalFileStorage();
    private String userType = dbh.printUserType(local.getCurrentUsersUserName());

    @FXML
    private ListView<String> listView;
    @FXML
    private Button returnButton;
    @FXML
    private ChoiceBox<String> choiceBox;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        choiceBox.setItems(choices);
        trips = dbh.getTrips();

        listView.setItems(trips);
    }

    @FXML
    private void goBack(ActionEvent ae) {
        System.out.println(userType);
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
}
