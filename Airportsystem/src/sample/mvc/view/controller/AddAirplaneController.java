package sample.mvc.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sample.mvc.model.Airplane;
import sample.mvc.model.DBHandler;
import sample.mvc.model.DataStorage;
import sample.mvc.model.SwitchScene;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by woojen on 2017-04-25.
 */
public class AddAirplaneController implements Initializable {

    private SwitchScene sw = new SwitchScene();

    private ObservableList<String> pstrLocationList = FXCollections.observableArrayList();

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

        DataStorage dbh = new DBHandler();

        pstrLocationList = dbh.getPSTR();

        pstrId.setItems(pstrLocationList);

    }


    @FXML
    private void returnToAdmin(ActionEvent ae) {

        sw.GoTo(ae, "Admin.fxml");

    }

    @FXML
    private void addAirplane(ActionEvent ae) {


        int MaxKG;
        int Passenger;
        String Speed;
        String Reg;
        String Model;

        DataStorage dbh = new DBHandler();

        String choice = pstrId.getSelectionModel().getSelectedItem().toString();


        Model = model.getText();
        Passenger = Integer.parseInt(passenger.getText());
        Speed = speed.getText();
        Reg = reg.getText();
        MaxKG = Integer.parseInt(maxKG.getText());

        Airplane airplane = new Airplane(Passenger, MaxKG, Speed, Reg, Model);

        dbh.insertAirplane(airplane, choice);


    }


}
