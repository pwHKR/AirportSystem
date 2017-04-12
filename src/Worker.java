/**
 * Created by woojen on 2017-04-12.
 */

public abstract class Worker extends User {

    private int workerId;

    public Worker(String firstName, String lastName, boolean isMale, String country, String ssn, Short age, String adress, String eMail, String userName, String password, Integer workerId) {
        super(firstName, lastName, isMale, country, ssn, age, adress, eMail, userName, password);
        this.workerId = workerId;
    }
}
