package sample.mvc.view.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import sample.mvc.model.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by woojen on 2017-04-14.
 */
public class NewPersonController implements Initializable {

    private MyAlert myAlert = new MyAlert();
    private SwitchScene sw = new SwitchScene();
    DataStorage dbh = new DBHandler();

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField ssn;
    @FXML
    private TextField adress;
    @FXML
    private ChoiceBox<String> country = new ChoiceBox<>();
    @FXML
    private CheckBox female;
    @FXML
    private CheckBox male;
    @FXML
    private Button addButton;

    @FXML
    private void addUser(ActionEvent ae) {
        String stringFirstName;
        String stringLastName;
        String stringSSN;
        String stringAddress;
        String stringCountry;
        boolean IsMale;


        stringFirstName = firstName.getText();
        stringLastName = lastName.getText();
        stringSSN = ssn.getText();
        stringAddress = adress.getText();
        stringCountry = country.getValue();

        IsMale = !female.isSelected();

        if (firstName.getText().matches("^[a-öA-Ö]+$")) {

            if (lastName.getText().matches("^[a-öA-Ö]+$")) {

                if (ssn.getText().matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")) {

                    if (adress.getText().matches("^[a-öA-Ö]+ \\d+$")) {
                        Person person = new Person(stringFirstName, stringLastName, IsMale, stringCountry, stringSSN, stringAddress);
                        dbh.insertPerson(person);
                        sw.GoTo(ae, "Employee.fxml");
                    } else {
                        myAlert.addressNameErr();
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

        ObservableList<String> countryList = dbh.getCountries();

        country.setItems(countryList);
    }
}