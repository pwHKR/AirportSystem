package sample.mvc.model;

/**
 * Created by woojen on 2017-04-13.
 */
public interface DataStorage {

    void printAirplanes();

    void insertAirplane(Airplane airplane);

    void insertUser(User customer, String typeOfUser);

    String matchPassword(String UserName);

    String printUserType(String Username);

    void insertDestination(Destination destination);


}
