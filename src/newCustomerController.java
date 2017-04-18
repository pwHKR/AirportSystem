import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by woojen on 2017-04-14.
 */
public class newCustomerController {


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


    @FXML
    private void createCustomerAccount(ActionEvent ae) {

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


        Customer customer = new Customer(FirstName, LastName, IsMale, Country, SSN, Address, Email, UserName, Password);


        DataStorage dbh = new DBHandler();


        dbh.insertCustomer(customer);

        Node node = (Node) ae.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

    @FXML
    private void help() {

        MyAlert.helpNewCustomer();
    }

    @FXML
    private void checkForDuplicate() {  ///for the male and female checkboxes, two boxes can't be checked at the same time
        if (female.isSelected()) {
            male.setSelected(false);
        }
        if (male.isSelected())
            female.setSelected(false);
    }

    @FXML
    private void returnToLoginScreen(ActionEvent ae) {
        Node node = (Node) ae.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }


}