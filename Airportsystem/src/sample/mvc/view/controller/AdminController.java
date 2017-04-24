package sample.mvc.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import sample.mvc.model.DBHandler;
import sample.mvc.model.DataStorage;
import sample.mvc.model.SwitchScene;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by woojen on 2017-04-18.
 */
public class AdminController implements Initializable {

    SwitchScene sw = new SwitchScene();

    @FXML
    Label online;  ///label för antalet online användare

    @FXML
    private void logout(ActionEvent ae) {

        sw.GoTo(ae, "Login.fxml");

    }


    @FXML
    private void addTrip(ActionEvent ae) {

        sw.GoTo(ae, "AddTrip.fxml");
    }

    @FXML
    private void newEmployee(ActionEvent ae) {


        NewUserController.setCustomer(false);


        sw.GoTo(ae, "NewUser.fxml");


    }

    @FXML
    private void viewWorkers(ActionEvent ae) {

        sw.GoTo(ae, "ViewWorkers.fxml");


    }

    @FXML
    private void addDestination(ActionEvent ae) {
        sw.GoTo(ae, "AddLocation.fxml");

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DataStorage dbhandler = new DBHandler();

        online.setText(String.valueOf(dbhandler.getUsersOnline()));
    }
}
