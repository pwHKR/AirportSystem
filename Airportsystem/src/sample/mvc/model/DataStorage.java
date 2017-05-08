package sample.mvc.model;

import javafx.collections.ObservableList;

/**
 * Created by woojen on 2017-04-13.
 */
public interface DataStorage {

    ObservableList<String> getAirplaneRegNumber(String selected);

    void insertAirplane(Airplane airplane, String select);

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

    int getUsersOnlineCount();

    boolean isUserOnline(String userName);

    void AddLocationPSTR(String IdPSTR, String locationId);

    ObservableList<String> getPSTR();

    ObservableList<String> getPSTRLocationInfo(String selected);

    ObservableList<String> getSystemVariables();

    ObservableList<String> getUsersOnline();

    void updateTimeStampUser(String userName); // Last activity by user

    ObservableList<String> searchForPassword(String userName);

    ObservableList<String> getAllAirplaneRegNumbers();

    ObservableList<String> getAirplaneInfo(String regNumber);

    void insertFlight(Flight flight, boolean newFlightStatus);

    ObservableList<Integer> getAllFlightIds();

    ObservableList<String> getCountriesWithPstr();

    ObservableList<String> matchCityWithCountry(String country);

    void setBalance(double balance, int systemId);

    int getIdFromUserName(String userName);

    String getBalanceFromId(int systemId);

    void insertTrip(Trip trip, int locationIdFrom, int locationIdTo);

    int getLocationId(String country, String city);

    Airplane getAirplaneObject(String regNumber);

    int getLastFlightId();

    ObservableList<String> getTrips();

    int getLocationIdFromPstrId(int pstrId);

    int getMaxTripId();

    //void linkTrip(Trip trip, Location)


}


