package sample.mvc.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Stefan on 2017-04-22.
 */
public class ViewWorkersController extends ControllerModelObject implements Initializable {

    ;

    private ObservableList<String> workerlist = FXCollections.observableArrayList();
    private ObservableList<String> infolist = FXCollections.observableArrayList();

    @FXML
    private ListView<String> workers;

    @FXML
    private TextArea workerInfo;

    @FXML
    private Button helpButton;

    @FXML
    private void getWorkerInfo() {

        String choice = workers.getSelectionModel().getSelectedItem();
        infolist = dbh.getWorkerinfo(choice);
        workerInfo.setText(infolist.toString().replace("[", "").replace("]", ""));

    }

    @FXML
    private void kickWorker() {
        String id = workers.getSelectionModel().getSelectedItem();

        dbh.removeWorker(id);
        if (workers.getSelectionModel().getSelectedItem() != null) {
            workerlist = dbh.getWorkers();
            workers.setItems(workerlist);
            workerInfo.setText(infolist.toString().replace("[", "").replace("]", ""));
            workerInfo.clear();
        } else {
            myAlert.noEmployeeSelectedError();
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        workerlist = dbh.getWorkers();


        workers.setItems(workerlist);


    }

    @FXML
    private void returnToAdmin(ActionEvent ae) {

        sw.GoTo(ae, "Admin.fxml");

    }

    @FXML
    private void setHelpButton(ActionEvent ae) {
        myAlert.helpViewWorkers();
    }
}
