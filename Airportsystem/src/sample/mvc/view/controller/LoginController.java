package sample.mvc.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.mvc.model.*;

import java.net.URL;
import java.sql.SQLNonTransientConnectionException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private MyAlert myAlert = new MyAlert();
    private SwitchScene sw = new SwitchScene();
    private DataStorage dbh = new DBHandler();
    private LocalFileStorage local = new LocalFileStorage();

    @FXML
    private PasswordField password;

    @FXML
    private TextField userName;

    @FXML
    private Button recoverPasswordButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        local.saveAirplaneToFile(dbh.getAirplaneObject("1254"));
        Airplane airplane = local.readAirplaneFromFile();


        userName.setText(local.getCurrentUsersUserName());
        password.setText(local.getCurrentUsersPassword());
    }

    @FXML
    private void help() {
        myAlert.helpLogIn();
    }

    @FXML
    private void login(ActionEvent ae) throws SQLNonTransientConnectionException {

        String stringUserName = userName.getText();
        String stringPassword = password.getText();
        local.saveUser(stringUserName, stringPassword); //saves username and password to localfile

        String sentPassword = dbh.matchPassword(stringUserName);

        String typeOfUser; // Variable for holding typeOfUser information from resultset(DB)


        boolean isAdmin = false;
        boolean isEmployee = false;

        if (!dbh.userNameExists(stringUserName)) {
            myAlert.nameNotFound();
        }

        typeOfUser = dbh.printUserType(stringUserName);

        if (typeOfUser.equals("Employee")) {
            isEmployee = true;
        }

        if (stringUserName.equals("admin")) {
            isAdmin = true;
        }
        if (stringUserName.isEmpty()) {
            myAlert.emptyUserName();
        }


        if (stringUserName.isEmpty() == false) {

            if (sentPassword.equals(stringPassword) == false) {

                myAlert.loginFail();
            }

            if (sentPassword.equals(stringPassword) && isAdmin == true) {
                dbh.setUserOnline(stringUserName);
                sw.GoTo(ae, "Admin.fxml");
            }
        }


        if (sentPassword.equals(stringPassword) && isAdmin == false && isEmployee == false) {

            dbh.setUserOnline(stringUserName);
            sw.GoTo(ae, "Customer.fxml");

        }


        if (sentPassword.equals(stringPassword) && isAdmin == false && isEmployee == true) {

            dbh.setUserOnline(stringUserName);
            sw.GoTo(ae, "Employee.fxml");

        }
    }


    @FXML
    private void newUser(ActionEvent ae) {


        sw.goToUnLogged(ae, "NewUser.fxml");
    }

    @FXML
    private void recoverPassword(ActionEvent event) {
        sw.goToUnLogged(event, "passwordRecovery.fxml");
    }
}
