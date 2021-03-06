package sample.mvc.view.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sample.mvc.model.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by woojen on 2017-04-25.
 */
public class AddAirplaneController extends ControllerModelObject implements Initializable {


    @FXML
    private ListView pstrId;
    @FXML
    private TextField model;
    @FXML
    private TextField passenger;
    @FXML
    private TextField speed;
    @FXML
    private TextField reg;
    @FXML
    private TextField maxKG;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        ObservableList<String> pstrLocationList = dbh.getPSTR();

        pstrId.setItems(pstrLocationList);

    }


    @FXML
    private void returnToAdmin(ActionEvent ae) {

        sw.GoTo(ae, "Admin.fxml");

    }

    @FXML
    private void addAirplane(ActionEvent ae) {
        int intMaxKG;
        int intPassenger;
        String stringSpeed;
        String stringReg;
        String stringModel;


        if (!model.getText().isEmpty()) {
            if (passenger.getText().matches("^\\d+$")) {
                if (speed.getText().matches("^\\d+$")) {
                    if (!reg.getText().isEmpty()) {
                        if (maxKG.getText().matches("^\\d+$")) {
                            if (pstrId.getSelectionModel().getSelectedItem() != null) {


                                String choice = pstrId.getSelectionModel().getSelectedItem().toString();


                                stringModel = model.getText();
                                intPassenger = Integer.parseInt(passenger.getText());
                                stringSpeed = speed.getText();
                                stringReg = reg.getText();
                                intMaxKG = Integer.parseInt(maxKG.getText());


                                Airplane airplane = new Airplane(intPassenger, intMaxKG, stringSpeed, stringReg, stringModel);

                                dbh.insertAirplane(airplane, choice);

                                sw.GoTo(ae, "Admin.fxml");


                            } else {
                                myAlert.noPstrIdSelectedError();
                            }
                        } else {
                            myAlert.noLuggageWeightError();
                        }
                    } else {
                        myAlert.noRegNumberError();
                    }
                } else {
                    myAlert.noMaxSpeedError();
                }
            } else {
                myAlert.noPassengerAmountError();
            }
        } else {
            myAlert.noPlaneModelError();
        }


    }

    @FXML
    private void helpFunction(ActionEvent ae) {
        myAlert.addAirPlaneHelp();
    }


}
