package sample.mvc.model;

/**
 * Created by woojen on 2017-04-12.
 */
public class Trip {

    private Double tripPrice;
    private String date;
    private Flight flight;
    private Location location;


    public Trip(Double tripPrice, String date, Flight flight, Location location) {
        this.tripPrice = tripPrice;
        this.date = date;
        this.flight = flight;
        this.location = location;
    }

    public Double getTripPrice() {
        // TODO implement here
        return null;
    }

    public void setTripPrice(Double value) {
        // TODO implement here
    }

}

