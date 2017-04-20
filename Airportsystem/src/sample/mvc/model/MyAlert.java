package sample.mvc.model;

import javafx.scene.control.Alert;

/**
 * Created by woojen on 2017-04-18.
 */
public class MyAlert {

    static javafx.scene.control.Alert msg = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
    static javafx.scene.control.Alert err = new javafx.scene.control.Alert(Alert.AlertType.ERROR);

    public static void helpNewCustomer() {
        msg.setHeaderText("Create account helpNewCustomer");
        msg.setContentText("In this screen you are able to create a new account and become a customer at PSTR-Airlines." +
                " Enter the appropriate infromation in each textfield to create an account." +
                " If you run into problems please contact the customer service via phone");
        msg.show();
    }

    public static void helpLogIn() {
        msg.setHeaderText("Login helpNewCustomer");
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
}
