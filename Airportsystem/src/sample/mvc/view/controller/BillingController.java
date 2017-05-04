package sample.mvc.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.mvc.model.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by woojen on 2017-05-04.
 */
public class BillingController implements Initializable {


    SwitchScene sw = new SwitchScene();
    LocalFileStorage lfs = new LocalFileStorage();
    DataStorage dbh = new DBHandler();


    private int systemId;

    @FXML
    private Label balanceLabel;
    @FXML
    private TextField amountTextField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        systemId = dbh.getIdFromUserName(lfs.getCurrentUsersUserName()); // Get systemID of the current user
        balanceLabel.setText(dbh.getBalanceFromId(systemId) + "  sek");
    }

    @FXML
    private void deposit(ActionEvent ae) {

        String stringBalance;
        double balance;

        String stringDepositAmount;
        double depositAmount;

        double setBalanceTo;

        stringBalance = dbh.getBalanceFromId(systemId);
        balance = Double.parseDouble(stringBalance);

        stringDepositAmount = amountTextField.getText();
        depositAmount = Double.parseDouble(stringDepositAmount);

        setBalanceTo = balance + depositAmount;


        dbh.setBalance(setBalanceTo, systemId);

        MyAlert.depositMsg();

        updateBalance();


    }

    @FXML
    private void goBack(ActionEvent ae) {

        sw.GoTo(ae, "Customer.fxml");

    }

    private void updateBalance() {

        balanceLabel.setText(dbh.getBalanceFromId(systemId) + "  sek");

    }
}
