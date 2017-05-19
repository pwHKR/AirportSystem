package sample.mvc.view.controller;

import sample.mvc.model.*;

/**
 * Created by woojen on 2017-05-20.
 */


public abstract class ControllerModelObject {
    protected MyAlert myAlert = new MyAlert();
    protected SwitchScene sw = new SwitchScene();
    protected DataStorage dbh = new DBHandler();
    protected LocalFileStorage local = new LocalFileStorage();
}
