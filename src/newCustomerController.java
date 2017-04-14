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
    private void createCustomerAccount(ActionEvent ae) {

        String FirstName;
        String LastName;
        String SSN;
        String Password;
        String UserName;
        String Email;
        String Adress;
        String Country;
        boolean IsMale;

        FirstName = firstName.getText();
        LastName = lastName.getText();
        SSN = ssn.getText();
        Password = password.getText();
        UserName = userName.getText();
        Email = email.getText();
        Adress = adress.getText();
        Country = country.getText();

        if (female.isSelected()) {

            IsMale = false;
        } else {
            IsMale = true;
        }


        Customer customer = new Customer(FirstName, LastName, IsMale, Country, SSN, Adress, Email, UserName, Password);


        DBHandler dbh = new DBHandler();

        dbh.insertCustomer(customer);


    }


}