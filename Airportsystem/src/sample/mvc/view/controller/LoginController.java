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

    @FXML
    private Button exitButton;

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

        boolean isPassword = false;

        String stringUserName = userName.getText();
        String stringPassword = password.getText();
        local.saveUser(stringUserName, stringPassword); //saves username and password to localfile

        String sentPassword = dbh.matchPassword(stringUserName);

        String typeOfUser = ""; // Variable for holding typeOfUser information from resultset(DB)


        boolean isAdmin = false;
        boolean isEmployee = false;

        if (dbh.userNameExists(stringUserName)) {
            typeOfUser = dbh.printUserType(stringUserName);


            if (sentPassword != null) {

                if (sentPassword.matches(stringPassword)) {

                    if (!stringUserName.isEmpty()) {

                        if (typeOfUser.matches("Admin")) {
                            dbh.setUserOnline(stringUserName);
                            sw.GoTo(ae, "Admin.fxml");
                        }


                        //if (sentPassword != null) {
                        if (typeOfUser.matches("Customer")) {
                            dbh.setUserOnline(stringUserName);
                            sw.GoTo(ae, "Customer.fxml");
                        }
                    /*} else {
                        myAlert.userNameErr();
                    }*/

                        //if (sentPassword != null) {
                        if (typeOfUser.matches("Employee")) {
                            dbh.setUserOnline(stringUserName);
                            sw.GoTo(ae, "Employee.fxml");
                        }
                        //}
                    } else {
                        myAlert.loginFail();
                    }
                } else {
                    myAlert.invalidPasswordError();
                }
            } else {
                myAlert.userNameErr();
            }
        } else {
            myAlert.nameNotFound();

        }

    }


    @FXML
    private void newUser(ActionEvent ae) {

        local.saveUser("", "");
        sw.goToUnLogged(ae, "NewUser.fxml");
    }

    @FXML
    private void recoverPassword(ActionEvent event) {
        sw.goToUnLogged(event, "passwordRecovery.fxml");
    }

    @FXML
    private void exit(ActionEvent ae) {
        java.lang.System.exit(0);
    }
}
