/**
 * Created by woojen on 2017-04-13.
 */
public interface DataStorage {

    void printAirplanes();

    void insertAirplane(Airplane airplane);

    void insertCustomer(Customer customer);

    String printPassword(String UserName);

}
