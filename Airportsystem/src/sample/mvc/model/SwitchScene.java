package sample.mvc.model;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by woojen on 2017-04-22.
 */
public class SwitchScene {

    private MyAlert myAlert = new MyAlert();

    private boolean userLoggedOut; //Checks if the user logged out voluntarily or not


    public void GoTo(ActionEvent ae, String fxmlFile) {

        boolean isOnline;

        isOnline = checkIfOnline();

        if (isOnline == false && userLoggedOut == false) {

            myAlert.logoutInactivity();
            fxmlFile = "Login.fxml";

        }


        Node node = (Node) ae.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/" + "" + fxmlFile + ""));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

    public boolean checkIfOnline() {

        Path path = Paths.get("../view/logInLog.bin");

        boolean isOnline = false;

        DataStorage dbh = new DBHandler();

        try {
            List<String> textLines = Files.readAllLines(path);

            String userName = textLines.get(0);


            if (textLines.get(0).matches("admin")) {

                isOnline = true; // Admin cant be logged out beacuse of inactivity.
                // Therefore admin is always online according to java (but not to the datastorage)
            } else {
                isOnline = dbh.isUserOnline(userName);

                if (isOnline == true) {

                    dbh.updateTimeStampUser(textLines.get(0));

                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }


        return isOnline;

    }

    // not used, remove?
    /*public boolean isUserLoggedOut() {
        return userLoggedOut;
    }*/

    public void setUserLoggedOut(boolean userLoggedOut) {
        this.userLoggedOut = userLoggedOut;
    }


    public void goToUnLogged(ActionEvent ae, String fxmlFile) {

        Node node = (Node) ae.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/" + "" + fxmlFile + ""));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

    public void gotoCheckUserType(ActionEvent ae, String fxmlAdmin, String fxmlCustomer, String fxmlEmployee) {
        boolean isOnline;
        DataStorage dbh = new DBHandler();
        LocalFileStorage localFileStorage = new LocalFileStorage();
        String userType = dbh.printUserType(localFileStorage.getCurrentUsersUserName());
        isOnline = checkIfOnline();

        //checks if the user is online otherwise you will come to startscreen
        if (isOnline == false && userLoggedOut == false) {
            myAlert.logoutInactivity();
            fxmlAdmin = "Login.fxml";
            fxmlEmployee = "Login.fxml";
            fxmlCustomer = "Login.fxml";
        }

        Node node = (Node) ae.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        if (userType.matches("Admin")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/" + "" + fxmlAdmin + ""));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }

        if (userType.matches("Employee")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/" + "" + fxmlEmployee + ""));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }

        if (userType.matches("Customer")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/" + "" + fxmlCustomer + ""));
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
}
