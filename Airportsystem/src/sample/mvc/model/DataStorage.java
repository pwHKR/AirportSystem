package sample.mvc.model;

import javafx.collections.ObservableList;

/**
 * Created by woojen on 2017-04-13.
 */
public interface DataStorage {

    ObservableList<String> getAirplaneRegNumber(String selected);

    void insertAirplane(Airplane airplane, String select);

    //void insertUserAndPerson(User customer, String typeOfUser);

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

    ObservableList<String> getUsersOnline();

    void updateTimeStampUser(String userName); // Last activity by user

    ObservableList<String> searchForPassword(String userName);

    ObservableList<String> getAllAirplaneRegNumbers();

    ObservableList<String> getAirplaneInfo(String regNumber);

    void insertFlight(Flight flight, boolean newFlightStatus);

    ObservableList<Integer> getAllFlightIds();


    void setBalance(double balance, int systemId);

    int getIdFromUserName(String userName);

    String getBalanceFromId(int systemId);

    void insertTrip(Trip trip, int locationIdFrom, int locationIdTo);

    Airplane getAirplaneObject(String regNumber);

    int getLastFlightId();

    ObservableList<String> getTripList();

    int getLocationIdFromPstrId(int pstrId);

    int getMaxTripId();

    Location getLocationObject(int locationId);

    ObservableList<String> getFilteredResults(String input, String choice);

    Trip getTripObject(String tripId);

    int getTripId(String flightId);

    Location getFromLocationObject(int tripID);

    int getMaxPstrId();

    Flight getFlightObject(int flightId);


    ObservableList<String> getFlightInformation(int flightId);

    void changeFlightStatus(String newStatus, int flightId);

    int getTicketAmount(int tripId);

    void setTicketAmount(int ticketAmount, int tripId);

    void insertBooking(Booking booking);

    void insertPersonHasBooking(int systemId, int bookingId);

    int getMaxBookingId();

    ObservableList<String> getUserBookings(String userName);

    String getBookingInfo(int bookingId);

    Booking getBookingObject(int bookingId);

    void cancelBookingId(int bookingId);

    void insertPerson(Person person);

    boolean userNameExists(String userName);

    String getAddress(String ssn);

    int getSystemIdFromSsn(String ssn);

    void updateHasFood(boolean tOf, int bookingId);

    String checkSsn(String ssn);

    Customer getBonusPoint(int systemId);

    void addBonusPoint(String userName, int point);

    void insertOnlyUser(User user, String typeOfUser);
}


