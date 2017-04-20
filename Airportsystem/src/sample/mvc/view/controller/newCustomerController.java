package sample.mvc.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.mvc.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by woojen on 2017-04-14.
 */
public class newCustomerController implements Initializable {


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
    TextField country;
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
        Country = country.getText();

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

                            if (country.getText().matches("^[a-öA-Ö]+$")) {

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

                                    Node node = (Node) ae.getSource();
                                    Stage stage = (Stage) node.getScene().getWindow();
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../login.fxml"));
                                    Parent root = null;
                                    try {
                                        root = loader.load();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    Scene scene = new Scene(root);
                                    stage.setScene(scene);
                                }

                                if (isCustomer == false) {


                                    Node node = (Node) ae.getSource();
                                    Stage stage = (Stage) node.getScene().getWindow();
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../adminLoggedIn.fxml"));
                                    Parent root = null;
                                    try {
                                        root = loader.load();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    Scene scene = new Scene(root);
                                    stage.setScene(scene);
                                }


                            } else {
                                Alert dialog = new Alert(Alert.AlertType.INFORMATION);
                                dialog.setTitle("Error");
                                dialog.setHeaderText("Invalid info");
                                dialog.setContentText("Your country name can only contain letters.");
                                dialog.showAndWait();

                            }
                        } else {
                            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
                            dialog.setTitle("Error");
                            dialog.setHeaderText("Invalid info");
                            dialog.setContentText("Your address must contain street name and street number.");
                            dialog.showAndWait();

                        }
                    } else {
                        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
                        dialog.setTitle("Error");
                        dialog.setHeaderText("Invalid info");
                        dialog.setContentText("Email adress must contain ''@'' ");
                        dialog.showAndWait();

                }
            } else {
                Alert dialog = new Alert(Alert.AlertType.INFORMATION);
                dialog.setTitle("Error");
                dialog.setHeaderText("Invalid info");
                dialog.setContentText("Your ssn must be 10 numbers");
                dialog.showAndWait();

            }
        } else {
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setTitle("Error");
            dialog.setHeaderText("Invalid info");
            dialog.setContentText("Your last name can only contain letters.");
            dialog.showAndWait();

        }


        /*Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle("Error");
        dialog.setHeaderText("Invalid info");
        dialog.setContentText("Your first name can only contain letters.");
        dialog.showAndWait();*/

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
        Node node = (Node) ae.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../login.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        if (isCustomer == true) {
            typeOfUser = "Customer";
        }

        if (isCustomer == false) {
            typeOfUser = "Employee";
        }
    }
}