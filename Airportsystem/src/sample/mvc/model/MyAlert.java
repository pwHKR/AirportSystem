package sample.mvc.model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Created by woojen on 2017-04-18.
 */
public class MyAlert {

    private Alert[] alertType = new Alert[3];


    public MyAlert() {

        javafx.scene.control.Alert msg = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        javafx.scene.control.Alert err = new javafx.scene.control.Alert(Alert.AlertType.ERROR);
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);

        alertType[0] = msg; //This one is used for normal messages
        alertType[1] = err; //This one is used for error messages
        alertType[2] = confirm; //This one is used as confirm dialogues


    }

    public void helpNewCustomer() {
        alertType[0].setHeaderText("Create account help");
        alertType[0].setContentText("In this screen you are able to create a new account and become a customer at PSTR-Airlines." +
                " Enter the appropriate infromation in each textfield to create an account." +
                " If you run into problems please contact the customer service via phone");
        alertType[0].show();
    }

    public void helpLogIn() {
        alertType[0].setHeaderText("Login help");
        alertType[0].setTitle("Help");
        alertType[0].setContentText("If you do not already have an account, press Create Account \nYou can recover your password" +
                " if you have lost it by pressing the Recover Password button.");
        alertType[0].show();
    }

    public void addressNameErr() {
        alertType[1].setTitle("Error");
        alertType[1].setHeaderText("Invalid info");
        alertType[1].setContentText("Your address must contain street name and street number.");
        alertType[1].showAndWait();
    }

    public void eMailNameErr() {

        alertType[1].setTitle("Error");
        alertType[1].setHeaderText("Invalid info");
        alertType[1].setContentText("Email adress must contain ''@'' ");
        alertType[1].showAndWait();
    }

    public void ssnInputErr() {


        alertType[1].setTitle("Error");
        alertType[1].setHeaderText("Invalid info");
        alertType[1].setContentText("Your ssn must be 10 numbers");
        alertType[1].showAndWait();
    }

    public void firstNameErr() {


        alertType[1].setTitle("Error");
        alertType[1].setHeaderText("Invalid info");
        alertType[1].setContentText("Your name can only contain letters.");
        alertType[1].showAndWait();
    }

    public void lastNameErr() {
        alertType[1].setTitle("Error");
        alertType[1].setHeaderText("Invalid info");
        alertType[1].setContentText("Your last name can only contain letters.");
        alertType[1].showAndWait();
    }

    public void loginFail() {

        alertType[1].setTitle("Error");
        alertType[1].setHeaderText("Invalid info");
        alertType[1].setContentText("Login failed!");
        alertType[1].show();
    }

    public void userNameErr() {
        alertType[1].setTitle("Error");
        alertType[1].setHeaderText("Invalid info");
        alertType[1].setContentText("The username textfield can't be left empty.");
        alertType[1].show();
    }

    public void passwordErr() {
        alertType[1].setTitle("Error");
        alertType[1].setHeaderText("Invalid info");
        alertType[1].setContentText("The password field can't be left empty.");
        alertType[1].show();
    }

    public void ssnExistsErr() {

        alertType[1].setTitle("Error");
        alertType[1].setHeaderText("ssn already exists");
        alertType[1].setContentText("The ssn you input already exists");
        alertType[1].showAndWait();
    }

    public void userNameExists() {

        alertType[1].setTitle("Error");
        alertType[1].setHeaderText("Username already exists");
        alertType[1].setContentText("The Username you input already exists");
        alertType[1].showAndWait();
    }

    public void requestSent() {

        alertType[0].setContentText("request sent");
        alertType[0].show();
    }

    public void helpAdmin() {
        alertType[0].setHeaderText("Start help");
        alertType[0].setTitle("Help");
        alertType[0].setContentText("You are now logged in, click on one of the buttons to get to the next page where you can " +
                "continue to do your work.");
        alertType[0].show();
    }

    public void helpViewWorkers() {
        alertType[0].setHeaderText("View worker help");
        alertType[0].setTitle("Help");
        alertType[0].setContentText("In the left window you can see the workers system-ID. By clicking their id´s you will be able to see" +
                "more information in the right window. You can click Kick selected to remove any of the employees.");
        alertType[0].show();
    }


    public void logoutInactivity() {
        alertType[0].setHeaderText("Logged out");
        alertType[0].setTitle("Logged out");
        alertType[0].setContentText("You have been logged out due to inactivity");
        alertType[0].show();
    }


    public void generalError() {
        alertType[0].setHeaderText("Error");
        alertType[0].setTitle("Error");
        alertType[0].setContentText("Error");
        alertType[0].showAndWait();
    }


    public void depositMsg(String amount) {
        alertType[0].setHeaderText("Your account has been credited with +" + amount + " sek");
        alertType[0].setTitle("Deposit successful");
        alertType[0].setContentText("You will receive your bill on the mail within 7 days");
        alertType[0].showAndWait();
    }

    public void noDateError() {
        alertType[1].setTitle("Error");
        alertType[1].setHeaderText("no date selected");
        alertType[1].setContentText("You have to enter a date");
        alertType[1].showAndWait();
    }

    public void noInformationInDatabase() {
        alertType[1].setTitle("Error");
        alertType[1].setHeaderText("No information in database");
        alertType[1].setContentText("Sorry we couldn't find your user information in our database.");
        alertType[1].showAndWait();
    }

    public void recoverPasswordHelp() {
        alertType[0].setTitle("Information");
        alertType[0].setHeaderText("Recover Password");
        alertType[0].setContentText("To recover your password you have to fill in the username field and press recover password and the " +
                "program will display your password in the text area.");
        alertType[0].showAndWait();
    }

    public void informationSaved() {
        alertType[0].setTitle("Information");
        alertType[0].setHeaderText("Your information has been saved");
        alertType[0].setContentText("You can now go back to previous screen and log in");
        alertType[0].showAndWait();
    }

    public void withdrawMsg(String amount) {
        alertType[0].setHeaderText("You withdrew -" + amount + " sek");
        alertType[0].setTitle("Withdraw successful");
        alertType[0].setContentText("The money has been sent to your bank account");
        alertType[0].showAndWait();
    }

    public void withdrawErr() {
        alertType[1].setHeaderText("Withdraw failed");
        alertType[1].setTitle("You can't withdraw that much");
        alertType[1].setContentText("You don't have that much money to withdraw");
        alertType[1].showAndWait();
    }

    public void billingHelp() {
        alertType[0].setHeaderText("Manage your account billings");
        alertType[0].setTitle("Withdraw or deposit money to your account");
        alertType[0].setContentText("Frequent bookings are rewarded bonus points " +
                "that give you discounts on bookings.");
        alertType[0].showAndWait();
    }

    public void searchHelp() {
        alertType[0].setHeaderText("Welcome to the main function of the PSTR booking system");
        alertType[0].setTitle("Search for locations and book your trip.");
        alertType[0].setContentText("This is where you can see what trips we are offering, enter a location name in the search field below and click" +
                "the search button");
        alertType[0].showAndWait();
    }

    public void viewBookingsHelp() {
        alertType[0].setHeaderText("You can see your bookings below");
        alertType[0].setTitle("View bookings");
        alertType[0].setContentText("In this window you are able to see your bookings. Click one of your bookings to see detailed information" +
                "and click edit to be able to edit/cancel your booking");
        alertType[0].showAndWait();
    }

    public void helpCustomer() {
        alertType[0].setHeaderText("This is the main menu");
        alertType[0].setTitle("Main menu");
        alertType[0].setContentText("This is where you choose what you want to do, you can click on any of the buttons to enter the next page where " +
                "you will be able to continue.");
        alertType[0].showAndWait();
    }

    public void helpEmployee() {
        alertType[0].setHeaderText("This is the main menu for employees");
        alertType[0].setTitle("Main menu");
        alertType[0].setContentText("Choose what you want to do by clicking one of the buttons then you are able to continue your work.");
        alertType[0].showAndWait();
    }

    public void helpChangeFlightStatus() {
        alertType[0].setHeaderText("This is where you change the status of flights");
        alertType[0].setTitle("Change flight status");
        alertType[0].setContentText("Click on any of the ID's on your left side and then you will se all information about that flight. You can change the " +
                "status by writing something in the textfield below.");
        alertType[0].showAndWait();
    }

    public void noPriceError() {
        alertType[1].setHeaderText("No price found");
        alertType[1].setTitle("You have to  add a price");
        alertType[1].setContentText("You have not input a correct price amount, make sure that the text field only contains numbers");
        alertType[1].showAndWait();
    }

    public void noOriginEror() {
        alertType[1].setHeaderText("Nothing selected");
        alertType[1].setTitle("You have to select a from Id");
        alertType[1].setContentText("You have to select a from id.");
        alertType[1].showAndWait();
    }

    public void noToCountryCityAirportError() {
        alertType[1].setHeaderText("Nothing selected");
        alertType[1].setTitle("Specify the information");
        alertType[1].setContentText("You have to select a country city and airport to travel to.");
        alertType[1].showAndWait();
    }

    public void errorChangingFlightStatus() {
        alertType[1].setHeaderText("Not enough information selected");
        alertType[1].setTitle("You have not entered enough information");
        alertType[1].setContentText("You have to both select a flight id and write a status in the " +
                "text field below");
        alertType[1].showAndWait();
    }

    public void flighStatusChanged() {
        alertType[0].setHeaderText("Your new status has been sent to the database");
        alertType[0].setTitle("The flightStatus has been changed");
        alertType[0].showAndWait();
    }


    public boolean confirmBooking(double totalPrice) {

        boolean choice;

        alertType[2].setTitle("Confirm booking");
        alertType[2].setHeaderText("Are you sure you want to confirm your booking?");
        alertType[2].setContentText("Total price: " + String.valueOf(totalPrice) + " sek");

        Optional<ButtonType> result = alertType[2].showAndWait();
        // ... user chose OK
// ... user chose CANCEL or closed the dialog
        choice = result.get() == ButtonType.OK;
        return choice;
    }

    public boolean confirmCancelBooking() {

        boolean choice;

        alertType[2].setTitle("Confirm Cancel");
        alertType[2].setHeaderText("Are you sure you want to cancel the booking?");
        alertType[2].setContentText("Yes or No");

        Optional<ButtonType> result = alertType[2].showAndWait();
        // ... user chose OK
// ... user chose CANCEL or closed the dialog
        choice = result.get() == ButtonType.OK;
        return choice;
    }

    public void bookingConfirmed() {
        alertType[0].setHeaderText("Booking confirmed");
        alertType[0].setTitle("Your booking has been confirmed.");
        alertType[0].setContentText("Your booking is now completed, thank you for booking with PSTR - airlines");
        alertType[0].showAndWait();

    }

    public void bookingConfirmHelp() {
        alertType[0].setHeaderText("This is the scene where you have to confirm your booking");
        alertType[0].setTitle("Confirm your booking");
        alertType[0].setContentText("Enter how many passengers you want and enter how many kilo's weight you will bring " +
                "on the airplane then press confirm when you are done.");
        alertType[0].showAndWait();
    }

    public void luggageMaxMsg(String maxWeight) {
        alertType[0].setHeaderText("The max weight is that you can book is :" + maxWeight + " kg");
        alertType[0].setTitle("Please edit luggage weight");
        alertType[0].setContentText("Please edit the luggage weight in order to confirm your booking!");
        alertType[0].showAndWait();
    }

    public void soldOutMsg() {
        alertType[0].setHeaderText("Trip is sold out!");
        alertType[0].setTitle("Sorry");
        alertType[0].setContentText("Please choose a new trip");
        alertType[0].showAndWait();
    }


    public void nameNotFound() {
        alertType[0].setHeaderText("Username not found");
        alertType[0].setTitle("Sorry");
        alertType[0].setContentText("Please write your username again");
        alertType[0].showAndWait();
    }

    public void billingSent(String adress) {
        alertType[0].setHeaderText("Booking confirmed");
        alertType[0].setTitle("Success");
        alertType[0].setContentText("An invoice has been sent to " + adress);
        alertType[0].showAndWait();
    }

    public void ssnDoesNotExist() {
        alertType[1].setHeaderText("Invalid ssn");
        alertType[1].setTitle("The ssn you input does not exist");
        alertType[1].setContentText("You must input a valid ssn");
        alertType[1].showAndWait();
    }

    public void noBookingSelectedError() {
        alertType[1].setHeaderText("No booking selected");
        alertType[1].setContentText("You must select a specific booking to edit.");
        alertType[1].showAndWait();
    }

    public void noLocationSelectedError() {
        alertType[1].setHeaderText("No location selected");
        alertType[1].setContentText("You must select a specific location.");
        alertType[1].showAndWait();
    }

    public void invalidPasswordError() {
        alertType[1].setHeaderText("Invalid Password");
        alertType[1].setContentText("Your password is incorrect");
        alertType[1].showAndWait();
    }

    public void noGateOrPlaneError() {
        alertType[1].setHeaderText("no gate or airplane selected");
        alertType[1].setContentText("You have to select a gate and a airplane for your flight.");
        alertType[1].showAndWait();
    }

    public void noEmployeeSelectedError() {
        alertType[1].setHeaderText("No employee selected");
        alertType[1].setContentText("You have to select an employeeId before you can remove them from the database");
        alertType[1].showAndWait();
    }

    public void planeRegNumberExists() {
        alertType[1].setHeaderText("Invalid reg number");
        alertType[1].setContentText("The reg number already exists in the database.");
        alertType[1].showAndWait();
    }

    public void noPstrIdSelectedError() {
        alertType[1].setHeaderText("No id selected");
        alertType[1].setContentText("Select a pstrId in order to make an airplane.");
        alertType[1].showAndWait();
    }

    public void noLuggageWeightError() {
        alertType[1].setTitle("Error");
        alertType[1].setHeaderText("No luggage weight");
        alertType[1].setContentText("Specify the max luggage weight per passenger.");
        alertType[1].showAndWait();
    }

    public void noRegNumberError() {
        alertType[1].setHeaderText("No red number");
        alertType[1].setContentText("Specify the reg number for the plane.");
        alertType[1].showAndWait();
    }

    public void noMaxSpeedError() {
        alertType[1].setHeaderText("No max speed");
        alertType[1].setContentText("Specify the max speed of the plane.");
        alertType[1].showAndWait();
    }

    public void noPassengerAmountError() {
        alertType[1].setHeaderText("No max passenger amount");
        alertType[1].setContentText("Specify how many passengers can be aboard the plane.");
        alertType[1].showAndWait();
    }

    public void noPlaneModelError() {
        alertType[1].setHeaderText("No plane model");
        alertType[1].setContentText("Input a plane model.");
        alertType[1].showAndWait();
    }

    public void noAirportCityCountryError() {
        alertType[1].setHeaderText("Specify information");
        alertType[1].setContentText("You have to select a country city and airport.");
        alertType[1].showAndWait();
    }

    public void newPSTRHelp() {
        alertType[0].setHeaderText("This is where you assign a new PSTR location");
        alertType[0].setTitle("New PSRT location");
        alertType[0].setContentText("A PSTR location is defined as an airport where you can fly from. " +
                "You assign a new location by choosing a country, ciy and airport below and clicking ADD.");
        alertType[0].showAndWait();
    }

    public void serverHelp() {
        alertType[0].setHeaderText("This is where you can see the server status");
        alertType[0].setTitle("Server help");
        alertType[0].setContentText("On this page you will be able to read some interesting information about the server that " +
                "is currently up and running. In the left text area you can see all the users that are currently online.");
        alertType[0].showAndWait();
    }

    public void noCountrySelected() {
        alertType[1].setHeaderText("Error");
        alertType[1].setTitle("No country selected.");
        alertType[1].setContentText("Please select a country in the choicebox below.");
        alertType[1].showAndWait();
    }

    public void genderIsNotSelected() {
        alertType[1].setHeaderText("Error");
        alertType[1].setTitle("No gender selected");
        alertType[1].setContentText("Please choose one of the two genders below");
        alertType[1].showAndWait();
    }

    public void eMailExistsError() {
        alertType[1].setHeaderText("Error");
        alertType[1].setTitle("eMail already exists");
        alertType[1].setContentText("The eMail you input already exists");
        alertType[1].showAndWait();
    }

    public void createAccountInformation() {
        alertType[1].setHeaderText("Error");
        alertType[1].setTitle("This information is already in our database");
        alertType[1].setContentText("You can only have one account. " +
                "You will now be returned to the login screen");
        alertType[1].showAndWait();
    }

    public void addTripHelp() {
        alertType[0].setHeaderText("This is where you can add trips");
        alertType[0].setTitle("Add trip");
        alertType[0].setContentText("On this page you will be able to add trips. insert all required " +
                "information in the fields below and press add.");
        alertType[0].showAndWait();
    }

    public void addFlightHelp() {
        alertType[0].setHeaderText("This is where you can add flights");
        alertType[0].setTitle("Add flights");
        alertType[0].setContentText("On this page you will be able to add flights. Pick an airplane and a " +
                "gate then press add and continue to next page.");
        alertType[0].showAndWait();
    }

    public void editBookingHelp() {
        alertType[0].setHeaderText("Select if you want food on the plane");
        alertType[0].setTitle("Edit booking");
        alertType[0].setContentText("On this page you are able to select if you want to have food on the plane. " +
                "You can also cancel you booking and get all your money refunded.");
        alertType[0].showAndWait();
    }

    public void viewPstrHelp() {
        alertType[0].setHeaderText("This is where you view pstr locations");
        alertType[0].setTitle("Edit booking");
        alertType[0].setContentText("Here you are able to view all of our offices and see information about those. " +
                "You can also see all airplanes are available at those airports.");
        alertType[0].showAndWait();
    }

    public void addAirPlaneHelp() {
        alertType[0].setHeaderText("This is where you add airplanes");
        alertType[0].setTitle("Add aiplane");
        alertType[0].setContentText("Enter all information in the textfields below and assign the plane to one of the " +
                "pstr airports.");
        alertType[0].showAndWait();
    }
}