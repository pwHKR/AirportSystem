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
 * Created by Stefan on 2017-04-22.
 */
public class ViewWorkersController implements Initializable {

    ObservableList<String> workerlist = FXCollections.observableArrayList();

    @FXML
    private ListView<String> workers = new ListView<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DataStorage dbHandler = new DBHandler();

        workerlist = dbHandler.getWorkers();

        System.out.println(workerlist);      //// DOESNT WORK
        workers.setItems(workerlist);


    }
}
