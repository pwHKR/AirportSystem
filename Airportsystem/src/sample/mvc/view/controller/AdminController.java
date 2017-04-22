package sample.mvc.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.mvc.model.SwitchScene;

/**
 * Created by woojen on 2017-04-18.
 */
public class AdminController {

    SwitchScene sw = new SwitchScene();

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


}
