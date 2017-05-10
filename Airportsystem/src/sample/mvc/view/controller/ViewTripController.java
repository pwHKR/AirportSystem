package sample.mvc.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import sample.mvc.model.Trip;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by woojen on 2017-05-10.
 */
public class ViewTripController implements Initializable {


    private ArrayList<Trip> tripList = new ArrayList<>();
    private ObservableList<String> stringTripList = FXCollections.observableArrayList();

    @FXML
    private ListView listView;

    @FXML
    TextArea textArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
