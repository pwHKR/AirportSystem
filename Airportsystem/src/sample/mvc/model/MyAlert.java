package sample.mvc.model;

import javafx.scene.control.Alert;

/**
 * Created by woojen on 2017-04-18.
 */
public class MyAlert {

    static javafx.scene.control.Alert msg = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
    static javafx.scene.control.Alert err = new javafx.scene.control.Alert(Alert.AlertType.ERROR);

    public static void helpNewCustomer() {
        msg.setHeaderText("Create account help");
        msg.setContentText("In this screen you are able to create a new account and become a customer at PSTR-Airlines." +
                " Enter the appropriate infromation in each textfield to create an account." +
                " If you run into problems please contact the customer service via phone");
        msg.show();
    }

    public static void helpLogIn() {
        msg.setHeaderText("Login help");
        msg.setTitle("Help");
        msg.setContentText("If you do not already have an account, press Create Account \nYou can recover your password" +
                " if you have lost it by pressing the Recover Password button.");
        msg.show();
    }

    public static void emptyUserName() {
        err.setContentText("You must enter a user name!");
        err.show();
    }

    public static void countryNameErr() {
        err.setTitle("Error");
        err.setHeaderText("Invalid info");
        err.setContentText("Your country name can only contain letters.");
        err.showAndWait();
    }

    public static void addressNameErr() {
        err.setTitle("Error");
        err.setHeaderText("Invalid info");
        err.setContentText("Your address must contain street name and street number.");
        err.showAndWait();
    }

    public static void eMailNameErr() {

        err.setTitle("Error");
        err.setHeaderText("Invalid info");
        err.setContentText("Email adress must contain ''@'' ");
        err.showAndWait();


    }

    public static void ssnInputErr() {


        err.setTitle("Error");
        err.setHeaderText("Invalid info");
        err.setContentText("Your ssn must be 10 numbers");
        err.showAndWait();
    }

    public static void firstNameErr() {


        err.setTitle("Error");
        err.setHeaderText("Invalid info");
        err.setContentText("Your name can only contain letters.");
        err.showAndWait();
    }

    public static void lastNameErr() {
        err.setTitle("Error");
        err.setHeaderText("Invalid info");
        err.setContentText("Your last name can only contain letters.");
        err.showAndWait();
    }

    public static void loginFail() {

        err.setTitle("Error");
        err.setHeaderText("Invalid info");
        err.setContentText("Login failed!");
        err.show();
    }

    public static void userNameErr() {
        err.setTitle("Error");
        err.setHeaderText("Invalid info");
        err.setContentText("The username textfield can't be left empty.");
        err.show();
    }

    public static void passwordErr() {
        err.setTitle("Error");
        err.setHeaderText("Invalid info");
        err.setContentText("The password field can't be left empty.");
        err.show();
    }

    public static void ssnExistsErr() {

        err.setTitle("Error");
        err.setHeaderText("ssn already exists");
        err.setContentText("The ssn you input already exists");
        err.showAndWait();
    }

    public static void userNameExists() {

        err.setTitle("Error");
        err.setHeaderText("Username already exists");
        err.setContentText("The Username you input already exists");
        err.showAndWait();
    }

    public static void requestSent() {

        msg.setContentText("request sent");
        msg.show();
    }

    public static void helpAdmin() {
        msg.setHeaderText("Start help");
        msg.setTitle("Help");
        msg.setContentText("You are now logged in, click on one of the buttons to get to the next page where you can " +
                "continue to do your work.");
        msg.show();
    }

    public static void helpViewWorkers() {
        msg.setHeaderText("View worker help");
        msg.setTitle("Help");
        msg.setContentText("In the left window you can see the workers system-ID. By clicking their id´s you will be able to see" +
                "more information in the right window. You can click Kick selected to remove any of the employees.");
        msg.show();
    }


    public static void logoutInactivity() {
        msg.setHeaderText("Logged out");
        msg.setTitle("Logged out");
        msg.setContentText("You have been logged out due to inactivity");
        msg.show();
    }


}
