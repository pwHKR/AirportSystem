package sample.mvc.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import sample.mvc.model.DBHandler;
import sample.mvc.model.DataStorage;
import sample.mvc.model.SwitchScene;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Stefan on 2017-04-22.
 */
public class ViewWorkersController implements Initializable {

    SwitchScene sw = new SwitchScene();

    private ObservableList<String> workerlist = FXCollections.observableArrayList();
    private ObservableList<String> infolist = FXCollections.observableArrayList();

    @FXML
    private ListView<String> workers;

    @FXML
    private TextArea workerInfo;

    @FXML
    private void getWorkerInfo() {
        DataStorage dbHandler = new DBHandler();

        String choice = workers.getSelectionModel().getSelectedItem();
        infolist = dbHandler.getWorkerinfo(choice);
        workerInfo.setText(infolist.toString().replace("[", "").replace("]", ""));


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DataStorage dbHandler = new DBHandler();

        workerlist = dbHandler.getWorkers();


        workers.setItems(workerlist);


    }

    @FXML
    private void returnToAdmin(ActionEvent ae) {

        sw.GoTo(ae, "Admin.fxml");

    }
}
