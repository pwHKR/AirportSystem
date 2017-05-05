package sample.mvc.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.mvc.model.*;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    private Path path = Paths.get("logInLog.bin");


    @FXML
    private Button recoverPasswordButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        userName.setText(local.getCurrentUsersUserName());
        password.setText(local.getCurrentUsersPassword());
    }

    @FXML
    private void help() {
        myAlert.helpLogIn();
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
            myAlert.emptyUserName();
        }

        if (UserName.isEmpty() == false) {

            if (sentPassword.equals(Password) == false) {

                myAlert.loginFail();
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
