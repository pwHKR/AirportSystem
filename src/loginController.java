import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.scene.Node;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class loginController implements Initializable {


    @FXML
    PasswordField password;
    @FXML
    TextField userName;

    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void help() {
        alert.setHeaderText("Login help");
        alert.setTitle("Help");
        alert.setContentText("If you do not already have an account, press Create Account \nYou can recover your password" +
                " if you have lost it by pressing the Recover Password button.");
        alert.show();
    }

    @FXML
    private void login(ActionEvent ae) {


        DataStorage dbh = new DBHandler();

        String UserName = userName.getText();
        String Password = password.getText();
        String sentPassword = dbh.matchPassword(UserName);

        if (UserName.isEmpty()) {

            alert.setContentText("You must enter a user name!");
            alert.show();
        }

        if (UserName.isEmpty() == false) {


            if (sentPassword.equals(Password)) {

                Node node = (Node) ae.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("customerLoggedIn.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();


                }
                Scene scene = new Scene(root);
                stage.setScene(scene);
            } else {
                alert.setContentText("Login failed!");
                alert.show();
            }
        }
    }

    @FXML
    private void newCustomer(ActionEvent ae) {


        Node node = (Node) ae.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("createAccount.fxml"));
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
