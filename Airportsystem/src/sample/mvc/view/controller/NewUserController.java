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

    SwitchScene sw = new SwitchScene();

    ObservableList<String> countryList = FXCollections.observableArrayList();

    @FXML
    TextField firstName;
    @FXML
    TextField lastName;
    @FXML
    TextField ssn;
    @FXML
    TextField password;
    @FXML
    TextField userName;
    @FXML
    TextField email;
    @FXML
    TextField adress;
    @FXML
    ChoiceBox<String> country = new ChoiceBox<>();
    @FXML
    CheckBox female;
    @FXML
    CheckBox male;

    public static boolean isCustomer;

    public static void setCustomer(boolean customer) {
        isCustomer = customer;
    }


    String typeOfUser;


    @FXML
    private void addUser(ActionEvent ae) {
        String FirstName;
        String LastName;
        String SSN;
        String Password;
        String UserName;
        String Email;
        String Address;
        String Country;
        boolean IsMale;


        FirstName = firstName.getText();
        LastName = lastName.getText();
        SSN = ssn.getText();
        Password = password.getText();
        UserName = userName.getText();
        Email = email.getText();
        Address = adress.getText();
        Country = country.getValue();

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
                                            User customer = new Customer(FirstName,
                                                    LastName, IsMale, Country, SSN, Address, Email, UserName, Password);
                                            dbh.insertUser(customer, typeOfUser);
                                        } else if (isCustomer == false) {

                                            User customer = new Employee(FirstName,
                                                    LastName, IsMale, Country, SSN, Address, Email, UserName, Password);

                                            dbh.insertUser(customer, typeOfUser);
                                        }


                                        if (isCustomer == true) {

                                            sw.goToUnLogged(ae, "Login.fxml");
                                        }

                                        if (isCustomer == false) {
                                            sw.GoTo(ae, "Admin.fxml");
                                        }
                                    } else {
                                        MyAlert.passwordErr();
                                    }
                                } else {
                                    MyAlert.userNameErr();
                                }
                            /*} else {
                                MyAlert.countryNameErr();
                            }*/
                        } else {
                            MyAlert.addressNameErr();
                        }
                    } else {
                        MyAlert.eMailNameErr();
                    }
                } else {
                    MyAlert.ssnInputErr();
                }
            } else {
                MyAlert.lastNameErr();
            }
        } else {
            MyAlert.firstNameErr();
        }
    }

    @FXML
    private void help() {

        MyAlert.helpNewCustomer();
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


        countryList = dbh.getCountries();

        country.setItems(countryList);


        if (isCustomer == true) {
            typeOfUser = "Customer";
        }

        if (isCustomer == false) {
            typeOfUser = "Employee";
        }
    }
}