package sample.mvc.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.mvc.model.DBHandler;
import sample.mvc.model.DataStorage;
import sample.mvc.model.SwitchScene;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Stefan on 2017-04-25.
 */
public class EmployeeController {

    private DataStorage dbh = new DBHandler();
    private SwitchScene sw = new SwitchScene();
    private Path path = Paths.get("logInLog.bin");

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


}
