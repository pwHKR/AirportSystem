package sample.mvc.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.mvc.model.*;

import java.net.URL;
import java.util.InputMismatchException;
import java.util.ResourceBundle;

/**
 * Created by woojen on 2017-05-04.
 */
public class BillingController implements Initializable {


    private MyAlert myAlert = new MyAlert();
    private SwitchScene sw = new SwitchScene();
    private LocalFileStorage lfs = new LocalFileStorage();
    private DataStorage dbh = new DBHandler();


    private int systemId;

    @FXML
    private Label balanceLabel;
    @FXML
    private TextField amountTextField;
    @FXML
    private Button withdrawButton;
    @FXML
    private Button helpButton;


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


        // If statement will "catch" numbers with digits first and then either "f", "F", "d" or "D"
        if (!stringDepositAmount.endsWith("f") && !stringDepositAmount.endsWith("F")
                && !stringDepositAmount.endsWith("d") && !stringDepositAmount.endsWith("D")) {


            try {
                depositAmount = Double.parseDouble(stringDepositAmount);

                setBalanceTo = balance + depositAmount;


                dbh.setBalance(setBalanceTo, systemId);

                myAlert.depositMsg(stringDepositAmount);

                updateBalance();
            } catch (NumberFormatException e) {

                myAlert.generalError();
                amountTextField.clear();

            } catch (InputMismatchException i) {

                myAlert.generalError();
                amountTextField.clear();

            }

        }

        // if String number contains digits first and then either "f", "F", "d" or "D" as last char in the sequence
        else {

            myAlert.generalError();
            amountTextField.clear();

        }

    }


    @FXML
    private void goBack(ActionEvent ae) {

        sw.GoTo(ae, "Customer.fxml");

    }

    private void updateBalance() {

        balanceLabel.setText(dbh.getBalanceFromId(systemId) + "  sek");

    }

    @FXML
    private void withdraw(ActionEvent ae) {
        String stringBalance;
        double balance;

        String withdrawAmountText;
        double withdrawAmount;
        double setBalanceTo;

        stringBalance = dbh.getBalanceFromId(systemId);
        balance = Double.parseDouble(stringBalance);
        withdrawAmountText = amountTextField.getText();


        // If statement will "cath" numbers with digits first and then either "f", "F", "d" or "D"
        if (!withdrawAmountText.endsWith("f") && !withdrawAmountText.endsWith("F")
                && !withdrawAmountText.endsWith("d") && !withdrawAmountText.endsWith("D")) {

            try {
                withdrawAmount = Double.parseDouble(withdrawAmountText);
                setBalanceTo = balance - withdrawAmount;
                if (balance > withdrawAmount) {
                    dbh.setBalance(setBalanceTo, systemId);
                    myAlert.withdrawMsg(withdrawAmountText);
                    updateBalance();
                } else {
                    myAlert.withdrawErr();
                }
            } catch (NumberFormatException e) {

                myAlert.generalError();
                amountTextField.clear();

            } catch (InputMismatchException i) {

                myAlert.generalError();
                amountTextField.clear();

            }
        }

        // if String number contains digits first and then either "f", "F", "d" or "D" as last char in the sequence
        else {

            myAlert.generalError();
            amountTextField.clear();

        }
    }

    @FXML
    private void helpFunction(ActionEvent ae) {
        myAlert.billingHelp();
    }
}
