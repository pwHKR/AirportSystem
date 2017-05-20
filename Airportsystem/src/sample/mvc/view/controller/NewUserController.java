package sample.mvc.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import sample.mvc.model.Customer;
import sample.mvc.model.Employee;
import sample.mvc.model.User;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by woojen on 2017-04-14.
 */
public class NewUserController extends NewPersonController implements Initializable {

    private String typeOfUser = dbh.printUserType(local.getCurrentUsersUserName());
    private boolean isUserOnline = dbh.isUserOnline(local.getCurrentUsersUserName());
    private String stringPassword;
    private String stringUserName;
    private String stringEmail;


    @FXML
    private TextField password;
    @FXML
    private TextField userName;
    @FXML
    private TextField email;


    @FXML
    private void addUser(ActionEvent ae) {

        stringFirstName = firstName.getText();
        stringLastName = lastName.getText();
        stringSSN = ssn.getText();
        stringPassword = password.getText();
        stringUserName = userName.getText();
        stringEmail = email.getText();
        stringAddress = adress.getText();
        stringCountry = country.getValue();

        IsMale = !female.isSelected();

        if (email.getText().contains("@")) {

            if (!userName.getText().isEmpty()) {

                if (!password.getText().isEmpty()) {

                    //Checks if it's an employee making the account or if it's not online
                    if (!isUserOnline) {
                        addPerson(ae);
                        if (readyToInsertUser) {
                            User customer = new Customer(stringFirstName,
                                    stringLastName, IsMale, stringCountry, stringSSN, stringAddress, stringEmail, stringUserName, stringPassword);
                            dbh.insertOnlyUser(customer, "Customer");
                            sw.goToUnLogged(ae, "Login.fxml");
                        }
                    }

                    //Checks if it's an admin making the account
                    else if (typeOfUser.matches("Admin") && isUserOnline) {
                        addPerson(ae);
                        if (readyToInsertUser && typeOfUser.matches("Admin")) {
                            User employee = new Employee(stringFirstName,
                                    stringLastName, IsMale, stringCountry, stringSSN, stringAddress, stringEmail, stringUserName, stringPassword);
                            dbh.insertOnlyUser(employee, "Employee");
                            sw.GoTo(ae, "Admin.fxml");
                        }
                    }

                } else {
                    myAlert.passwordErr();
                }
            } else {
                myAlert.userNameErr();
            }

        } else {
            myAlert.eMailNameErr();
        }

    }

    @FXML
    private void returnToLoginScreen(ActionEvent ae) {
        if (typeOfUser.matches("Admin")) {
            sw.GoTo(ae, "Admin.fxml");
        } else {
            sw.goToUnLogged(ae, "Login.fxml");
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ini();
    }
}