package sample.mvc.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import sample.mvc.model.SwitchScene;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    SwitchScene sw = new SwitchScene();

    @FXML
    private void logout(ActionEvent ae) {

        sw.GoTo(ae, "Login.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
