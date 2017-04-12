/**
 * Created by woojen on 2017-04-12.
 */
public abstract class User extends Person {

    private String eMail;
    private String userName;
    private String password;


    public User(String firstName, String lastName, boolean isMale,
                String country, String ssn, Short age, String adress,
                String eMail, String userName, String password) {
        super(firstName, lastName, isMale, country, ssn, age, adress);
        this.eMail = eMail;
        this.userName = userName;
        this.password = password;
    }
}
