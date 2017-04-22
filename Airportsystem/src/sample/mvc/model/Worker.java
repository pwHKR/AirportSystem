package sample.mvc.model;

/**
 * Created by woojen on 2017-04-12.
 */

public abstract class Worker extends User {

    private int workerId;

    public Worker(String firstName, String lastName, boolean isMale, String country, String ssn, String adress, String eMail, String userName, String password) {
        super(firstName, lastName, isMale, country, ssn, adress, eMail, userName, password);

    }


}
