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

    protected MyAlert myAlert = new MyAlert();
    protected SwitchScene sw = new SwitchScene();
    protected DataStorage dbh = new DBHandler();
    protected String stringFirstName;
    protected String stringLastName;
    protected String stringSSN;
    protected String stringAddress;
    protected String stringCountry;
    protected boolean IsMale;
    private boolean passed = false;
    protected boolean readyToInsertUser = false;

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
    protected void addPerson(ActionEvent ae) {
        stringFirstName = firstName.getText();
        stringLastName = lastName.getText();
        stringSSN = ssn.getText();
        stringAddress = adress.getText();
        stringCountry = country.getValue();

        IsMale = !female.isSelected();
        if (female.isSelected() || male.isSelected()) {

            if (country.getSelectionModel().getSelectedItem() != null) {

                if (firstName.getText().matches("^[a-öA-Ö]+$")) {

                    if (lastName.getText().matches("^[a-öA-Ö]+$")) {

                        if (ssn.getText().matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")) {

                            if (adress.getText().matches("^[a-öA-Ö]+ \\d+$")) {
                                Person person = new Person(stringFirstName, stringLastName, IsMale, stringCountry, stringSSN, stringAddress);
                                dbh.insertPerson(person);
                                passed = true;
                                readyToInsertUser = true;
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
            } else {
                myAlert.noCountrySelected();
            }
        } else {
            myAlert.genderIsNotSelected();
        }
    }

    @FXML
    protected void help() {

        myAlert.helpNewCustomer();
    }

    @FXML
    protected void maleToFemale() {  ///for the male and female checkboxes, two boxes can't be checked at the same time
        if (female.isSelected()) {
            male.setSelected(false);
        }
    }

    @FXML
    protected void femaleToMale() {
        if (male.isSelected())
            female.setSelected(false);
    }

    @FXML
    private void returnToEmployeeScreen(ActionEvent ae) {

        sw.GoTo(ae, "Employee.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<String> countryList = dbh.getCountries();

        country.setItems(countryList);
    }

    @FXML
    public void superAddPerson(ActionEvent ae) {
        addPerson(ae);
        if (passed) {

            sw.GoTo(ae, "Employee.fxml");
        }
    }
}