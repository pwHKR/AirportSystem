/**
 * Created by woojen on 2017-04-08.
 */
public class Person {

    private String firstName;
    private String lastName;
    private boolean isMale;
    private String country;
    private String ssn;
    private Short age;
    private String adress;


    public Person(String firstName, String lastName, boolean isMale, String country, String ssn, Short age, String adress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isMale = isMale;
        this.country = country;
        this.ssn = ssn;
        this.age = age;
        this.adress = adress;
    }
}
