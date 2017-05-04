package sample.mvc.model;

/**
 * Created by woojen on 2017-04-12.
 */
public class Trip {

    private Double tripPrice;
    private String date;
    private int flightID;


    public Trip(Double tripPrice, String date, int flightID) {
        this.tripPrice = tripPrice;
        this.date = date;
        this.flightID = flightID;
    }

    public Double getTripPrice() {
        return tripPrice;
    }

    public String getDate() {
        return date;
    }

    public int getFlightID() {
        return flightID;
    }
}

