package sample.mvc.model;

/**
 * Created by woojen on 2017-04-12.
 */
public abstract class User extends Person {

    private String eMail;


    private String userName;
    private String password;


    public User(String firstName, String lastName, boolean isMale,
                String country, String ssn, String address,
                String eMail, String userName, String password) {
        super(firstName, lastName, isMale, country, ssn, address);
        this.eMail = eMail;
        this.userName = userName;
        this.password = password;
    }

    public User() {
    }


    public String getEmail() {
        return eMail;
    }

    public void setEmail(String eMail) {
        this.eMail = eMail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
