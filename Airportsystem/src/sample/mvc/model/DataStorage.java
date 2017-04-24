package sample.mvc.model;

import javafx.collections.ObservableList;

/**
 * Created by woojen on 2017-04-13.
 */
public interface DataStorage {

    void printAirplanes();

    void insertAirplane(Airplane airplane);

    void insertUser(User customer, String typeOfUser);

    String matchPassword(String UserName);

    String printUserType(String Username);

    String getLocationId(Location location);

    ObservableList<String> getCountries();

    ObservableList<String> getCities(String inputCountry);

    ObservableList<String> getAirports(String inputCity);

    ObservableList<String> getWorkers();

    ObservableList<String> getWorkerinfo(String selected);

    void setUserOnline(String userName);

    void setUserOffline(String userName);

    void removeWorker(String sysId);

    int getUsersOnline();

}


