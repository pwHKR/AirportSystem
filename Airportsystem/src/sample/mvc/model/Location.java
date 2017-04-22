package sample.mvc.model;

/**
 * Created by woojen on 2017-04-12.
 */
public class Location {

    private String airport;


    private String city;
    private String country;

    public Location(String airport, String city, String country) {
        this.airport = airport;
        this.city = city;
        this.country = country;
    }


    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getAirport() {
        return airport;
    }
}
