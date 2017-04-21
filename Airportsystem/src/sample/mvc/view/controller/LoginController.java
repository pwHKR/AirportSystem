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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private PasswordField password;

    @FXML
    private TextField userName;

    private Path path = Paths.get("logInLog.bin");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            List<String> textLines = Files.readAllLines(path);
            userName.setText(textLines.get(0));
            password.setText(textLines.get(1));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    private void help() {
        MyAlert.helpLogIn();
    }

    @FXML
    private void login(ActionEvent ae) throws SQLNonTransientConnectionException {
        DataStorage dbh = new DBHandler();
        ArrayList<String> loginInformation = new ArrayList<>();

        String UserName = userName.getText();
        String Password = password.getText();
        loginInformation.add(UserName);
        loginInformation.add(Password);
        String sentPassword = dbh.matchPassword(UserName);

        try {
            Files.write(path, loginInformation, StandardOpenOption.CREATE);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Admin.fxml"));
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

        if (sentPassword.equals(Password) && isAdmin == false) {

            Node node = (Node) ae.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Customer.fxml"));
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../NewUser.fxml"));
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
