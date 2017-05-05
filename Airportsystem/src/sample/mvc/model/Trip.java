package sample.mvc.model;

/**
 * Created by woojen on 2017-04-12.
 */
public class Trip {

    private Double tripPrice;
    private String date;
    private int flightID;
    private int ticketAmount;


    public Trip(Double tripPrice, String date, int flightID, int ticketAmount) {
        this.tripPrice = tripPrice;
        this.date = date;
        this.flightID = flightID;
        this.ticketAmount = ticketAmount;
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


    public int getTicketAmount() {
        return ticketAmount;
    }

    public void setTicketAmount(int ticketAmount) {
        this.ticketAmount = ticketAmount;
    }
}

