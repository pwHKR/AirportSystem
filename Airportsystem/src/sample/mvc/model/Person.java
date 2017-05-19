package sample.mvc.model;

/**
 * Created by woojen on 2017-04-08.
 */
public class Person {

    private String firstName;
    private String lastName;
    private boolean isMale;


    private String country;
    private String ssn;
    private String adress;


    public Person(String firstName, String lastName, boolean isMale, String country, String ssn, String adress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isMale = isMale;
        this.country = country;
        this.ssn = ssn;
        this.adress = adress;
    }

    public Person() {
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isMale() {
        return isMale;
    }

    public String getCountry() {
        return country;
    }

    public String getSsn() {
        return ssn;
    }

    public String getAdress() {
        return adress;
    }


}
