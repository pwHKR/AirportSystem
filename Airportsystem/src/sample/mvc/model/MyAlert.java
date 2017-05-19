package sample.mvc.model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Created by woojen on 2017-04-18.
 */
public class MyAlert {

    private Alert[] AlertType = new Alert[3];


    public MyAlert() {

        javafx.scene.control.Alert msg = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        javafx.scene.control.Alert err = new javafx.scene.control.Alert(Alert.AlertType.ERROR);
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);

        AlertType[0] = msg;
        AlertType[1] = err;
        AlertType[2] = confirm;


    }

    public void helpNewCustomer() {
        AlertType[0].setHeaderText("Create account help");
        AlertType[0].setContentText("In this screen you are able to create a new account and become a customer at PSTR-Airlines." +
                " Enter the appropriate infromation in each textfield to create an account." +
                " If you run into problems please contact the customer service via phone");
        AlertType[0].show();
    }

    public void helpLogIn() {
        AlertType[0].setHeaderText("Login help");
        AlertType[0].setTitle("Help");
        AlertType[0].setContentText("If you do not already have an account, press Create Account \nYou can recover your password" +
                " if you have lost it by pressing the Recover Password button.");
        AlertType[0].show();
    }

    public void emptyUserName() {
        AlertType[1].setContentText("You must enter a user name!");
        AlertType[1].show();
    }


    public void addressNameErr() {
        AlertType[1].setTitle("Error");
        AlertType[1].setHeaderText("Invalid info");
        AlertType[1].setContentText("Your address must contain street name and street number.");
        AlertType[1].showAndWait();
    }

    public void eMailNameErr() {

        AlertType[1].setTitle("Error");
        AlertType[1].setHeaderText("Invalid info");
        AlertType[1].setContentText("Email adress must contain ''@'' ");
        AlertType[1].showAndWait();
    }

    public void ssnInputErr() {


        AlertType[1].setTitle("Error");
        AlertType[1].setHeaderText("Invalid info");
        AlertType[1].setContentText("Your ssn must be 10 numbers");
        AlertType[1].showAndWait();
    }

    public void firstNameErr() {


        AlertType[1].setTitle("Error");
        AlertType[1].setHeaderText("Invalid info");
        AlertType[1].setContentText("Your name can only contain letters.");
        AlertType[1].showAndWait();
    }

    public void lastNameErr() {
        AlertType[1].setTitle("Error");
        AlertType[1].setHeaderText("Invalid info");
        AlertType[1].setContentText("Your last name can only contain letters.");
        AlertType[1].showAndWait();
    }

    public void loginFail() {

        AlertType[1].setTitle("Error");
        AlertType[1].setHeaderText("Invalid info");
        AlertType[1].setContentText("Login failed!");
        AlertType[1].show();
    }

    public void userNameErr() {
        AlertType[1].setTitle("Error");
        AlertType[1].setHeaderText("Invalid info");
        AlertType[1].setContentText("The username textfield can't be left empty.");
        AlertType[1].show();
    }

    public void passwordErr() {
        AlertType[1].setTitle("Error");
        AlertType[1].setHeaderText("Invalid info");
        AlertType[1].setContentText("The password field can't be left empty.");
        AlertType[1].show();
    }

    public void ssnExistsErr() {

        AlertType[1].setTitle("Error");
        AlertType[1].setHeaderText("ssn already exists");
        AlertType[1].setContentText("The ssn you input already exists");
        AlertType[1].showAndWait();
    }

    public void userNameExists() {

        AlertType[1].setTitle("Error");
        AlertType[1].setHeaderText("Username already exists");
        AlertType[1].setContentText("The Username you input already exists");
        AlertType[1].showAndWait();
    }

    public void requestSent() {

        AlertType[0].setContentText("request sent");
        AlertType[0].show();
    }

    public void helpAdmin() {
        AlertType[0].setHeaderText("Start help");
        AlertType[0].setTitle("Help");
        AlertType[0].setContentText("You are now logged in, click on one of the buttons to get to the next page where you can " +
                "continue to do your work.");
        AlertType[0].show();
    }

    public void helpViewWorkers() {
        AlertType[0].setHeaderText("View worker help");
        AlertType[0].setTitle("Help");
        AlertType[0].setContentText("In the left window you can see the workers system-ID. By clicking their id´s you will be able to see" +
                "more information in the right window. You can click Kick selected to remove any of the employees.");
        AlertType[0].show();
    }


    public void logoutInactivity() {
        AlertType[0].setHeaderText("Logged out");
        AlertType[0].setTitle("Logged out");
        AlertType[0].setContentText("You have been logged out due to inactivity");
        AlertType[0].show();
    }


    public void generalError() {
        AlertType[0].setHeaderText("Error");
        AlertType[0].setTitle("Error");
        AlertType[0].setContentText("Error");
        AlertType[0].showAndWait();
    }


    public void depositMsg(String amount) {
        AlertType[0].setHeaderText("Your account has been credited with +" + amount + " sek");
        AlertType[0].setTitle("Deposit successful");
        AlertType[0].setContentText("You will receive your bill on the mail within 7 days");
        AlertType[0].showAndWait();
    }

    public void noDateError() {
        AlertType[1].setTitle("Error");
        AlertType[1].setHeaderText("no date selected");
        AlertType[1].setContentText("You have to enter a date");
        AlertType[1].showAndWait();
    }

    public void noInformationInDatabase() {
        AlertType[1].setTitle("Error");
        AlertType[1].setHeaderText("No information in database");
        AlertType[1].setContentText("Sorry we couldn't find your user information in our database.");
        AlertType[1].showAndWait();
    }

    public void recoverPasswordHelp() {
        AlertType[0].setTitle("Information");
        AlertType[0].setHeaderText("Recover Password");
        AlertType[0].setContentText("To recover your password you have to fill in the username field and press recover password and the " +
                "program will display your password in the text area.");
        AlertType[0].showAndWait();
    }

    public void informationSaved() {
        AlertType[0].setTitle("Information");
        AlertType[0].setHeaderText("Your information has been saved");
        AlertType[0].setContentText("You can now go back to previous screen and log in");
        AlertType[0].showAndWait();
    }

    public void withdrawMsg(String amount) {
        AlertType[0].setHeaderText("You withdrew -" + amount + " sek");
        AlertType[0].setTitle("Withdraw successful");
        AlertType[0].setContentText("The money has been sent to your bank account");
        AlertType[0].showAndWait();
    }

    public void withdrawErr() {
        AlertType[1].setHeaderText("Withdraw failed");
        AlertType[1].setTitle("You can't withdraw that much");
        AlertType[1].setContentText("You don't have that much money to withdraw");
        AlertType[1].showAndWait();
    }

    public void billingHelp() {
        AlertType[0].setHeaderText("Manage your account billings");
        AlertType[0].setTitle("Withdraw or deposit money to your account");
        AlertType[0].setContentText("Enter the amount of money you wish to withdraw/deposit in the field below then click the button to " +
                "send your request");
        AlertType[0].showAndWait();
    }

    public void searchHelp() {
        AlertType[0].setHeaderText("Welcome to the main function of the PSTR booking system");
        AlertType[0].setTitle("Search for locations and book your trip.");
        AlertType[0].setContentText("This is where you can see what trips we are offering, enter a location name in the search field below and click" +
                "the search button");
        AlertType[0].showAndWait();
    }

    public void viewBookingsHelp() {
        AlertType[0].setHeaderText("You can see your bookings below");
        AlertType[0].setTitle("View bookings");
        AlertType[0].setContentText("In this window you are able to see your bookings. Click one of your bookings to see detailed information" +
                "and click edit to be able to edit/cancel your booking");
        AlertType[0].showAndWait();
    }

    public void helpCustomer() {
        AlertType[0].setHeaderText("This is the main menu");
        AlertType[0].setTitle("Main menu");
        AlertType[0].setContentText("This is where you choose what you want to do, you can click on any of the buttons to enter the next page where " +
                "you will be able to continue.");
        AlertType[0].showAndWait();
    }

    public void helpEmployee() {
        AlertType[0].setHeaderText("This is the main menu for employees");
        AlertType[0].setTitle("Main menu");
        AlertType[0].setContentText("Choose what you want to do by clicking one of the buttons then you are able to continue your work.");
        AlertType[0].showAndWait();
    }

    public void helpChangeFlightStatus() {
        AlertType[0].setHeaderText("This is where you change the status of flights");
        AlertType[0].setTitle("Change flight status");
        AlertType[0].setContentText("Click on any of the ID's on your left side and then you will se all information about that flight. You can change the " +
                "status by writing something in the textfield below.");
        AlertType[0].showAndWait();
    }

    public void noPriceError() {
        AlertType[1].setHeaderText("No price found");
        AlertType[1].setTitle("You have to  add a price");
        AlertType[1].setContentText("You have not input a correct price amount, make sure that the text field only contains numbers");
        AlertType[1].showAndWait();
    }

    public void noOriginEror() {
        AlertType[1].setHeaderText("Nothing selected");
        AlertType[1].setTitle("You have to select a from Id");
        AlertType[1].setContentText("You have to select a from id.");
        AlertType[1].showAndWait();
    }

    public void noToCountryCityAirportError() {
        AlertType[1].setHeaderText("Nothing selected");
        AlertType[1].setTitle("Specify the information");
        AlertType[1].setContentText("You have to select a country city and airport to travel to.");
        AlertType[1].showAndWait();
    }

    public void errorChangingFlightStatus() {
        AlertType[1].setHeaderText("Not enough information selected");
        AlertType[1].setTitle("You have not entered enough information");
        AlertType[1].setContentText("You have to both select a flight id and write a status in the " +
                "text field below");
        AlertType[1].showAndWait();
    }

    public void flighStatusChanged() {
        AlertType[0].setHeaderText("Your new status has been sent to the database");
        AlertType[0].setTitle("The flightStatus has been changed");
        AlertType[0].showAndWait();
    }


    public boolean confirmBooking(double totalPrice) {

        boolean choice;

        AlertType[2].setTitle("Confirm booking");
        AlertType[2].setHeaderText("Are you sure you want to confirm your booking?");
        AlertType[2].setContentText("Total price: " + String.valueOf(totalPrice) + " sek");

        Optional<ButtonType> result = AlertType[2].showAndWait();
        // ... user chose OK
// ... user chose CANCEL or closed the dialog
        choice = result.get() == ButtonType.OK;
        return choice;
    }

    public boolean confirmCancelBooking() {

        boolean choice;

        AlertType[2].setTitle("Confirm Cancel");
        AlertType[2].setHeaderText("Are you sure you want to cancel the booking?");
        AlertType[2].setContentText("Yes or No");

        Optional<ButtonType> result = AlertType[2].showAndWait();
        // ... user chose OK
// ... user chose CANCEL or closed the dialog
        choice = result.get() == ButtonType.OK;
        return choice;
    }

    public void bookingConfirmed() {
        AlertType[0].setHeaderText("Booking confirmed");
        AlertType[0].setTitle("Your booking has been confirmed.");
        AlertType[0].setContentText("Your booking is now completed, thank you for booking with PSTR - airlines");
        AlertType[0].showAndWait();

    }

    public void bookingConfirmHelp() {
        AlertType[0].setHeaderText("This is the scene where you have to confirm your booking");
        AlertType[0].setTitle("Confirm your booking");
        AlertType[0].setContentText("Enter how many passengers you want and enter how many kilo's weight you will bring " +
                "on the airplane then press confirm when you are done.");
        AlertType[0].showAndWait();
    }

    public void luggageMaxMsg(String maxWeight) {
        AlertType[0].setHeaderText("The max weight for a passenger is :" + maxWeight);
        AlertType[0].setTitle("Please edit luggage weight");
        AlertType[0].setContentText("Please edit the luggage weight in order to confirm your booking!");
        AlertType[0].showAndWait();
    }

    public void soldOutMsg() {
        AlertType[0].setHeaderText("Trip is sold out!");
        AlertType[0].setTitle("Sorry");
        AlertType[0].setContentText("Please choose a new trip");
        AlertType[0].showAndWait();
    }


    public void nameNotFound() {
        AlertType[0].setHeaderText("Username not found");
        AlertType[0].setTitle("Sorry");
        AlertType[0].setContentText("Please write your username again");
        AlertType[0].showAndWait();
    }

    public void billingSent(String adress) {
        AlertType[0].setHeaderText("Booking confirmed");
        AlertType[0].setTitle("Success");
        AlertType[0].setContentText("An invoice has been sent to " + adress);
        AlertType[0].showAndWait();
    }

    public void ssnDoesNotExist() {
        AlertType[1].setHeaderText("Invalid ssn");
        AlertType[1].setTitle("The ssn you input does not exist");
        AlertType[1].setContentText("You must input a valid ssn");
        AlertType[1].showAndWait();
    }

    public void noBookingSelectedError() {
        AlertType[1].setHeaderText("No booking selected");
        AlertType[1].setContentText("You must select a specific booking to edit.");
        AlertType[1].showAndWait();
    }

    public void noLocationSelectedError() {
        AlertType[1].setHeaderText("No location selected");
        AlertType[1].setContentText("You must select a specific location.");
        AlertType[1].showAndWait();
    }

    public void invalidPasswordError() {
        AlertType[1].setHeaderText("Invalid Password");
        AlertType[1].setContentText("Your password is incorrect");
        AlertType[1].showAndWait();
    }

    public void noGateOrPlaneError() {
        AlertType[1].setHeaderText("no gate or airplane selected");
        AlertType[1].setContentText("You have to select a gate and a airplane for your flight.");
        AlertType[1].showAndWait();
    }

    public void noEmployeeSelectedError() {
        AlertType[1].setHeaderText("No employee selected");
        AlertType[1].setContentText("You have to select an employeeId before you can remove them from the database");
        AlertType[1].showAndWait();
    }

    public void planeRegNumberExists() {
        AlertType[1].setHeaderText("Invalid reg number");
        AlertType[1].setContentText("The reg number already exists in the database.");
        AlertType[1].showAndWait();
    }

    public void noPstrIdSelectedError() {
        AlertType[1].setHeaderText("No id selected");
        AlertType[1].setContentText("Select a pstrId in order to make an airplane.");
        AlertType[1].showAndWait();
    }

    public void noLuggageWeightError() {
        AlertType[1].setTitle("Error");
        AlertType[1].setHeaderText("No luggage weight");
        AlertType[1].setContentText("Specify the max luggage weight per passenger.");
        AlertType[1].showAndWait();
    }

    public void noRegNumberError() {
        AlertType[1].setHeaderText("No red number");
        AlertType[1].setContentText("Specify the reg number for the plane.");
        AlertType[1].showAndWait();
    }

    public void noMaxSpeedError() {
        AlertType[1].setHeaderText("No max speed");
        AlertType[1].setContentText("Specify the max speed of the plane.");
        AlertType[1].showAndWait();
    }

    public void noPassengerAmountError() {
        AlertType[1].setHeaderText("No max passenger amount");
        AlertType[1].setContentText("Specify how many passengers can be aboard the plane.");
        AlertType[1].showAndWait();
    }

    public void noPlaneModelError() {
        AlertType[1].setHeaderText("No plane model");
        AlertType[1].setContentText("Input a plane model.");
        AlertType[1].showAndWait();
    }

    public void noAirportCityCountryError() {
        AlertType[1].setHeaderText("Specify information");
        AlertType[1].setContentText("You have to select a country city and airport.");
        AlertType[1].showAndWait();
    }
}