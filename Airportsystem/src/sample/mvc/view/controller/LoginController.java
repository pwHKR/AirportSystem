package sample.mvc.view.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.mvc.model.DBHandler;
import sample.mvc.model.DataStorage;
import sample.mvc.model.MyAlert;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    @FXML
    PasswordField password;
    @FXML
    TextField userName;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void help() {
        MyAlert.helpLogIn();
    }

    @FXML
    private void login(ActionEvent ae) {


        DataStorage dbh = new DBHandler();

        String UserName = userName.getText();
        String Password = password.getText();
        String sentPassword = dbh.matchPassword(UserName);

        boolean isAdmin = false;

        if (UserName.equals("admin")) {

            isAdmin = true;
        }


        if (UserName.isEmpty()) {

            MyAlert.emptyUserName();
        }

        if (UserName.isEmpty() == false) {

            if (sentPassword.equals(Password) && isAdmin == true) {


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
        }

        if (sentPassword.equals(Password) && isAdmin == false)

        {

            Node node = (Node) ae.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../customerLoggedIn.fxml"));
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


    @FXML
    private void newCustomer(ActionEvent ae) {


        NewUserController.setCustomer(true);


        Node node = (Node) ae.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../createAccount.fxml"));
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