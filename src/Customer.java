/**
 * Created by woojen on 2017-04-08.
 */
public class Customer extends User {

    private int customerId;

    public Customer(String firstName, String lastName, boolean isMale, String country, String ssn, Short age, String adress, String eMail, String userName, String password, int customerId) {
        super(firstName, lastName, isMale, country, ssn, age, adress, eMail, userName, password);
        this.customerId = customerId;
    }
}
