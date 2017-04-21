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

    String getDestinationId(Destination destination);
    ObservableList<String> getCountries();

    ObservableList<String> getCities(String inputCountry);

    ObservableList<String> getAirports(String inputCity);


}
