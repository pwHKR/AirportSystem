package sample.mvc.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.mvc.model.DBHandler;
import sample.mvc.model.DataStorage;
import sample.mvc.model.LocalFileStorage;
import sample.mvc.model.SwitchScene;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Tobias Åkesson on 2017-04-29.
 */
public class PasswordRecoveryController implements Initializable {

    @FXML
    private Button recoverButton;
    @FXML
    private Button backButton;
    @FXML
    private Button saveButton;
    @FXML
    private TextField idText;

    private DataStorage dbhandler = new DBHandler();
    private SwitchScene sw = new SwitchScene();
    private LocalFileStorage localFileStorage = new LocalFileStorage();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void passwordRecovery(ActionEvent ae) {
        if (idText.getLength() > 0) {
            int id = Integer.parseInt(idText.getText());
            dbhandler.searchForUser(id);
        }

    }

    @FXML
    private void goBack(ActionEvent ae) {
        sw.goToUnLogged(ae, "Login.fxml");
    }

    @FXML
    private void save(ActionEvent ae) {

    }
}
