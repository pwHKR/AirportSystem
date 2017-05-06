package sample.mvc.model;

import javafx.scene.control.Alert;

/**
 * Created by woojen on 2017-04-18.
 */
public class MyAlert {

    private javafx.scene.control.Alert msg = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
    private javafx.scene.control.Alert err = new javafx.scene.control.Alert(Alert.AlertType.ERROR);

    public void helpNewCustomer() {
        msg.setHeaderText("Create account help");
        msg.setContentText("In this screen you are able to create a new account and become a customer at PSTR-Airlines." +
                " Enter the appropriate infromation in each textfield to create an account." +
                " If you run into problems please contact the customer service via phone");
        msg.show();
    }

    public void helpLogIn() {
        msg.setHeaderText("Login help");
        msg.setTitle("Help");
        msg.setContentText("If you do not already have an account, press Create Account \nYou can recover your password" +
                " if you have lost it by pressing the Recover Password button.");
        msg.show();
    }

    public void emptyUserName() {
        err.setContentText("You must enter a user name!");
        err.show();
    }

    public void countryNameErr() {
        err.setTitle("Error");
        err.setHeaderText("Invalid info");
        err.setContentText("Your country name can only contain letters.");
        err.showAndWait();
    }

    public void addressNameErr() {
        err.setTitle("Error");
        err.setHeaderText("Invalid info");
        err.setContentText("Your address must contain street name and street number.");
        err.showAndWait();
    }

    public void eMailNameErr() {

        err.setTitle("Error");
        err.setHeaderText("Invalid info");
        err.setContentText("Email adress must contain ''@'' ");
        err.showAndWait();


    }

    public void ssnInputErr() {


        err.setTitle("Error");
        err.setHeaderText("Invalid info");
        err.setContentText("Your ssn must be 10 numbers");
        err.showAndWait();
    }

    public void firstNameErr() {


        err.setTitle("Error");
        err.setHeaderText("Invalid info");
        err.setContentText("Your name can only contain letters.");
        err.showAndWait();
    }

    public void lastNameErr() {
        err.setTitle("Error");
        err.setHeaderText("Invalid info");
        err.setContentText("Your last name can only contain letters.");
        err.showAndWait();
    }

    public void loginFail() {

        err.setTitle("Error");
        err.setHeaderText("Invalid info");
        err.setContentText("Login failed!");
        err.show();
    }

    public void userNameErr() {
        err.setTitle("Error");
        err.setHeaderText("Invalid info");
        err.setContentText("The username textfield can't be left empty.");
        err.show();
    }

    public void passwordErr() {
        err.setTitle("Error");
        err.setHeaderText("Invalid info");
        err.setContentText("The password field can't be left empty.");
        err.show();
    }

    public void ssnExistsErr() {

        err.setTitle("Error");
        err.setHeaderText("ssn already exists");
        err.setContentText("The ssn you input already exists");
        err.showAndWait();
    }

    public void userNameExists() {

        err.setTitle("Error");
        err.setHeaderText("Username already exists");
        err.setContentText("The Username you input already exists");
        err.showAndWait();
    }

    public void requestSent() {

        msg.setContentText("request sent");
        msg.show();
    }

    public void helpAdmin() {
        msg.setHeaderText("Start help");
        msg.setTitle("Help");
        msg.setContentText("You are now logged in, click on one of the buttons to get to the next page where you can " +
                "continue to do your work.");
        msg.show();
    }

    public void helpViewWorkers() {
        msg.setHeaderText("View worker help");
        msg.setTitle("Help");
        msg.setContentText("In the left window you can see the workers system-ID. By clicking their id´s you will be able to see" +
                "more information in the right window. You can click Kick selected to remove any of the employees.");
        msg.show();
    }


    public void logoutInactivity() {
        msg.setHeaderText("Logged out");
        msg.setTitle("Logged out");
        msg.setContentText("You have been logged out due to inactivity");
        msg.show();
    }


    public void generalError() {
        msg.setHeaderText("Error");
        msg.setTitle("Error");
        msg.setContentText("Error");
        msg.showAndWait();
    }


    public void depositMsg(String amount) {
        msg.setHeaderText("Your account has been credited with +" + amount + " sek");
        msg.setTitle("Deposit successful");
        msg.setContentText("You will receive your bill on the mail within 7 days");
        msg.showAndWait();
    }

    public void noDateError() {
        err.setTitle("Error");
        err.setHeaderText("no date selected");
        err.setContentText("You have to enter a date");
        err.showAndWait();
    }

    public void noInformationInDatabase() {
        err.setTitle("Error");
        err.setHeaderText("No information in database");
        err.setContentText("Sorry we couldn't find your user information in our database.");
        err.showAndWait();
    }

    public void recoverPasswordHelp() {
        msg.setTitle("Information");
        msg.setHeaderText("Recover Password");
        msg.setContentText("To recover your password you have to fill in the username field and press recover password and the " +
                "program will display your password in the text area.");
        msg.showAndWait();
    }

    public void informationSaved() {
        msg.setTitle("Information");
        msg.setHeaderText("Your information has been saved");
        msg.setContentText("You can now go back to previous screen and log in");
        msg.showAndWait();
    }

    public void withdrawMsg(String amount) {
        msg.setHeaderText("You withdrew -" + amount + " sek");
        msg.setTitle("Withdraw successful");
        msg.setContentText("The money has been sent to your bank account");
        msg.showAndWait();
    }

    public void withdrawErr() {
        err.setHeaderText("Withdraw failed");
        err.setTitle("You can't withdraw that much");
        err.setContentText("You don't have that much money to withdraw");
        err.showAndWait();
    }

    public void billingHelp() {
        msg.setHeaderText("Manage your account billings");
        msg.setTitle("Withdraw or deposit money to your account");
        msg.setContentText("Enter the amount of money you wish to withdraw/deposit in the field below then click the button to " +
                "send your request");
        msg.showAndWait();
    }
}
