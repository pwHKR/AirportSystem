package sample.mvc.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import sample.mvc.model.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by woojen on 2017-04-14.
 */
public class NewUserController implements Initializable {

    private MyAlert myAlert = new MyAlert();
    private SwitchScene sw = new SwitchScene();

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField ssn;
    @FXML
    private TextField password;
    @FXML
    private TextField userName;
    @FXML
    private TextField email;
    @FXML
    private TextField adress;
    @FXML
    private ChoiceBox<String> country = new ChoiceBox<>();
    @FXML
    private CheckBox female;
    @FXML
    private CheckBox male;

    public static boolean isCustomer;

    public static void setCustomer(boolean customer) {
        isCustomer = customer;
    }


    private String typeOfUser;


    @FXML
    private void addUser(ActionEvent ae) {
        String stringFirstName;
        String stringLastName;
        String stringSSN;
        String stringPassword;
        String stringUserName;
        String stringEmail;
        String stringAddress;
        String stringCountry;
        boolean IsMale;


        stringFirstName = firstName.getText();
        stringLastName = lastName.getText();
        stringSSN = ssn.getText();
        stringPassword = password.getText();
        stringUserName = userName.getText();
        stringEmail = email.getText();
        stringAddress = adress.getText();
        stringCountry = country.getValue();

        if (female.isSelected()) {
            IsMale = false;
        } else {
            IsMale = true;
        }

        if (firstName.getText().matches("^[a-öA-Ö]+$")) {

            if (lastName.getText().matches("^[a-öA-Ö]+$")) {

                if (ssn.getText().matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")) {

                    if (email.getText().contains("@")) {

                        if (adress.getText().matches("^[a-öA-Ö]+ \\d+$")) {

                            //if (country.getValue().matches("^[a-öA-Ö]+$")) {

                                if (!userName.getText().isEmpty()) {

                                    if (!password.getText().isEmpty()) {

                                        DataStorage dbh = new DBHandler();

                                        if (isCustomer == true) {
                                            User customer = new Customer(stringFirstName,
                                                    stringLastName, IsMale, stringCountry, stringSSN, stringAddress, stringEmail, stringUserName, stringPassword);
                                            dbh.insertUser(customer, typeOfUser);
                                        } else if (isCustomer == false) {

                                            User customer = new Employee(stringFirstName,
                                                    stringLastName, IsMale, stringCountry, stringSSN, stringAddress, stringEmail, stringUserName, stringPassword);

                                            dbh.insertUser(customer, typeOfUser);
                                        }


                                        if (isCustomer == true) {

                                            sw.goToUnLogged(ae, "Login.fxml");
                                        }

                                        if (isCustomer == false) {
                                            sw.GoTo(ae, "Admin.fxml");
                                        }
                                    } else {
                                        myAlert.passwordErr();
                                    }
                                } else {
                                    myAlert.userNameErr();
                                }
                            /*} else {
                                myAlert.countryNameErr();
                            }*/
                        } else {
                            myAlert.addressNameErr();
                        }
                    } else {
                        myAlert.eMailNameErr();
                    }
                } else {
                    myAlert.ssnInputErr();
                }
            } else {
                myAlert.lastNameErr();
            }
        } else {
            myAlert.firstNameErr();
        }
    }

    @FXML
    private void help() {

        myAlert.helpNewCustomer();
    }

    @FXML
    private void maleToFemale() {  ///for the male and female checkboxes, two boxes can't be checked at the same time
        if (female.isSelected()) {
            male.setSelected(false);
        }
    }

    @FXML
    private void femaleToMale() {
        if (male.isSelected())
            female.setSelected(false);
    }

    @FXML
    private void returnToLoginScreen(ActionEvent ae) {
        sw.goToUnLogged(ae, "Login.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String SelectedCountry = country.getValue();


        DataStorage dbh = new DBHandler();


        ObservableList<String> countryList = dbh.getCountries();

        country.setItems(countryList);


        if (isCustomer == true) {
            typeOfUser = "Customer";
        }

        if (isCustomer == false) {
            typeOfUser = "Employee";
        }
    }
}