package sample.mvc.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import sample.mvc.model.DBHandler;
import sample.mvc.model.DataStorage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Stefan on 2017-04-20.
 */
public class ViewTripController implements Initializable {

    private ObservableList<String> trips = FXCollections.observableArrayList();

    @FXML
    private ListView<String> listView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DataStorage dbh = new DBHandler();

        trips = dbh.getTrips();

        listView.setItems(trips);

        System.out.println(trips);
    }
}
