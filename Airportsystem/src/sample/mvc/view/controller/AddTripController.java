package sample.mvc.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Stefan on 2017-04-20.
 */
public class AddTripController implements Initializable {
    ArrayList<String> arr = new ArrayList<>();


    @FXML
    private Label destination;

    @FXML
    private DatePicker date;

    @FXML
    private TextField price;

    @FXML
    private void addTrip() {
        //Trip trip = new Trip(Double.parseDouble(price.getText()),date.toString(),); ///// still to do
    }

    @FXML
    private void returnToAdmin(ActionEvent ae) {

        Node node = (Node) ae.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Admin.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
