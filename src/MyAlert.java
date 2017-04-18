/**
 * Created by woojen on 2017-04-18.
 */
public class MyAlert {

    static javafx.scene.control.Alert msg = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);

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
        msg.setContentText("You must enter a user name!");
        msg.show();
    }

    public static void loginFail() {


        msg.setContentText("Login failed!");
        msg.show();
    }
}
