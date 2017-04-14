/**
 * Created by woojen on 2017-04-08.
 */
public class Customer extends User {

    private int customerId;

    public Customer(String firstName, String lastName, boolean isMale, String country, String ssn, String adress, String eMail, String userName, String password) {
        super(firstName, lastName, isMale, country, ssn, adress, eMail, userName, password);

    }
}
