package sample.mvc.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import sample.mvc.model.DBHandler;
import sample.mvc.model.SwitchScene;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by woojen on 2017-04-25.
 */
public class ServerController implements Initializable {

    private SwitchScene sw = new SwitchScene();

    private ObservableList<String> OnlineUsersList = FXCollections.observableArrayList();

    private DBHandler dbh = new DBHandler();

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


        // Server status
        ObservableList<String> serverVariablesList = dbh.getSystemVariables();


        serverVariables.setText(serverVariablesList.toString().
                replace("[", "").replace("]", ""));

        // Online users
        OnlineUsersList = dbh.getUsersOnline();
        onlineUsers.setText(OnlineUsersList.toString().replace("[", "").replace("]",
                ""));


    }

    @FXML
    private void updateOnlineUsers(ActionEvent ae) {


        OnlineUsersList = dbh.getUsersOnline();
        onlineUsers.setText(OnlineUsersList.toString().replace("[", "").replace("]",
                ""));


    }
}
