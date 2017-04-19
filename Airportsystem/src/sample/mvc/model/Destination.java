package sample.mvc.model;

/**
 * Created by woojen on 2017-04-12.
 */
public class Destination {

    private Airport airport;
    private String city;
    private String country;

    public Destination(Airport airport, String city, String country) {
        this.airport = airport;
        this.city = city;
        this.country = country;
    }
}
