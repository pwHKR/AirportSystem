package sample.mvc.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import sample.mvc.model.DBHandler;
import sample.mvc.model.DataStorage;
import sample.mvc.model.SwitchScene;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by woojen on 2017-04-25.
 */
public class ServerController implements Initializable {

    SwitchScene sw = new SwitchScene();

    private ObservableList<String> ServerVariablesList = FXCollections.observableArrayList();
    private ObservableList<String> OnlineUsersList = FXCollections.observableArrayList();

    @FXML
    private TextArea serverVariables;

    @FXML
    private TextArea onlineUsers;

    @FXML
    private void returnToAdmin(ActionEvent ae) {

        sw.GoTo(ae, "Admin.fxml");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        DataStorage dbHandler = new DBHandler();

        // Server status
        ServerVariablesList = dbHandler.getSystemVariables();


        serverVariables.setText(ServerVariablesList.toString().
                replace("[", "").replace("]", ""));

        // Online users
        OnlineUsersList = dbHandler.getUsersOnline();
        onlineUsers.setText(OnlineUsersList.toString().replace("[", "").replace("]",
                ""));


    }

    @FXML
    private void updateOnlineUsers(ActionEvent ae) {

        DataStorage dbHandler = new DBHandler();

        OnlineUsersList = dbHandler.getUsersOnline();
        onlineUsers.setText(OnlineUsersList.toString().replace("[", "").replace("]",
                ""));


    }
}