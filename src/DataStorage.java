/**
 * Created by woojen on 2017-04-13.
 */
public interface DataStorage {

    void printAirplanes();

    void insertAirplane(int passengerCapacity, String maxSpeed, String maxLuggageWeight, String regNumber);

    void insertCustomer(Customer customer);

}
