package sample.mvc.model;

/**
 * Created by woojen on 2017-04-08.
 */
public class Customer extends User {

    public Customer(String firstName, String lastName, boolean isMale, String country, String ssn, String address, String eMail, String userName, String password) {
        super(firstName, lastName, isMale, country, ssn, address, eMail, userName, password);

    }
}
