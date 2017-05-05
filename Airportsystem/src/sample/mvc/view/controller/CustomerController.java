package sample.mvc.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import sample.mvc.model.DBHandler;
import sample.mvc.model.DataStorage;
import sample.mvc.model.SwitchScene;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    private boolean isOnline;

    SwitchScene sw = new SwitchScene();
    private Path path = Paths.get("logInLog.bin");
    private DataStorage dbh = new DBHandler();
    @FXML
    Label nameLabel;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        try {
            List<String> textLines = Files.readAllLines(path);
            String userName = textLines.get(0);

            nameLabel.setText(userName);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    @FXML
    private void testMethod(ActionEvent ae) {

        sw.GoTo(ae, "test.fxml");

    }

    @FXML
    private void goToBilling(ActionEvent ae) {

        sw.GoTo(ae, "Billing.fxml");
    }

}
