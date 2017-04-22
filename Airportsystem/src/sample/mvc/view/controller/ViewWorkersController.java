package sample.mvc.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import sample.mvc.model.DBHandler;
import sample.mvc.model.DataStorage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Stefan on 2017-04-22.
 */
public class ViewWorkersController implements Initializable {

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
        workerInfo.setText(infolist.toString());


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DataStorage dbHandler = new DBHandler();

        workerlist = dbHandler.getWorkers();


        workers.setItems(workerlist);


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
}
