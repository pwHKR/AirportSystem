package sample.mvc.view.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Tobias Åkesson on 2017-04-29.
 */
public class PasswordRecoveryController extends ControllerModelObject implements Initializable {

    @FXML
    private Button recoverButton;
    @FXML
    private Button backButton;
    @FXML
    private Button saveButton;
    @FXML
    private TextField idText;
    @FXML
    private TextArea informationArea;
    @FXML
    private Button helpButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void passwordRecovery(ActionEvent ae) {
        if (!idText.getText().isEmpty()) {
            ObservableList<String> userInformation = dbh.searchForPassword(idText.getText());
            if (userInformation.size() > 0)
                informationArea.setText(userInformation.get(0).toString());
            else {
                myAlert.noInformationInDatabase();
            }
        } else {
            myAlert.userNameErr();
        }

    }

    @FXML
    private void goBack(ActionEvent ae) {
        sw.goToUnLogged(ae, "Login.fxml");
    }

    @FXML
    private void save(ActionEvent ae) {
        if (!informationArea.getText().isEmpty()) {
            local.saveUser(idText.getText(), informationArea.getText());
            myAlert.informationSaved();
        }
    }

    @FXML
    private void helpFunction(ActionEvent ae) {
        myAlert.recoverPasswordHelp();
    }
}
