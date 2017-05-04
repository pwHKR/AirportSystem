package sample.mvc.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.mvc.model.*;

import java.lang.System;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLNonTransientConnectionException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    SwitchScene sw = new SwitchScene();

    @FXML
    private PasswordField password;

    @FXML
    private TextField userName;

    private Path path = Paths.get("logInLog.bin");

    DataStorage dbh = new DBHandler();
    LocalFileStorage local = new LocalFileStorage();

    @FXML
    private Button recoverPasswordButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        DataStorage dbhandler = new DBHandler();

        System.out.println(dbhandler.getUsersOnlineCount());
        userName.setText(local.getCurrentUsersUserName());
        password.setText(local.getCurrentUsersPassword());
    }

    @FXML
    private void help() {
        MyAlert.helpLogIn();
    }

    @FXML
    private void login(ActionEvent ae) throws SQLNonTransientConnectionException {

        String UserName = userName.getText();
        String Password = password.getText();
        local.saveUser(UserName, Password); //saves username and password to localfile

        String sentPassword = dbh.matchPassword(UserName);

        String typeOfUser; // Variable for holding typeOfUser information from resultset(DB)


        boolean isAdmin = false;
        boolean isEmployee = false;

        typeOfUser = dbh.printUserType(UserName);


        if (typeOfUser.equals("Employee")) {
            isEmployee = true;
        }

        if (UserName.equals("admin")) {
            isAdmin = true;
        }
        if (UserName.isEmpty()) {
            MyAlert.emptyUserName();
        }

        if (UserName.isEmpty() == false) {

            if (sentPassword.equals(Password) == false) {

                MyAlert.loginFail();
            }

            if (sentPassword.equals(Password) && isAdmin == true) {

                sw.GoTo(ae, "Admin.fxml");
            }
        }


        if (sentPassword.equals(Password) && isAdmin == false && isEmployee == false) {

            dbh.setUserOnline(UserName);
            sw.GoTo(ae, "Customer.fxml");

        }


        if (sentPassword.equals(Password) && isAdmin == false && isEmployee == true) {

            dbh.setUserOnline(UserName);
            sw.GoTo(ae, "Employee.fxml");

        }
    }


    @FXML
    private void newUser(ActionEvent ae) {
        NewUserController.setCustomer(true);

        sw.goToUnLogged(ae, "NewUser.fxml");
    }

    @FXML
    private void recoverPassword(ActionEvent event) {
        sw.goToUnLogged(event, "passwordRecovery.fxml");
    }
}
