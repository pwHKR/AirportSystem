package sample.mvc.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.mvc.model.DBHandler;
import sample.mvc.model.DataStorage;
import sample.mvc.model.MyAlert;
import sample.mvc.model.SwitchScene;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLNonTransientConnectionException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    SwitchScene sw = new SwitchScene();

    @FXML
    private PasswordField password;

    @FXML
    private TextField userName;

    private Path path = Paths.get("logInLog.bin");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        DataStorage dbhandler = new DBHandler();

        System.out.println(dbhandler.getUsersOnlineCount());

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

            if (sentPassword.equals(Password) == false) {

                MyAlert.loginFail();
            }

            if (sentPassword.equals(Password) && isAdmin == true) {

                sw.GoTo(ae, "Admin.fxml");
            }
        }


        if (sentPassword.equals(Password) && isAdmin == false) {

            sw.GoTo(ae, "Customer.fxml");
            dbh.setUserOnline(UserName);
        }

    }


    @FXML
    private void newCustomer(ActionEvent ae) {
        NewUserController.setCustomer(true);

        sw.GoTo(ae, "NewUser.fxml");
    }
}
